package com.loicdavid.testprojetbachelor.ViewModel;

import com.loicdavid.testprojetbachelor.Room.Rappel;

public class RappelViewModel {

    private Rappel rappel;

    public Rappel getRappel() {
        return rappel;
    }

    public void setRappel(Rappel rappel) {
        this.rappel = rappel;
    }

    private static RappelViewModel INSTANCE;
    public static RappelViewModel getInstance() {
        if (INSTANCE != null){
            return INSTANCE;
        }else {
            INSTANCE = new RappelViewModel();
            return INSTANCE;
        }
    }
}
