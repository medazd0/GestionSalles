package EjbSession;

import java.util.List;
import javax.ejb.Local;
import entities.*;

@Local
public interface FiliereServiceLocal {

    // Méthode pour ajouter une filière
    void AjouterFiliere(String nom, int effectif);

    // Méthode pour modifier une filière
    void ModifierFiliere(long id, String nouveauNom, int nouvelEffectif);

    // Méthode pour supprimer une filière
    void SupprimerFiliere(long id);

    // Méthode pour lister toutes les filières
    List<Filiere> consulterToutesLesFilieres();
    
    Filiere getfiliereById(Long id);
}
