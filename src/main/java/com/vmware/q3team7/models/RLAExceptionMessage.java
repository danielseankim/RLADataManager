package com.vmware.q3team7.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kdaniel
 *
 */

@XmlRootElement
public class RLAExceptionMessage {
    private String RLAExceptionMessageType;
    private String RLAExceptionMessage;

    public RLAExceptionMessage() {}
    
    public RLAExceptionMessage(String type, String msg) {
        this.RLAExceptionMessageType = type;
        this.RLAExceptionMessage = msg;
    }

    /**
     * @return the rLAExceptionMessageType
     */
    public String getRLAExceptionMessageType() {
        return RLAExceptionMessageType;
    }
    /**
     * @param rLAExceptionMessageType the rLAExceptionMessageType to set
     */
    public void setRLAExceptionMessageType(String rLAExceptionMessageType) {
        RLAExceptionMessageType = rLAExceptionMessageType;
    }
    /**
     * @return the rLAExceptionMessage
     */
    public String getRLAExceptionMessage() {
        return RLAExceptionMessage;
    }
    /**
     * @param rLAExceptionMessage the rLAExceptionMessage to set
     */
    public void setRLAExceptionMessage(String rLAExceptionMessage) {
        RLAExceptionMessage = rLAExceptionMessage;
    }
    
    
}
