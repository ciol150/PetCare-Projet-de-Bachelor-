package com.loicdavid.testprojetbachelor.View.Fragment;

import org.junit.Test;

import static org.junit.Assert.*;

public class InfoAnimalFragmentTest {

    @Test
    public void getAge() {
        //Date du futur
        assertEquals("Date de naissance invalide (-x an)", InfoAnimalFragment.getAge("02/09/2022"));
        //Date d'aujourd'hui
        assertEquals("0 jours", InfoAnimalFragment.getAge("02/09/2021"));
        //Date d'hier
        assertEquals("1 jours", InfoAnimalFragment.getAge("01/09/2021"));
        //Date d'il y a 2 semaines
        assertEquals("14 jours", InfoAnimalFragment.getAge("19/08/2021"));
        //Date d'il y a 11 mois
        assertEquals("11 mois", InfoAnimalFragment.getAge("02/10/2020"));
        //Date d'il y a 2 ans
        assertEquals("2 ans", InfoAnimalFragment.getAge("02/09/2019"));

        System.out.println("Test mois");
        assertEquals("1 mois", InfoAnimalFragment.getAge("01/08/2021"));


        //Date d'il y a 8 mois
        assertEquals("10 mois", InfoAnimalFragment.getAge("02/11/2020"));
        //Date d'il y a 6 mois
        assertEquals("6 mois", InfoAnimalFragment.getAge("02/03/2021"));
        //Date d'il y a 364 mois
        assertEquals("11 mois", InfoAnimalFragment.getAge("03/09/2020"));

    }
}