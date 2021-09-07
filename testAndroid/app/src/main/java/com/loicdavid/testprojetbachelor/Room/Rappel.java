package com.loicdavid.testprojetbachelor.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import static androidx.room.ForeignKey.CASCADE;
import androidx.room.PrimaryKey;

@Entity(tableName = "Rappel", foreignKeys = @ForeignKey(entity = Animal.class,
        parentColumns = "id_animal",
        childColumns = "id_animal",
        onDelete = CASCADE,
        onUpdate = CASCADE))
public class Rappel {

    //id pour reconnaitre le rappel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_rappel")
    public int id_rappel;

    //id pour reconnaitre l'animal à qui appartient le rappel
    @ColumnInfo(name="id_animal")
    public int id_animal;

    //type de rappel                    custom, defaut, vaccin, rdv, traitement
    @ColumnInfo(name = "type_rappel")
    public String type_rappel;

    //Nom du rappel
    @ColumnInfo(name="nom_rappel")
    public String nom_rappel;

    //
    @ColumnInfo(name="description")
    public String description;

    //
    @ColumnInfo(name="date")
    public String date;

    //
    @ColumnInfo(name="heure")
    public String heure;

    //
    @ColumnInfo(name="notif_active")
    public Boolean notif_active;

    // intervalles, nombre par ex: d'heure, de jours, de semaine, de mois
    @ColumnInfo(name="intervalle_nombre")
    public int intervalle_nombre;

    // intervalle, unite (ex: heure, jours, mois etc)
    @ColumnInfo(name="intervalle_unite")
    public String intervalle_unite;

    public Rappel() {
    }

    public Rappel(int id_animal, String type_rappel, String nom_rappel, String description, String date, String heure, Boolean notif_active, int intervalle_nombre, String intervalle_unite) {
        //this.id_rappel = id_rappel;
        this.id_animal = id_animal;
        this.type_rappel = type_rappel;
        this.nom_rappel = nom_rappel;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.notif_active = notif_active;
        this.intervalle_nombre = intervalle_nombre;
        this.intervalle_unite = intervalle_unite;
    }

    public void set_Modification(String nom_rappel, String description, String date, String heure, Boolean notif_active, int intervalle_nombre, String intervalle_unite) {
        //this.id_rappel = id_rappel;
        //this.id_animal = id_animal;
        //this.type_rappel = type_rappel;
        this.nom_rappel = nom_rappel;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.notif_active = notif_active;
        this.intervalle_nombre = intervalle_nombre;
        this.intervalle_unite = intervalle_unite;
    }

    public int getId_rappel() {
        return id_rappel;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getType_rappel() {
        return type_rappel;
    }

    public void setType_rappel(String type_rappel) {
        this.type_rappel = type_rappel;
    }

    public String getNom_rappel() {
        return nom_rappel;
    }

    public void setNom_rappel(String nom_rappel) {
        this.nom_rappel = nom_rappel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Boolean getNotif_active() {
        return notif_active;
    }

    public void setNotif_active(Boolean notif_active) {
        this.notif_active = notif_active;
    }

    public int getIntervalle_nombre() {
        return intervalle_nombre;
    }

    public void setIntervalle_nombre(int intervalle_nombre) {
        this.intervalle_nombre = intervalle_nombre;
    }

    public String getIntervalle_unite() {
        return intervalle_unite;
    }

    public void setIntervalle_unite(String intervalle_unite) {
        this.intervalle_unite = intervalle_unite;
    }
}
