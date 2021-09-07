package com.loicdavid.testprojetbachelor.ViewModel;

import com.loicdavid.testprojetbachelor.Room.Animal;

public class AnimalActuelViewModel {
    private Animal animal;


    public String getName(){
        return this.animal.animal_name;
    }

    public int getId() {
        return this.animal.id_animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }


    private static AnimalActuelViewModel INSTANCE;
    public static AnimalActuelViewModel getInstance() {
        if (INSTANCE != null){
            return INSTANCE;
        }else {
            INSTANCE = new AnimalActuelViewModel();
            return INSTANCE;
        }
    }
}
