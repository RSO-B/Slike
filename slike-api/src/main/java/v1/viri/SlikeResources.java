package v1.viri;


import beans.SlikeBeans;
import entities.Slika;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/slike")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SlikeResources {

    @Inject
    private SlikeBeans slikeBeans;

    @GET
    public Response getSlikeList(){
        List<Slika> uporabnikList = slikeBeans.getSlikaList();
        return Response.ok(uporabnikList).build();
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

    @POST
    public Response createSlika(Slika slika) {

        if ((slika.getNaslov() == null || slika.getNaslov().isEmpty()) || (slika.getPath() == null
                || slika.getPath().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            slika = slikeBeans.createSlika(slika);
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

}
