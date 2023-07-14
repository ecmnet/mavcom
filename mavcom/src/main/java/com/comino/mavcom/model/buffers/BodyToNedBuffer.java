package com.comino.mavcom.model.buffers;

import com.comino.mavcom.model.DataModel;
import com.comino.mavcom.utils.MSP3DUtils;

import georegression.struct.se.Se3_F64;

public class BodyToNedBuffer<T> {
	
	private final BoundedSortedMap<Long,Se3_F64> buffer;
	private final DataModel model;
	
	public BodyToNedBuffer(DataModel model,int size) {
		this.model  = model;
		this.buffer = new BoundedSortedMap<Long,Se3_F64>(size);
		for(int i=0;i< size;i++) {
			buffer.put(0L, new Se3_F64());
		}
	}
	
     public void add(long timestamp) {
    	 Se3_F64 t = buffer.firstEntry().getValue();
    	 MSP3DUtils.convertModelToSe3_F64(model, t);
    	 buffer.put(timestamp, t); 	 
     }
     
     public Se3_F64 get(long timestamp) {
    	// System.out.println(buffer.floorEntry(Long.MAX_VALUE).getKey() - buffer.floorEntry(timestamp).getKey());
    	 return this.buffer.floorEntry(timestamp).getValue();
     }
     
     public long getTimestampAt(long timestamp) {
    	 return this.buffer.floorEntry(timestamp).getKey();
     }
     
     public long getDelta(long tms) {
    	 return buffer.lastKey()-buffer.floorKey(tms);
     }
     
     public long getIndexAt(long tms) {
    	 return buffer.subMap(tms, Long.MAX_VALUE).size();
     }
    
}
