package com.loicdavid.testprojetbachelor.Room;

import android.bluetooth.le.ScanSettings;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Animal")
public class Animal {

    //id pour reconnaitre l'animal
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_animal")
    public int id_animal;

    //nom de l'animal
    @ColumnInfo(name = "animal_name")
    public String animal_name;

    //lien vers l'image de profil de l'animal
    @ColumnInfo(name = "image_uri")
    public String image_uri;

    //sexe de l'animal
    @ColumnInfo(name = "sexe")
    public String sexe;

    //espece de l'animal
    @ColumnInfo(name = "espece")
    public String espece;

    //date de naissance de l'animal pour calculer l'âge de l'anniversaire
    @ColumnInfo(name = "date_naissance")
    public String date_naissance;

    @ColumnInfo(name = "is_sterilized")
    public Boolean is_sterilized;

    public Animal() {
    }


    public Animal(String animal_name, String sexe, String espece, String date_naissance, Boolean is_sterilized) {
        System.out.println("isOK - Animal.java");
        this.id_animal = id_animal;
        this.animal_name = animal_name;

        this.sexe = sexe;
        this.espece = espece;
        this.date_naissance = date_naissance;
        this.is_sterilized = is_sterilized;

        switch (espece) {
            case ("chat"):
                this.image_uri = "silhouette_chat";
                break;
            case ("chien"):
                this.image_uri = "R.drawable.silhouette_chien";
                break;
            case ("cochon d'inde"):
                this.image_uri = "R.drawable.silhouette_cochon_inde";
                break;
            case ("hamster"):
                this.image_uri = "R.drawable.silhouette_hamster";
                break;
            case ("oiseau"):
                this.image_uri = "R.drawable.silhouette_oiseau";
                break;
            default:
                this.image_uri = "R.drawable.ic_launcher_foreground";
                break;
        }
    }

    public int getId_animal() {
        return id_animal;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id_animal=" + id_animal +
                ", animal_name='" + animal_name + '\'' +
                ", image_uri='" + image_uri + '\'' +
                ", sexe='" + sexe + '\'' +
                ", espece='" + espece + '\'' +
                ", date_naissance=" + date_naissance +
                '}';
    }
}
