package service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wildfly.security.mechanism.http.UsernamePasswordAuthenticationMechanism;

import EjbSession.UtilisateurServiceLocal;

import entities.Utilisateur;
import entity.ProfResponse;
import entity.user;
@Stateless
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceLogin {
	 @Inject
	 private UtilisateurServiceLocal service;
	// Register 
	@Path("/add")
	@POST
	public String AddUser(Utilisateur user)
	{   
		
     	service.ajouterUtilisateur(user.getNom(), user.getEmail(), user.getMotDePasse(), "prof");
     	return "success";
     	
	}
	


	@Path("/auth")
	@POST
	public Response login(user user) {
	    try {
	        Utilisateur u = service.authentifier(user.getUsername(), user.getPassword());
	       
	        JsonObject jsonResponse = Json.createObjectBuilder()
	                                     .add("message", "Login successful")
	                                     .add("user", Json.createObjectBuilder()
	                                                      .add("id", u.getId())
	                                                      .add("role", u.getRole())
	                                                      .add("nom", u.getNom()) // Ajout uniquement du nom
	                                                      .build())
	                                     .build();

	        // Si l'authentification est réussie, retourne une réponse 200 OK avec l'objet JSON
	        return Response.status(Response.Status.OK)
	                       .entity(jsonResponse.toString())
	                       .build();
	    } catch (Exception e) {
	        // Créer un objet JSON pour la réponse d'erreur
	        JsonObject jsonResponse = Json.createObjectBuilder()
	                                     .add("message", "Invalid: " + e.getMessage())
	                                     .build();

	        // Gérer les erreurs et retourner une réponse 500 Internal Server Error avec l'objet JSON
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity(jsonResponse.toString())
	                       .build();
	    }
	}

@Path("/prof")
	@GET
	public List<ProfResponse> get()
	{  List<Utilisateur> users =service.getProfs();
	   List<ProfResponse> profs=new ArrayList<ProfResponse>();
	   for(Utilisateur u : users)
	   { 
		   ProfResponse prof=new ProfResponse(u.getId(),u.getEmail(),u.getNom());
		   profs.add(prof);
	   }
		return profs;
	}
	
@Path("/prof/{id}")
@DELETE
public void deleteprof(@PathParam("id") long id)
{
	service.supprimerUtilisateur(id);
}
}