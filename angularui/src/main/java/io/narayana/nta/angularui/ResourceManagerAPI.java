package io.narayana.nta.angularui;

import io.narayana.nta.persistence.DataAccessObject;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: Amila
 * Date: 15/04/14
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
@Path("/resourcemanager")
public class ResourceManagerAPI
{
    @GET
    @Produces("application/json")
    public Response getResourceManagers()
    {
        return Response.ok("Resource Manager Successfully Called").build();
    }
}
