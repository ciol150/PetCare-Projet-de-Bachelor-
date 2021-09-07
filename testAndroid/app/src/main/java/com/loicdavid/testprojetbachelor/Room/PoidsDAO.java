package com.loicdavid.testprojetbachelor.Room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface PoidsDAO {

    @Query("SELECT * FROM Poids")
    List<Poids> getAll();

    //les id_poids plus élevés sont forcement plus récent, permettant de prendre les 10 plus récents seulement
    @Query("SELECT * FROM Poids WHERE id_animal = :idAnimal ORDER BY id_poids DESC LIMIT 10")
    List<Poids> findAnimalWeight(int idAnimal);

    @Insert
    void insertAll(Poids... poids);

    @Delete
    void delete(Poids poids);

}
