package service;

import entities.Salle;
import entity.SalleResponse;
import EjbSession.SalleServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


@Path("/respo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ResponsableSalle {

    @Inject
    private SalleServiceLocal salleService;

   
    @Path("/salles")
    @GET
    public Response getSalles() {
    	System.out.println("\n \n \n \n gets salles \n \n \n " );
        List<Salle> sallesDisponibles = salleService.consulterSallesDisponibles();
        System.out.println("\n \n \n \n apres service \n \n \n " +sallesDisponibles.size());
        if(sallesDisponibles == null) 
        	System.out.println("\n \n \n \n vides salles \n \n \n ");
        // Salle<Resonse
        List<SalleResponse> listSalles=new ArrayList<SalleResponse>();
        for(Salle salle : sallesDisponibles)
        { 
        	SalleResponse sr=new SalleResponse(salle.getNom(),salle.getLocalisation(),salle.getCapacite(),salle.getType(),salle.isDisponibilite(),salle.getId());
        	listSalles.add(sr);
        }
        return Response.ok(listSalles).build();
    }


    @Path("/salles/add")
    @POST
    public Response addSalle(Salle salle) {
        try {
            // Appel de la méthode qui ne retourne rien
            salleService.ajouterSalle(salle.getNom(), salle.getLocalisation(), salle.getCapacite(), salle.getType(), salle.isDisponibilite());
            
            // Si l'ajout réussit, retourner un objet JSON avec success: true
            return Response.ok("{\"success\": true}").build();
        } catch (Exception e) {
            // En cas d'erreur, retourner un objet JSON avec success: false et le message d'erreur
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}")
                           .build();
        }
    }


    @PUT
    @Path("/salles/{id}")
    public Response updateSalle(
            @PathParam("id") long id,
            Salle salle) {
        salleService.modifierSalle(id, salle.getNom(), salle.getLocalisation(), salle.getCapacite(), salle.getType(), salle.isDisponibilite());
        return Response.ok("Salle modifiée avec succès").build();
    }

    @DELETE
    @Path("/salles/{id}")
    public Response deleteSalle(@PathParam("id") long id) {
        salleService.supprimerSalle(id);
        return Response.ok("Salle supprimée avec succès").build();
    }


    @GET
    @Path("/salles/{id}")
    public Response getSalle(@PathParam("id") long id) {
        Salle salle = salleService.trouverSalle(id);
        if (salle != null) {
        	SalleResponse sr=new SalleResponse(salle.getNom(),salle.getLocalisation(),salle.getCapacite(),salle.getType(),salle.isDisponibilite(),salle.getId());
            return Response.ok(sr).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Salle introuvable").build();
        }
    }
    

    /*@GET
    @Path("/salles/{nom}")
    public Response getSalleby(@PathParam("nom") String nom) {
        Salle salle = salleService.trouverSalleByname(nom);
        if (salle != null) {
        	SalleResponse sr=new SalleResponse(salle.getNom(),salle.getLocalisation(),salle.getCapacite(),salle.getType(),salle.isDisponibilite());
            return Response.ok(sr).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Salle introuvable").build();
        }
    }*/
}
