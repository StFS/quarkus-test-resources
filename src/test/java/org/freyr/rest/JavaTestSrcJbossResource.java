package org.freyr.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/java-test-src-jboss")
public class JavaTestSrcJbossResource {
    @GET
    @Path("check/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@PathParam("parameter") String parameter) {
        return "Check [java-test-jboss] " + parameter;
    }

}
