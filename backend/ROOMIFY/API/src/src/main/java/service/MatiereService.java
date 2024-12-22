package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EjbSession.FiliereServiceLocal;
import EjbSession.MatiereServiceLocal;
import entities.Filiere;
import entities.Matiere;
import entity.MatiereResponse;
import entity.Matieredao;

@Path("/matiere")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MatiereService {
  @Inject
  MatiereServiceLocal matiereService;
  @Inject
  FiliereServiceLocal filiereservice;
  
  
 @Path("/add")
 @POST
 public Response add(Matieredao matiere) {
     try {
         // Ajout de la filière
    	 System.out.println("matiere \n \n \n "+matiere.getHeures()+matiere.getNom()+"\n \n \n"); 
    	 Filiere f=filiereservice.getfiliereById(matiere.getId_filiere());
    	 System.out.println("matiere haha"+f.getNom()+"\n \n \n");
         matiereService.ajouterMatiere(matiere.getNom(), matiere.getHeures(),f);
         
         // Créer une réponse avec la filière ajoutée
         return Response.status(Response.Status.CREATED)
                 .entity(matiere) // Retourner l'objet Filiere ajouté
                 .build();
     } catch (Exception e) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity("Error while adding Filiere: " + e.getMessage())
                 .build();
     }
 }
 
 
 @Path("/{id}")
 @GET
 public List<MatiereResponse> getall(@javax.ws.rs.PathParam("id") long id) {
	 Filiere filiere = filiereservice.getfiliereById(id);
	 if(filiere==null)
		return null; 
	 filiere.getMatieres().size(); // Forces Hibernate to initialize the collection
	
	 
	 List<Matiere> m= matiereService.consulterMatieresParFiliere(filiere);
	List<MatiereResponse> matieres=new ArrayList<MatiereResponse>();
	for(Matiere mat : m )
	{   MatiereResponse r=new MatiereResponse(mat.getNom(),mat.getId(),mat.getHeures());
		matieres.add(r);
	}
  return matieres;    
 }
 @Path("/{id}")
 @DELETE
 public Response delete(@javax.ws.rs.PathParam("id") long id) {
     try {
         System.out.println("Méthode delete appelée avec ID : " + id);
         matiereService.supprimerMatiere(id);
         return Response.status(Response.Status.OK)
                        .entity("Matière avec ID " + id + " supprimée avec succès.")
                        .build();
     } catch (IllegalArgumentException e) {
         return Response.status(Response.Status.NOT_FOUND)
                        .entity("Matière avec ID " + id + " introuvable.")
                        .build();
     } catch (Exception e) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Une erreur est survenue lors de la suppression de la matière.")
                        .build();
     }
 }

 @Path("/gets")
 @GET
 public String ahh()
 {
	 return "hehh";
 }
 
 
 
 
}
