package com.vmware.q3team7.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vmware.q3team7.models.RLAExceptionMessage;
import com.vmware.q3team7.util.MarshalUtil;

/**
 * @author kdaniel
 *
 */
public class RLADataFormatException extends WebApplicationException {
    public RLADataFormatException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(MarshalUtil.marshalExceptionMessage(new RLAExceptionMessage("RLADataFormatException", message)))
                .type(MediaType.APPLICATION_XML).build());
    }
    
}
