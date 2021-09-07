package com.loicdavid.testprojetbachelor.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.Animal;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class InfoAnimalFragment extends Fragment {

    Button BoutonRetour;
    Button BoutonPagePoids;
    TextView TextViewNom;
    TextView TextViewEspece;
    TextView TextViewGenre;
    TextView TextViewPoids;
    TextView TextViewAge;




    public InfoAnimalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_animal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        BoutonRetour = view.findViewById(R.id.frag_info_animal_retour);
        BoutonPagePoids = view.findViewById(R.id.frag_info_animal_bouton_poids);

        TextViewNom = view.findViewById(R.id.frag_info_animal_nom);
        TextViewAge = view.findViewById(R.id.frag_info_animal_age);
        TextViewEspece = view.findViewById(R.id.frag_info_animal_espece);
        TextViewGenre = view.findViewById(R.id.frag_info_animal_genre);
        TextViewPoids = view.findViewById(R.id.frag_info_animal_poids);



        Animal animal = AnimalActuelViewModel.getInstance().getAnimal();

        TextViewEspece.setText(animal.espece);
        TextViewPoids.setText("poids");




        TextViewAge.setText(String.valueOf(getAge(animal.getDate_naissance())));


        TextViewGenre.setText(animal.sexe);
        TextViewNom.setText(animal.animal_name);

        BoutonPagePoids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("infoGeneralesAnimal").replace(R.id.main_activity_fragment_container, new GraphPoidsFragment()).commit();
            }
        });

        BoutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    public static String getAge(String date_naissance) {

        //Formatter date de naissance
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        formatter = formatter.withLocale(Locale.FRANCE);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate localDateNaissance = LocalDate.parse(date_naissance, formatter);

        int _year = localDateNaissance.getYear();
        int _month = localDateNaissance.getMonthValue();
        int _day = localDateNaissance.getDayOfMonth();

        int y, m, d, ageAn, ageMois, ageJour;

        //get Gurrent date
        y = Calendar.getInstance().get(Calendar.YEAR);
        m = Calendar.getInstance().get(Calendar.MONTH)+1; //le mois est en retards de 1
        d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        ageAn = y - _year; //calcul nombre d'année depuis naissance

        System.out.println(ageAn);

        if ((m < _month)  //si le mois de son anniv n'est pas passé, enlevé 1 an
                || ((m == _month)) && (d < _day)) { //si son jour d'anniv n'est pas encore passé, enlevé 1 an
            ageAn--;
        }
        System.out.println(ageAn);

        if(ageAn < 0){ //si a un age négatif
            return "Date de naissance invalide (-x an)";
        }else if(ageAn == 0){ //si a moins d'1 an, afficher les mois
            if (m < _month || ( m == _month && d < _day) ){
                ageMois = 12 + m - _month;
            }else{
                ageMois = m - _month;
            }
            if (d < _day) { //si son jour d'anniv n'est pas encore passé, enlevé 1 mois
                ageMois--;
            }

            if(m < _month && y < _year){//si a un age négatif
                return "Date de naissance invalide (-x mois)";
            }else if(ageMois == 0){ //si a moins de 1 mois, donner age en jours


                if(m == _month){ //s'il est né ce mois-ci
                    ageJour = (d - _day);//on considère 1 mois = 30 jours, on soustrait
                }else{//né mois précédent
                    //doit savoir nombre de jour dans mois naissance pour calculer âge en jours
                    if(_month == 2){//février
                        ageJour = 28+(d - _day);
                    }else if (_month == 1 || _month == 3 || _month == 5 || _month == 7 || _month == 8 || _month == 10 || _month == 12){
                        ageJour = 31 + (d - _day);
                    }else{
                        ageJour = 30 + (d - _day);
                    }
                }
                return (String.valueOf(ageJour) + " jours");
            }else{//si a au moins 1 mois
                return (String.valueOf(ageMois) + " mois");
            }

        }else{//si a au moins 1 an
            if (ageAn == 1){
                return (String.valueOf(ageAn) + " an"); //1 an au singulier
            }else{
                return (String.valueOf(ageAn) + " ans"); //x ans au pluriel
            }
        }
    }
}
