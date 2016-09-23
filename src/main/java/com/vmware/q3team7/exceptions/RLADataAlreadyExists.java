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
public class RLADataAlreadyExists extends WebApplicationException {
    public RLADataAlreadyExists(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity(MarshalUtil.marshalExceptionMessage(new RLAExceptionMessage("RLADataAlreadyExists", message)))
                .type(MediaType.APPLICATION_XML).build());
    }
}
