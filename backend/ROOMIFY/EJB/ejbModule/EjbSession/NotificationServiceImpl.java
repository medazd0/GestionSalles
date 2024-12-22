package EjbSession;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.*;
@Stateless

public class NotificationServiceImpl implements NotificationServiceLocal, NotificationServiceRemote {
	@PersistenceContext
    private EntityManager em;

	@Override
	public void envoyerNotification(long idUtilisateur, String message) {

	    Utilisateur utilisateur = em.find(Utilisateur.class, idUtilisateur);
	    
	    if (utilisateur != null) {
	        
	        Notification notification = new Notification();
	        notification.setMessage(message);
	        notification.setDestinataire(utilisateur);
	     
	        em.persist(notification);
	    } else {
	       
	        throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + idUtilisateur);
	    }
	}
	@Override
	public List<Notification> consulterNotificationsParUtilisateur(Utilisateur utilisateur) {
	    return utilisateur.getNotifications();
	}


	@Override
	public void supprimerNotification(long id) {
	    // Trouver la notification par son ID
	    Notification notificationASupprimer = em.find(Notification.class, id);

	    if (notificationASupprimer != null) {
	        // Supprimer la notification si elle existe
	    	em.remove(notificationASupprimer);
	    }else {
	        // Si la notification n'existe pas, afficher un message
	        System.out.println("Notification non trouvée avec l'ID : " + id);
	    }
	}


}
