package org.freyr.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/java-main-src-jboss")
public class JavaMainSrcJbossResource {
    @GET
    @Path("check/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@PathParam String parameter) {
        return "Check [java-main-jboss] " + parameter;
    }

}
