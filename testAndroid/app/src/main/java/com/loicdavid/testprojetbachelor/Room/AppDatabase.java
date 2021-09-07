package com.loicdavid.testprojetbachelor.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Animal.class, Poids.class, Rappel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AnimalDAO animalDAO();
    public abstract PoidsDAO poidsDAO();
    public abstract RappelDAO rappelDAO();
}

