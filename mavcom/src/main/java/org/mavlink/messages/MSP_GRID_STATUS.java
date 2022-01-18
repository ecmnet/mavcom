/**
 * Generated class : MSP_GRID_STATUS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface MSP_GRID_STATUS
 * 
 **/
public interface MSP_GRID_STATUS {
    /**
     * Grid has a raw point cloud stored
     */
    public final static int POINT_CLOUD = 0;
    /**
     * Grid has a 2D map stored
     */
    public final static int GRID_2D = 1;
    /**
     * Grid has a octree based 3D map stored
     */
    public final static int GRID_3D_OCTREE = 2;
    /**
     * Grid has a circular 3D map stored
     */
    public final static int GRID_3D_CIRCULAR = 3;
}
