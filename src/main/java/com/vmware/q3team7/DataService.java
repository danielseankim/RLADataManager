package com.vmware.q3team7;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.vmware.q3team7.models.RLAData;
import com.vmware.q3team7.util.RLADataPersister;
import com.vmware.q3team7.util.RLAValidator;

import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author kdaniel
 *
 */

@Path("/data")
public class DataService {
    final static Logger logger = Logger.getLogger(DataService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response defaultGET(){
        logger.debug("Invalid service path is hit - returning 404 message.");
        return Response.status(404).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public RLAData getData(@PathParam("id") String dataId) throws WebApplicationException {
        logger.debug("Received message to return data with id, " + dataId);
        RLAData data = null;
        try {
            data = RLADataPersister.getJedisPersister().getRLAData(dataId);
        } catch (WebApplicationException we) {
            throw we;
        } catch (JedisConnectionException e) {
            logger.error("Problem connecting to the Redis server!!!!");
            WebApplicationException wae = new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
            throw wae;
        } catch (Exception e) {
            logger.error("Unexpected exception is caught: " + e.getMessage());
            e.printStackTrace();
            WebApplicationException wae = new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
            throw wae;
        }
        return data;
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public RLAData createData(RLAData data) throws WebApplicationException {
        try {
            logger.debug("Received request to create data: " + data);
            RLAValidator.simpleDataValidation(data);
            RLADataPersister.getJedisPersister().storeRLAData(data);
        } catch (WebApplicationException we) {
            throw we;
        } catch (Exception e) {
            WebApplicationException wae = new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
            throw wae;
        }
        return null;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public RLAData updateData(RLAData data){
        try {
            logger.debug("Received request to update data: " + data);
            RLADataPersister dataPersister = RLADataPersister.getJedisPersister();
            dataPersister.getRLAData(data.getId());
            RLAValidator.simpleDataValidation(data);
            dataPersister.storeRLAData(data);
        } catch (WebApplicationException we) {
            throw we;
        } catch (Exception e) {
            WebApplicationException wae = new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
            throw wae;
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public RLAData removeData(@PathParam("id") String dataId){
        try {
            RLADataPersister.getJedisPersister().removeRLAData(dataId);
        } catch (WebApplicationException we) {
            throw we;
        } catch (Exception e) {
            WebApplicationException wae = new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
            throw wae;
        }
        return null;
    }
}
