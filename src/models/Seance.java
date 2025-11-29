package models;

public class Seance {
    private int seanceId;
    private String date;
    private String horaire;
    private float prix;
    private int capaciteMaximale;
    private String salle;
    private int filmId;

    public Seance(int seanceId, String date, String horaire, float prix, int capaciteMaximale, String salle,int filmId){
            this.seanceId = seanceId;
            this.date = date;
            this.horaire = horaire;
            this.prix = prix;
            this.capaciteMaximale = capaciteMaximale;
            this.salle = salle;
            this.filmId = filmId;
    }

    public void afficherSeance(){
        System.out.println("ID: " + this.seanceId + ", date : " + this.date+", horaire :"+ this.horaire+ ", prix : " + this.prix + ", capacite maximale : " + this.capaciteMaximale
                +", salle : "+this.salle+", film id : "+this.filmId);
    }

    public int getSeanceId() {
        return seanceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public void setCapaciteMaximale(int capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
