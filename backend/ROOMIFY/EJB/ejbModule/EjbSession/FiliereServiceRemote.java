package EjbSession;

import java.util.List;

import javax.ejb.Remote;

import entities.Filiere;

@Remote
public interface FiliereServiceRemote {

	public void AjouterFiliere(String nom,int effectif);
	
	public void ModifierFiliere(long id, String nouveauNom, int nouvelEffectif);
	
	public void SupprimerFiliere(long id);
	
	List<Filiere> consulterToutesLesFilieres();
	Filiere getfiliereById(Long id);
	
}
