package EjbSession;

import javax.ejb.Local;
import entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Local

public interface SalleServiceLocal {
	//void ajouterSalle(String nom, String localisation, int capacite, String type,boolean disponibilite);
    //void modifierSalle(long id, String Nom, String Localisation, int Capacite, String Type,boolean disponibilite);
    //void supprimerSalle(long id);
    //Salle trouverSalle(long id);
   // List<Salle> consulterSallesDisponibles();
    //void libererSalleDefinitivement(long id);
    //void libererSalleExceptionnellement(long id, LocalDate dateFinLiberation);
    //boolean verifierDisponibiliteSalle(Salle salle, LocalDate date, LocalTime heureDebut, LocalTime heureFin);
	//Salle trouverSalleByname(String nom);


    void reserveSalle(long id);
	void ajouterSalle(String nom, String localisation, int capacite, String type,boolean disponibilite);
    void modifierSalle(long id, String Nom, String Localisation, int Capacite, String Type,boolean disponibilite);
    void supprimerSalle(long id);
    Salle trouverSalle(long id);
    List<Salle> consulterSallesDisponibles();
    String libererSalleDefinitivement(long id);
    String libererSalleExceptionnellement(long id, LocalDate dateFinLiberation);
    boolean verifierDisponibiliteSalle(Salle salle, LocalDate date, LocalTime heureDebut, LocalTime heureFin);
}