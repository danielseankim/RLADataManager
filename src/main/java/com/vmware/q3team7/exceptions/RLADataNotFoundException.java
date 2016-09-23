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
public class RLADataNotFoundException extends WebApplicationException {
    private String message;

    public RLADataNotFoundException(String dataId) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(MarshalUtil.marshalExceptionMessage(new RLAExceptionMessage("RLADataFormatException", 
                        "RLA Data Service could not find the data referenced by the id, " + dataId + ".")))
                .type(MediaType.APPLICATION_XML).build());
    }
}
