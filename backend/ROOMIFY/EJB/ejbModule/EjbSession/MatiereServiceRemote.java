package EjbSession;

import java.util.List;


import javax.ejb.Remote;

import entities.*;
@Remote

public interface MatiereServiceRemote {
	 void ajouterMatiere(String nom, int chargeHoraire ,Filiere filiere) throws Exception;
	 void modifierMatiere(long id, String Nom, int ChargeHoraire);
	 void supprimerMatiere(long id);
	 List<Matiere> consulterMatieresParFiliere(Filiere filiere);
	 Matiere getMatiere(long id);
}
