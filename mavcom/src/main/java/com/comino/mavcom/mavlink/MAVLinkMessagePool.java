package com.comino.mavcom.mavlink;


import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.MAVLinkMessageFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MAVLinkMessagePool {

	private static MAVLinkMessagePool instance=null;

	private final Map<Integer,MessagePool> pool;

	public static MAVLinkMessagePool getInstance() {
		if(instance == null)
			instance = new MAVLinkMessagePool();
		return instance;
	}

	private MAVLinkMessagePool() {
		this.pool = new HashMap<Integer,MessagePool>();
	}

	public MAVLinkMessage checkout(int msgId, int sysId, int componentId,  byte[] rawData) throws IOException {

		MessagePool msgPool = pool.get(msgId);
		if(msgPool == null) {
			msgPool = new MessagePool();
			pool.put(msgId, msgPool);
		}
		return msgPool.checkout(msgId, sysId, componentId, rawData);
	}

	public void invalidate(MAVLinkMessage o) {
		MessagePool msgPool = pool.get(o.messageType);
		if(msgPool == null)
			return;
		msgPool.invalidate(o);
	}

	public int getHits() {
		int size = 0;
		for(Entry<Integer, MessagePool> p : pool.entrySet())
			size = size+p.getValue().getHits();
		return size;
	}

	public int getPoolSize() {
		return pool.size();
	}


	private class MessagePool {

		private final LinkedList<MAVLinkMessage> locked, unlocked;
		private int hits = 0;

		public MessagePool() {
			
			locked   = new LinkedList<MAVLinkMessage>();
			unlocked = new LinkedList<MAVLinkMessage>(); 
		}

		public MAVLinkMessage checkout(int msgId, int sysId, int componentId,  final byte[] rawData) throws IOException {
			MAVLinkMessage o;
			o = unlocked.poll();
			if(o!=null) {
				locked.push(o);
				o.sysId = sysId;
				o.componentId = componentId;
				o.isValid = true;
				LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new ByteArrayInputStream(rawData));
				o.decode(dis); 
				dis.close();
				hits++;
				return o;
			}
			o = MAVLinkMessageFactory.getMessage(msgId, sysId, componentId, rawData);
			locked.push( o);
			return o;
		}

		public void invalidate(MAVLinkMessage o) {
			if(locked.size()>0) {
				o.isValid = false;
				locked.remove(o);
				unlocked.push(o);
			}
		}

		public int getHits() {
			return hits;
		}

	}
}
