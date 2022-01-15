package com.comino.mavcom.mavlink;

public class MAVLinkBlockingReader extends MAVLinkReader implements Runnable {

	private MAVLinkToModelParser parser;

	public MAVLinkBlockingReader(int id, MAVLinkToModelParser parser) {
		this(id,false, parser);
	}

	public MAVLinkBlockingReader(int id, boolean noCRCCheck, MAVLinkToModelParser parser) {
		super(id,noCRCCheck);
		this.parser = parser;
		Thread t = new Thread(this);
		t.setName("MAVLinkBlockingReader");
		t.start();
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
