package EjbSession;


import java.util.List;

import javax.ejb.Local;
import entities.Utilisateur;

@Local
public interface UtilisateurServiceLocal {
    
    void ajouterUtilisateur(String nom, String email, String motDePasse, String role);
    
    Utilisateur trouverUtilisateur(long id);

    Utilisateur authentifier(String email, String motDePasse);

    void mettreAJourUtilisateur(long id, String nom, String email, String motDePasse, String role);

    
    void supprimerUtilisateur(long id);
    public Utilisateur getUserByEmail(String email) ;
    List<Utilisateur> getProfs();

   
}
