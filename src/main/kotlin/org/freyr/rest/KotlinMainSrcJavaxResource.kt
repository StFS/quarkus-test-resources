package org.freyr.rest

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
//import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/kotlin-main-src-javax")
class KotlinMainSrcJavaxResource {

    @GET
    @Path("check/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    fun check(@PathParam("parameter") parameter: String?) : String {
        return "Check [kotlin-main-javax] $parameter"
    }
}