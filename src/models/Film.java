package models;

public class Film {
    private int filmId;
    private String titre;
    private String duree;
    private String categorie;

    public Film(int filmId, String titre, String duree, String categorie){
            this.filmId = filmId;
            this.titre = titre;
            this.duree = duree;
            this.categorie = categorie;
    }
    public void afficherFilm(){
        System.out.println("ID: " + filmId + ", Titre : " + titre + ", Duree : " + duree + ", categorie : " + categorie);
    }

    public int getFilmId() {
        return filmId;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
