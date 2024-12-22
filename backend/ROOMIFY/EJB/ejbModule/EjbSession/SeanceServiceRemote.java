package EjbSession;

import java.util.List;
import DTOS.seance.SeanceRequest;
import entities.Seance;

import javax.ejb.Remote;

@Remote
public interface SeanceServiceRemote {

	 void ajouterSeance(Seance seance);

    void modifierSeance(long id, SeanceRequest seanceRequest);

    void supprimerSeance(long id);

    Seance trouverSeance(long id);

    List<Seance> listerSeances();
}
