package com.loicdavid.testprojetbachelor.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import static androidx.room.ForeignKey.CASCADE;
import androidx.room.PrimaryKey;

@Entity(tableName = "Poids", foreignKeys = @ForeignKey(entity = Animal.class,
        parentColumns = "id_animal",
        childColumns = "id_animal",
        onDelete = CASCADE,
        onUpdate = CASCADE))
public class Poids {

    //id pour reconnaitre le poids
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_poids")
    public int id_poids;

    //id pour reconnaitre l'animal à qui appartient le poids
    @ColumnInfo(name="id_animal")
    public int id_animal;

    //poids en kg
    @ColumnInfo(name = "poids")
    public float poids;

    //Date de la mesure du poids pour graphe
    @ColumnInfo(name="date_mesure")
    public String date_mesure;

    public Poids(){
    }

    public Poids(int id_animal, float poids, String date_mesure) {
        //this.id_poids = id_poids;
        this.id_animal = id_animal;
        this.poids = poids;
        this.date_mesure = date_mesure;
    }

    public int getId_poids() {
        return id_poids;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getDate_mesure() {
        return date_mesure;
    }

    public void setDate_mesure(String date_mesure) {
        this.date_mesure = date_mesure;
    }
}
