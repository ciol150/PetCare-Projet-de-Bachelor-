package com.loicdavid.testprojetbachelor.View.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.RadioButton;
import android.widget.TimePicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.Animal;
import com.loicdavid.testprojetbachelor.Room.AnimalDAO;
import com.loicdavid.testprojetbachelor.Room.AppDatabase;
import com.loicdavid.testprojetbachelor.Room.Poids;
import com.loicdavid.testprojetbachelor.Room.PoidsDAO;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalCreationViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

public class AnimalCreationFragment extends Fragment {

    Button buttonSave;
    Button retour;
    CheckBox sterilise;
    EditText editTextName;
    EditText editTextDate;
    EditText editTextPoids;
    DatePickerDialog pickerDate;
    private static FileWriter file;
    String genre;
    String espece;
    View.OnClickListener clickListenerGenre = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Is the button now checked?
            boolean checked = ((RadioButton) v).isChecked();

            // Check which radio button was clicked
            switch(v.getId()) {
                case R.id.frag_crea_animal_radio_femelle:
                    if (checked)
                        // animal femelle
                        genre = "femelle";
                    break;
                case R.id.frag_crea_animal_radio_male:
                    if (checked)
                        // animal male
                        genre = "male";
                    break;
            }
        }
    };


    View.OnClickListener clickListenerEspece = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Is the button now checked?
            boolean checked = ((RadioButton) v).isChecked();

            // Check which radio button was clicked
            switch(v.getId()) {
                case R.id.frag_crea_animal_radio_chat:
                    if (checked)
                        espece = "chat";

                    break;
                case R.id.frag_crea_animal_radio_chien:
                    if (checked)
                        espece = "chien";

                    break;
                case R.id.frag_crea_animal_radio_oiseau:
                    if (checked)
                        espece = "oiseau";

                    break;
                case R.id.frag_crea_animal_radio_hamster:
                    if (checked)
                        espece = "hamster";

                    break;
                case R.id.frag_crea_animal_radio_cochon_inde:
                    if (checked)
                        espece = "cochon d'inde";

                    break;
            }
        }
    };
    public AnimalCreationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_creation_animal, container, false);

        rootview.findViewById(R.id.frag_crea_animal_radio_male).setOnClickListener(clickListenerGenre);
        rootview.findViewById(R.id.frag_crea_animal_radio_femelle).setOnClickListener(clickListenerGenre);

        rootview.findViewById(R.id.frag_crea_animal_radio_chat).setOnClickListener(clickListenerEspece);
        rootview.findViewById(R.id.frag_crea_animal_radio_chien).setOnClickListener(clickListenerEspece);
        rootview.findViewById(R.id.frag_crea_animal_radio_oiseau).setOnClickListener(clickListenerEspece);
        rootview.findViewById(R.id.frag_crea_animal_radio_hamster).setOnClickListener(clickListenerEspece);
        rootview.findViewById(R.id.frag_crea_animal_radio_cochon_inde).setOnClickListener(clickListenerEspece);



        return rootview;
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialiser contenu XML
        buttonSave = view.findViewById(R.id.frag_crea_animal_saveButton);
        retour = view.findViewById(R.id.frag_crea_animal_retour);
        sterilise = view.findViewById(R.id.frag_crea_animal_sterilise);
        editTextName = view.findViewById(R.id.frag_crea_animal_editText_nom);
        editTextDate = view.findViewById(R.id.frag_crea_animal_editText_dateNaissance);
        editTextPoids = view.findViewById(R.id.frag_crea_animal_editText_poids);
        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextPoids.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextPoids.setKeyListener(DigitsKeyListener.getInstance("01234567890."));



        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });



        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instancier BDD
                AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();

                //instancier DAO (permet manipuler données)
                AnimalDAO animalDAO = db.animalDAO();
                PoidsDAO poidsDAO = db.poidsDAO();

                //créer animal avec infos
                Animal nouvelAnimal = new Animal(
                        editTextName.getText().toString(),
                        genre,
                        espece,
                        editTextDate.getText().toString(),
                        sterilise.isChecked());

                //Ajouter l'animal
                animalDAO.insertAll(nouvelAnimal);

                Poids nouveauPoids= new Poids(
                        animalDAO.findLatestAnimal().id_animal,
                        Float.parseFloat(editTextPoids.getText().toString()),
                        Calendar.getInstance().getTime().toString());


                //Ajouter son poids
                poidsDAO.insertAll(nouveauPoids);


                // NE MARCHE PLUS
                //getParentFragmentManager()
                getFragmentManager().beginTransaction().addToBackStack("creationAnimal").replace(R.id.main_activity_fragment_container, new ProfilAnimalFragment()).commit();
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

}

