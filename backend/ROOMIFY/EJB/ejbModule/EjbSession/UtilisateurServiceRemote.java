package EjbSession;


import java.util.List;

import javax.ejb.Remote;

import entities.Utilisateur;
@Remote

public interface UtilisateurServiceRemote {
void ajouterUtilisateur(String nom, String email, String motDePasse, String role);
    
    Utilisateur trouverUtilisateur(long id);

    Utilisateur authentifier(String email, String motDePasse);

    void mettreAJourUtilisateur(long id, String nom, String email, String motDePasse, String role);

    
    void supprimerUtilisateur(long id);
    public Utilisateur getUserByEmail(String email);
    List<Utilisateur> getProfs();

}
