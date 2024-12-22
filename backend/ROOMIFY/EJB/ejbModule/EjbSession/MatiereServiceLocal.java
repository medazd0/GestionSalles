package EjbSession;
import java.util.List;

import javax.ejb.Local;

import entities.*;

@Local

public interface MatiereServiceLocal {
	 void ajouterMatiere(String nom, int chargeHoraire ,Filiere filiere) throws Exception;
	    void modifierMatiere(long id, String Nom, int ChargeHoraire);
	    void supprimerMatiere(long id);
	    List<Matiere> consulterMatieresParFiliere(Filiere filiere);
		Matiere getMatiere(long id);
	
	
}
