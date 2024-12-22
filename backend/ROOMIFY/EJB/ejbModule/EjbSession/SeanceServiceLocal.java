package EjbSession;

import java.util.List;
import DTOS.seance.SeanceRequest;
import entities.Seance;

import javax.ejb.Local;

@Local
public interface SeanceServiceLocal {

	 void ajouterSeance(Seance seance);

    void modifierSeance(long id, SeanceRequest seanceRequest);

    void supprimerSeance(long id);

    Seance trouverSeance(long id);

    List<Seance> listerSeances();

	List<Seance> getSeancesByFiliere(Long idFiliere);
}
