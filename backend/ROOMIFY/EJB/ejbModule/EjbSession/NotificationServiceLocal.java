package EjbSession;
import java.util.List;
import entities.*;
import javax.ejb.Local;
@Local



public interface NotificationServiceLocal {
	void envoyerNotification(long utilisateur, String message);
    List<Notification> consulterNotificationsParUtilisateur(Utilisateur utilisateur);
    void supprimerNotification(long id);
}
