package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notification")
public class Notification implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;

    //Notification peut etre associé a plusieurs utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur destinataire;

    // Constructeurs
    //Le constructeur sans parametres
    public Notification() {
    }
    //Le constructeur avec parametres
    public Notification(String mes, Utilisateur deti) {
        this.message = mes;
        this.destinataire = deti;
    }

    // Getters et setters
    //Le getter et setter d'id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Le getter et setter de message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //Le getter et setter de destinataire
    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }
}
