package models;

public class Spectateur {
    private int spectateurId;
    private String nom;
    private String email;

    public Spectateur(int spectateurId, String nom, String email){
        this.spectateurId = spectateurId;
        this.nom = nom;
        this.email = email;
    }

    public void afficherSpectateur(){
        System.out.println("ID: " + this.spectateurId + ", nom : " + this.nom + ", email : " + this.email);
    }

    public int getSpectateurId() {
        return spectateurId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
