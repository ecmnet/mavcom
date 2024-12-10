/**
 * Generated class : VIDEO_STREAM_STATUS_FLAGS
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface VIDEO_STREAM_STATUS_FLAGS
 * Stream status flags (Bitmap)
 **/
public interface VIDEO_STREAM_STATUS_FLAGS {
    /**
     * Stream is active (running)
     */
    public final static int VIDEO_STREAM_STATUS_FLAGS_RUNNING = 1;
    /**
     * Stream is thermal imaging
     */
    public final static int VIDEO_STREAM_STATUS_FLAGS_THERMAL = 2;
    /**
     * Stream can report absolute thermal range (see CAMERA_THERMAL_RANGE).
     */
    public final static int VIDEO_STREAM_STATUS_FLAGS_THERMAL_RANGE_ENABLED = 4;
}
