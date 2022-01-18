/**
 * Generated class : WIFI_CONFIG_AP_RESPONSE
 * DO NOT MODIFY!
 **/
package org.mavlink.messages;
/**
 * Interface WIFI_CONFIG_AP_RESPONSE
 * Possible responses from a WIFI_CONFIG_AP message.
 **/
public interface WIFI_CONFIG_AP_RESPONSE {
    /**
     * Undefined response. Likely an indicative of a system that doesn't support this request.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_UNDEFINED = 0;
    /**
     * Changes accepted.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_ACCEPTED = 1;
    /**
     * Changes rejected.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_REJECTED = 2;
    /**
     * Invalid Mode.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_MODE_ERROR = 3;
    /**
     * Invalid SSID.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_SSID_ERROR = 4;
    /**
     * Invalid Password.
     */
    public final static int WIFI_CONFIG_AP_RESPONSE_PASSWORD_ERROR = 5;
}
