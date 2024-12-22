package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTOS.seance.SeanceRequest;
import DTOS.seance.SeanceResponse;
import EjbSession.FiliereServiceLocal;
import EjbSession.MatiereServiceLocal;
import EjbSession.SalleServiceLocal;
import EjbSession.SeanceServiceLocal;
import EjbSession.UtilisateurServiceLocal;
import entities.Filiere;
import entities.Matiere;
import entities.Salle;
import entities.Seance;
import entities.Utilisateur;
import entity.EmploiRequest;
import entity.SeanceRequestWeb;
import entity.Seancerequest;


@Path("/seances")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerSeance {

	@Inject
	SeanceServiceLocal serviceSeance;
	@Inject
	UtilisateurServiceLocal userService;
	@Inject
	FiliereServiceLocal filiereService;
	@Inject
	SalleServiceLocal salleSrevice;
   @Inject
  MatiereServiceLocal matiereService;
	 @Path("/add")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response ajouterSeance(SeanceRequestWeb seanceRequest) {
	        try {
	            // Appel au service pour ajouter une séance
	 	       //String heureDebut, String heureFin, String typeSeance, Utilisateur utilisateur, Filiere filiere, Matiere matiere, Salle salle
	        	Utilisateur user=userService.trouverUtilisateur(seanceRequest.getId_user());
	        	Filiere f=filiereService.getfiliereById(seanceRequest.getId_filiere());
	        	Salle salle=salleSrevice.trouverSalle(seanceRequest.getId_salle());        	
	        	 Matiere matiere = matiereService.getMatiere(seanceRequest.getId_matiere());
	        	Seance seance=new Seance(seanceRequest.getJour(),seanceRequest.getHeure(),seanceRequest.getType(),user,f,matiere,salle);
	            serviceSeance.ajouterSeance(seance);
	            return Response.ok("Séance ajoutée avec succès").build();
	             } catch (Exception e) {
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                           .entity("Erreur: " + e.getMessage())
	                           .build();
	             }
	    }
	 
	 @Path("/{id}")
	 @GET
	 public List<EmploiRequest> getSeancesByFiliereId(@PathParam("id") Long idFiliere) {
	     try {
	         // Retrieve all sessions for the given "idFiliere"
	         List<Seance> seances = serviceSeance.getSeancesByFiliere(idFiliere);
	         List<EmploiRequest> emploit=new ArrayList<EmploiRequest>();
	         
	         for(Seance s : seances)
	         {   
	        	 EmploiRequest e = new EmploiRequest(s.getUtilisateur().getNom(),s.getSalle().getNom(), s.getMatiere().getNom(),s.getHeureDebut(), s.getHeureFin());
	        	 emploit.add(e);
	         }
	         // If no sessions are found, return an empty list
	         return emploit;
	     } catch (Exception e) {
	         // Log the error (optional) and return an empty list in case of failure
	         e.printStackTrace();
	         return new ArrayList<>(); // Return an empty list instead of throwing an exception
	     }
	 }


	
	
	
	

}