package EjbSession;

	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

	
import javax.ejb.Remote;

import entities.*;
		@Remote
public interface ReservationServiceRemote {
		

		
			 boolean addReservation(String TypeSeance,Date dateDebut, Date dateFin, long idFiliere,long idSalle, long idProf);
			 List<Reservation> consulterReservationsParUtilisateur(long  id_utilisateur);
			  List<Reservation> consulterToutesLesReservations();
			  Salle proposerAlternative(String TypeSeance,Date dateDebut, Date dateFin);
			  Reservation findReservation(long id);

		

	}