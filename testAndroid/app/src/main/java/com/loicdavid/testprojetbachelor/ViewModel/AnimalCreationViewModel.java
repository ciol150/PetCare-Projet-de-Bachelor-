package com.loicdavid.testprojetbachelor.ViewModel;

public class AnimalCreationViewModel {

    private String profilData;



    public void setProfilData(String profilData){
        this.profilData = profilData;
    }

    public String getProfilData(){
        return this.profilData;
    }

    private static AnimalCreationViewModel INSTANCE;
    public static AnimalCreationViewModel getInstance() {
        if (INSTANCE != null){
            return INSTANCE;
        }else {
            INSTANCE = new AnimalCreationViewModel();
            return INSTANCE;
        }
    }
}
