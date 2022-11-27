package com.comino.mavcom.mavlink;


import org.mavlink.io.LittleEndianDataInputStream;
import org.mavlink.messages.MAVLinkMessage;
import org.mavlink.messages.MAVLinkMessageFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
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
		this.pool = new HashMap<Integer,MessagePool>(300);
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

		private Hashtable<MAVLinkMessage,Boolean> locked, unlocked;
		private int hits = 0;

		public MessagePool() {
			locked   = new Hashtable<MAVLinkMessage,Boolean>(0);
			unlocked = new Hashtable<MAVLinkMessage,Boolean>(0); 
		}
		
		public MAVLinkMessage checkout(int msgId, int sysId, int componentId,  byte[] rawData) throws IOException {
			MAVLinkMessage o;
			if( unlocked.size() > 0 ) {
				LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new ByteArrayInputStream(rawData));
				Enumeration<MAVLinkMessage> e = unlocked.keys();
				o = e.nextElement();
				o.sysId = sysId;
				o.componentId = componentId;
				o.decode(dis);
				dis.close();
				unlocked.remove(o);
				locked.put(o, true);
				hits++;
				return o;
			}
			o = MAVLinkMessageFactory.getMessage(msgId, sysId, componentId, rawData);
			locked.put( o, true );
			return o;
		}
		
		public void invalidate(MAVLinkMessage o) {
			if(locked.size()>0) {
				locked.remove(o);
				unlocked.put(o, true);
			}
		}
		
		public int getHits() {
			return hits;
		}

	}
}
