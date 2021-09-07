package com.loicdavid.testprojetbachelor.ViewModel;

import com.loicdavid.testprojetbachelor.Room.Rappel;

import java.util.List;



//Stores the rappelList for an animal
public class RappelListViewModel {
    List<Rappel> rappelList;

    public List<Rappel> getRappelList() {
        return rappelList;
    }

    public void setRappelList(List<Rappel> rappelList) {
        this.rappelList = rappelList;
    }

    private static RappelListViewModel INSTANCE;
    public static RappelListViewModel getInstance() {
        if (INSTANCE != null){
            return INSTANCE;
        }else {
            INSTANCE = new RappelListViewModel();
            return INSTANCE;
        }
    }
}
