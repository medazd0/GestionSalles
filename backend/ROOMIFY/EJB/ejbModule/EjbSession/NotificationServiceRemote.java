package EjbSession;

	import java.util.List;
	import entities.*;
	import javax.ejb.Remote;
	@Remote

public interface NotificationServiceRemote {
	
		void envoyerNotification(long utilisateur, String message);
	    List<Notification> consulterNotificationsParUtilisateur(Utilisateur utilisateur);
	    void supprimerNotification(long id);
	}
