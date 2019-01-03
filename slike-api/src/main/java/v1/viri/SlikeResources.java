package v1.viri;


import beans.SlikeBeans;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import entities.Slika;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Log
@ApplicationScoped
@Path("/slike")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SlikeResources {

    @Inject
    private SlikeBeans slikeBeans;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getSlikeList(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        List<Slika> albumList = slikeBeans.getSlikaList(query);
        Long count = slikeBeans.getSlikaCount(query);
        return Response.ok(albumList).header("X-Total-Count", count).build();
    }

    @GET
    @Path("/{id}")
    public Response getSlika(@PathParam("id") Integer id) {

        Slika uporabnik = slikeBeans.getSlika(id);

        if (uporabnik == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(uporabnik).build();
    }
    @GET
    @Path("/album/{id}")
    public Response getAlbumById(@PathParam("id") Integer id) {

        Slika uporabnik = slikeBeans.getSlika(id);

        if (uporabnik == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(uporabnik).build();
    }

    @POST
    public Response createSlika(Slika slika) {

        if ((slika.getNaslov() == null || slika.getNaslov().isEmpty()) || (slika.getPath() == null
                || slika.getPath().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            slikeBeans.createSlika(slika);
        }

        if (slika.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(slika).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(slika).build();
        }
    }


    @PUT
    @Path("{id}")
    public Response putSlika(@PathParam("id") String id, Slika slika) {

        slika = slikeBeans.putSlika(id, slika);

        if (slika == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (slika.getId() != null)
                return Response.status(Response.Status.OK).entity(slika).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteSlika(@PathParam("id") String id) {

        boolean deleted = slikeBeans.deleteSlika(id);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("info")
    public Response info() {



        JsonObject json = Json.createObjectBuilder()
                .add("clani", Json.createArrayBuilder().add("bk2178"))
                .add("opis_projekta", "Projekt implementira projekt za deljenje slik.")
                .add("mikrostoritve", Json.createArrayBuilder().add("http://35.225.210.81:8080/v1/slike"))
                .add("github", Json.createArrayBuilder().add("https://github.com/RSO-B/katalogSlik"))
                .add("travis", Json.createArrayBuilder().add("https://travis-ci.com/RSO-B/katalogSlik"))
                .add("dockerhub", Json.createArrayBuilder().add("https://hub.docker.com/r/bostjan15/rsoslike/"))
                .build();


        return Response.ok(json.toString()).build();
    }

}
