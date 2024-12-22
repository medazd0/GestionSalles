package service;


import EjbSession.NotificationServiceLocal;
import EjbSession.ReservationServiceLocal;
import EjbSession.SalleServiceLocal;
import EjbSession.UtilisateurServiceLocal;
import entities.Notification;
import entities.Reservation;
import entities.Salle;
import entities.Utilisateur;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.NotificationDto;

import entity.Proff;

import entity.ReservationDto;
import entity.ResrvationShow;
@Path("/prof")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfResource {
	

     @Inject
	 private  NotificationServiceLocal notificationService;
    @Inject
    private ReservationServiceLocal reservationService;
    @Inject
    private SalleServiceLocal salleService;
    @Inject 
    private UtilisateurServiceLocal serviceUser;
    
    @GET
    public List<Proff> getall()
    {
		List<Utilisateur> users = serviceUser.getProfs();
		List<Proff> profs=new ArrayList<Proff>();
		for(Utilisateur u : users)
		{
			Proff p = new Proff(u.getNom(), u.getId());
			profs.add(p);
		}
		return profs;
		
    }
    //KHDAMAA
    @Path("/notifications/{id}")
    @GET
    public List<NotificationDto>  getAll(@PathParam("id") long id)
    {
    	Utilisateur user=serviceUser.trouverUtilisateur(id);
    	//List<Notification> notifications= notificationService.consulterNotificationsParUtilisateur(user);
    	List<Notification> notifications=user.getNotifications();
    	List<NotificationDto> nf=new ArrayList<NotificationDto>();
    	
    	for(Notification n : notifications )
    	{
    		NotificationDto note=new NotificationDto(n.getMessage(),n.getId());
    		nf.add(note);
    	}
    	return nf;
    	
    }
    @Path("/addReservation")
    @POST
   
    public Response demanderReservation(ReservationDto demande) {
    	
    	try {
    		
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
           System.out.println(demande.getDateDebut()+"\n\n\n\n"+demande.getDateFin());
           
           Date dateDebut = formatter.parse(demande.getDateDebut()); 
           Date dateFin = formatter.parse(demande.getDateFin());
           
           System.out.println(dateDebut+"\n\n\n\n"+dateFin);
           
           
           
            boolean success = reservationService.addReservation(
                    demande.getTypeSeance(),
                    dateDebut,
                    dateFin,
                    demande.getIdFiliere(),
                    demande.getIdSalle(),
                    demande.getIdUtilisateur()
            );

            if (success) {
                // Envoi de la notification
                try {
                    String message = "Votre réservation de la salle "+demande.getIdSalle()+"  a été confirmée.";
                    notificationService.envoyerNotification(demande.getIdUtilisateur(), message);
                    salleService.reserveSalle(demande.getIdSalle());
                    
                } catch (Exception e) {
                    
                    e.printStackTrace();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Réservation réussie, mais une erreur s'est produite lors de l'envoi de la notification.").build();
                }

                
                return Response.ok("Réservation effectuée avec succès. Une notification a été envoyée.").build();
            } else {
                
            	Salle alternative = reservationService.proposerAlternative(
                        demande.getTypeSeance(),
                        dateDebut,
                        dateFin
                );

                if (alternative != null) {
                   
                    String message = String.format(
                            "Réservation impossible pour la salle demandée. " +
                            "Proposition alternative : Salle %s (ID: %d) est disponible.",
                            alternative.getNom(), alternative.getId()
                    );
                    return Response.status(Response.Status.CONFLICT)
                            .entity(message).build();
                } else {
                    
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Réservation impossible et aucune alternative disponible.").build();
                }

            }
        } catch (Exception e) {
            // Gestion des erreurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la demande de réservation : " + e.getMessage()).build();
        }
    }
    
	
	  @Path("/ListReservation/{id_u}")  
	  @GET 
	  public List<ReservationDto> getAllReservations(@PathParam("id_u") Long id_u) {
		  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	        List<Reservation> reservations = reservationService.consulterReservationsParUtilisateur(id_u);

	        if (reservations == null || reservations.isEmpty()) {
	            System.out.println("Aucune réservation trouvée pour l'utilisateur avec l'ID : " + id_u);
	            return new ArrayList<>();
	        }

	        List<ReservationDto> reservationDTOs = new ArrayList<>();
	        for (Reservation reservation : reservations) {
	        	ReservationDto dto = new ReservationDto(
	        			reservation.getSalle().getNom(),
	        			reservation.getTypeSeance(),
	            		formatter.format( reservation.getDateDebut()),
	            		formatter.format(reservation.getDateFin()),
	            		reservation.getUtilisateur().getId(),
	            		reservation.getFiliere().getId(),
	            		reservation.getSalle().getId(),
	            		reservation.getId()
	            		);
	            reservationDTOs.add(dto);
	        }

	        return reservationDTOs;
	    }
	  
	  
	  @Path("/ListAllReservations")  
	  @GET 
	  public List<ResrvationShow> getAllReservations() {
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	      List<Reservation> reservations = reservationService.consulterToutesLesReservations();

	      if (reservations == null || reservations.isEmpty()) {
	          System.out.println("Aucune réservation trouvée.");
	          return new ArrayList<>();
	      }

	      List<ResrvationShow> reservationDTOs = new ArrayList<>();
	      for (Reservation reservation : reservations) {
	         
	          String nomUtilisateur = reservation.getUtilisateur() != null ? reservation.getUtilisateur().getNom() : "Inconnu";
	          String nomFiliere = reservation.getFiliere() != null ? reservation.getFiliere().getNom() : "Inconnue";
	          String nomSalle = reservation.getSalle() != null ? reservation.getSalle().getNom() : "Inconnue";

	          // Créer un DTO avec les noms au lieu des IDs
	          ResrvationShow dto = new ResrvationShow(
	              reservation.getTypeSeance(),
	              formatter.format(reservation.getDateDebut()),
	              formatter.format(reservation.getDateFin()),
	              nomUtilisateur,  // Nom du prof
	              nomFiliere,      // Nom de la filière
	              nomSalle,        // Nom de la salle
	              reservation.getId()
	          );
	          reservationDTOs.add(dto);
	      }

	      return reservationDTOs;
	  }

	  
	  
	  
	  
	  
	  
	
    //KHdamaaaa
   
   
    @Path("/libererSalleDefinitive/{idReservation}")
    @POST
    public Response libererSalleDefinitive(@PathParam("idReservation") long idReservation) {
        try {
        	Reservation res = reservationService.findReservation(idReservation);
        	System.out.println("reservation trouver");
            String message=salleService.libererSalleDefinitivement(res.getSalle().getId());
            String msg= "Votre liberation de la salle "+res.getSalle().getNom()+"  a été confirmée.";
            notificationService.envoyerNotification(res.getUtilisateur().getId(), msg);
            return Response.ok(message).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la libération définitive : " + e.getMessage()).build();
        }
    }

    //KHdamaaaa
	  @POST	  
	  @Path("/libererSalleExceptionnelle/{idSalle}")
	  public Response libererSalleExceptionnellement(
	            @PathParam("idSalle") long idSalle,
	            @QueryParam("dateFinLiberation") String dateFinLiberationStr) {

	        try {
	           
	            LocalDate dateFinLiberation = LocalDate.parse(dateFinLiberationStr);
	            
	            String result = salleService.libererSalleExceptionnellement(idSalle, dateFinLiberation);

	            return Response.ok(result).build();
	        } catch (Exception e) {
	            return Response.status(Response.Status.BAD_REQUEST)
	                    .entity("Erreur dans le traitement de la demande : " + e.getMessage()).build();
	        }
	    }
	
	

    
   
    
    
    
    
}