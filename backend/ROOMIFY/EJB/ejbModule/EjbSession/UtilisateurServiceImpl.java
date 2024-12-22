package EjbSession;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Utilisateur;

@Stateless
public class UtilisateurServiceImpl implements UtilisateurServiceRemote, UtilisateurServiceLocal {

	@PersistenceContext
    private EntityManager em;

    
    @Override
    public void ajouterUtilisateur(String nom, String email, String motDePasse, String role) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole(role);
        em.persist(utilisateur);
    }

    // Trouver un utilisateur par ID
    @Override
    public Utilisateur trouverUtilisateur(long id) {
        return em.find(Utilisateur.class, id);
    }

    // Authentifier un utilisateur
    @Override
    public Utilisateur authentifier(String email, String motDePasse) {
        TypedQuery<Utilisateur> query = em.createQuery(
            "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.motDePasse = :motDePasse", 
            Utilisateur.class
        );
        query.setParameter("email", email);
        query.setParameter("motDePasse", motDePasse);
        List<Utilisateur> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Mettre à jour un utilisateur
    @Override
    public void mettreAJourUtilisateur(long id, String nom, String email, String motDePasse, String role) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(motDePasse);
            utilisateur.setRole(role);
            em.merge(utilisateur);
        }
    }

 // Trouver un utilisateur par Email
    @Override
    public Utilisateur getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("L'email ne peut pas être nul");
        }

        TypedQuery<Utilisateur> query = em.createQuery(
            "SELECT u FROM Utilisateur u WHERE u.email = :email", 
            Utilisateur.class
        );
        query.setParameter("email", email);
        List<Utilisateur> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void supprimerUtilisateur(long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            em.remove(utilisateur);
        } else {
            // Vous pouvez ajouter une gestion d'erreur ici si l'utilisateur n'est pas trouvé
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + id + " n'existe pas.");
        }
    }

	@Override
	public List<Utilisateur> getProfs() {
		
		
	    	TypedQuery<Utilisateur> query = em.createQuery("SELECT s FROM Utilisateur s WHERE s.role = 'prof'", Utilisateur.class);
	        return query.getResultList(); 
	    
	}

}
