package service;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EjbSession.FiliereServiceLocal;
import entities.Filiere;
import entity.FiliereResponse;

@Path("/cordinateur")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerFiliere {

    @Inject
    private FiliereServiceLocal serviceFiliere;

    // 🟢 Récupérer toutes les filières
    @Path("/filieres")
    @GET
    public List<FiliereResponse> getAll() {
        List<FiliereResponse> fr = new LinkedList<>();
        List<Filiere> fl = serviceFiliere.consulterToutesLesFilieres();
        for (Filiere f : fl) {
            // Retourner l'id comme Long, nom et effectif
            FiliereResponse e = new FiliereResponse(f.getId(),f.getNom(),f.getEffectif());
            fr.add(e);
        }
        return fr;
    }

    // 🟢 Récupérer une filière spécifique
    @Path("/filieres/{id}")
    @GET
    public FiliereResponse getFiliere(@PathParam("id") Long id) {
        Filiere f = serviceFiliere.getfiliereById(id);
        // Retourner l'`id` comme Long avec les autres informations
        FiliereResponse fr = new FiliereResponse(f.getId(), f.getNom(), f.getEffectif());
        return fr;
    }

    // 🟢 Ajouter une nouvelle filière
    @Path("/filieres/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFiliere(Filiere filiere) {
        try {
            // Ajout de la filière
            serviceFiliere.AjouterFiliere(filiere.getNom(), filiere.getEffectif());
            
            // Créer une réponse avec la filière ajoutée
            return Response.status(Response.Status.CREATED)
                    .entity(filiere) // Retourner l'objet Filiere ajouté
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while adding Filiere: " + e.getMessage())
                    .build();
        }
    }

    @Path("/filieres/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFiliere(@PathParam("id") Long id, Filiere filiere) {
        try {
            // 🟢 Utilisation de la méthode ModifierFiliere et récupération de la filière mise à jour
            serviceFiliere.ModifierFiliere(id, filiere.getNom(), filiere.getEffectif());

            // 🟢 Récupérer la filière mise à jour après la modification
            Filiere updatedFiliere = serviceFiliere.getfiliereById(id);

            // ✅ Retourner la filière mise à jour dans la réponse
            return Response.ok(updatedFiliere).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"success\": false, \"message\": \"Error while updating Filiere: " + e.getMessage() + "\"}")
                    .build();
        }
    }


    // 🟢 Supprimer une filière
    @Path("/filieres/delete/{id}")
    @DELETE
    public Response deleteFiliere(@PathParam("id") Long id) {
        try {
            serviceFiliere.SupprimerFiliere(id);
            return Response.status(Response.Status.OK)
                    .entity("Filiere successfully deleted")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while deleting Filiere: " + e.getMessage())
                    .build();
        }
    }
    
    @OPTIONS
    @Path("/{id}/{nom}/{effectif}")
    public Response handleOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}
