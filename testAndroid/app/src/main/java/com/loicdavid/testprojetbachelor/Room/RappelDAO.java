package com.loicdavid.testprojetbachelor.Room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RappelDAO {


    @Query("SELECT * FROM Rappel")
    List<Rappel> getAll();

    @Query("SELECT * FROM Rappel WHERE id_animal = :idAnimal")
    List<Rappel> findAnimalRappels(int idAnimal);

    @Query("SELECT * FROM Rappel WHERE id_animal = :idAnimal AND notif_active == 1")
    List<Rappel> findAnimalRappelsNotif(int idAnimal);


    @Query("SELECT * FROM Rappel WHERE id_animal = :idAnimal AND type_rappel LIKE 'Vaccins' ")
    List<Rappel> findAnimalRappelsVaccins(int idAnimal);

    @Query("SELECT * FROM Rappel WHERE id_animal = :idAnimal AND type_rappel LIKE 'Traitements' ")
    List<Rappel> findAnimalRappelsTraitements(int idAnimal);

    @Query("SELECT * FROM Rappel WHERE id_animal = :idAnimal AND type_rappel LIKE 'Visites Médicales' ")
    List<Rappel> findAnimalRappelsRDV(int idAnimal);

    @Insert
    void insertAll(Rappel... rappels);

    @Update
    void updateAll(Rappel... rappels);

    @Delete
    void delete(Rappel rappel);

}
