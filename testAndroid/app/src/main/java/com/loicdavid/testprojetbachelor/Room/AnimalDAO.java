package com.loicdavid.testprojetbachelor.Room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AnimalDAO {
    @Query("SELECT * FROM Animal")
    List<Animal> getAll();

    @Query("SELECT * FROM Animal WHERE animal_name LIKE :first LIMIT 1")
    Animal findByAnimalName(String first);

    @Query("SELECT * FROM Animal WHERE id_animal = :id LIMIT 1")
    Animal findByAnimalId(int id);

    @Query("SELECT * FROM Animal ORDER BY id_animal DESC LIMIT 1")
    Animal findLatestAnimal();

    @Query("SELECT id_animal, date_naissance FROM Animal WHERE id_animal LIKE :id LIMIT 1")
    Animal getAnimalBirthday(int id);

    @Query("SELECT COUNT(*) AS count FROM Animal")
    int isAnimalEmpty();

    @Insert
    void insertAll(Animal... animals);

    @Update
    void updateAll(Animal... animals);

    @Delete
    void delete(Animal animal);

}
