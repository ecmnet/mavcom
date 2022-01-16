package com.comino.mavcom.mavlink;

import com.comino.mavcom.model.DataModel;

public class MAVLinkBlockingReader extends MAVLinkReader implements Runnable {

	private MAVLinkToModelParser parser;

	public MAVLinkBlockingReader(int id, DataModel model) {
		this(id,false, model);
	}

	public MAVLinkBlockingReader(int id, boolean noCRCCheck, DataModel model) {
		super(id,noCRCCheck);
		this.parser = new MAVLinkToModelParser(model);
		Thread t = new Thread(this);
		t.setName("MAVLinkBlockingReader");
		t.start();
	}
	
	public MAVLinkToModelParser getParser() {
		return parser;
	}

	@Override
	public void run() {
		
			while(true) {
				try {
					synchronized(this) {
					if(packets.isEmpty())
						wait();
					while(!packets.isEmpty() && parser!=null)
					  parser.parseMessage(getNextMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

}
