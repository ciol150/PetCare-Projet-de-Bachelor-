package com.loicdavid.testprojetbachelor.View.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.Animal;
import com.loicdavid.testprojetbachelor.Room.AnimalDAO;
import com.loicdavid.testprojetbachelor.Room.AppDatabase;
import com.loicdavid.testprojetbachelor.Room.Rappel;
import com.loicdavid.testprojetbachelor.Room.RappelDAO;
import com.loicdavid.testprojetbachelor.View.Activity.AlarmReceiver;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalCreationViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class ProfilAnimalFragment extends Fragment {

    private static final int PICK_IMAGE = 100;

    Button boutonAjout;
    Button animalSuivant;
    Button animalPrecedent;

    Button boutonPageRappel;
    Button boutonPageInfosAnimal;
    Button boutonPageCarnetSanté;
    Button boutonImage;

    TextView nomAnimal;
    ImageView imageAnimal;

    AppDatabase db;
    AnimalDAO animalDAO;
    List<Animal> animalList;


    int animalActuel;

    public ProfilAnimalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boutonAjout = view.findViewById(R.id.frag_profil_animal_ajout_animal);
        boutonPageRappel = view.findViewById(R.id.frag_profil_animal_open_rappel);
        boutonPageInfosAnimal = view.findViewById(R.id.frag_profil_animal_open_info_animal);
        boutonPageCarnetSanté = view.findViewById(R.id.frag_profil_animal_open_dossier_medical);
        boutonImage = view.findViewById(R.id.frag_profil_animal_bouton_image);

        animalPrecedent = view.findViewById(R.id.frag_profil_animal_precedent);
        animalSuivant = view.findViewById(R.id.frag_profil_animal_suivant);
        nomAnimal = view.findViewById(R.id.frag_profil_animal_textView);
        imageAnimal = view.findViewById(R.id.frag_profil_animal_imageView);
        animalActuel = 0;

/*
        Date = view.findViewById(R.id.frag_profil_animal_editText_date);
        Heure = view.findViewById(R.id.frag_profil_animal_editText_heure);
        Date.setInputType(InputType.TYPE_NULL);
        Heure.setInputType(InputType.TYPE_NULL);
*/

        //instancier BDD
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();

        //instancier DAO (permet manipuler données)
        animalDAO = db.animalDAO();



        animalList = animalDAO.getAll();


        nomAnimal.setText(animalList.get(animalActuel).animal_name);

        imageAnimal.setImageURI(Uri.parse(animalList.get(animalActuel).image_uri));



        AnimalActuelViewModel.getInstance().setAnimal(animalList.get(animalActuel));

        animalPrecedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loop if below 0
                animalActuel = (animalActuel-1);
                if(animalActuel < 0){
                    animalActuel = animalList.size()-1;
                }
                nomAnimal.setText(animalList.get(animalActuel).animal_name);
                imageAnimal.setImageURI(Uri.parse(animalList.get(animalActuel).image_uri));
                AnimalActuelViewModel.getInstance().setAnimal(animalList.get(animalActuel));
            }
        });

        animalSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loop if above array length
                animalActuel = (animalActuel+1)%animalList.size();
                nomAnimal.setText(animalList.get(animalActuel).animal_name);
                imageAnimal.setImageURI(Uri.parse(animalList.get(animalActuel).image_uri));

                AnimalActuelViewModel.getInstance().setAnimal(animalList.get(animalActuel));
            }
        });

        //CODE POUR CHANGER L'IMAGE D'UN ANIMAL

        boutonImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent gallery =
                       new Intent(Intent.ACTION_PICK,
                               android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
               gallery.setType("image/*");
               startActivityForResult(gallery, PICK_IMAGE);
           }


        });




        boutonAjout.setOnClickListener(new View.OnClickListener() { //passer a la page AnimalCreation
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("profilAnimal").replace(R.id.main_activity_fragment_container, new AnimalCreationFragment()).commit();
            }
        });

        boutonPageRappel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("profilAnimal").replace(R.id.main_activity_fragment_container, new pageRappelFragment()).commit();
            }
        });
        boutonPageInfosAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("profilAnimal").replace(R.id.main_activity_fragment_container, new InfoAnimalFragment()).commit();
            }
        });
        boutonPageCarnetSanté.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("profilAnimal").replace(R.id.main_activity_fragment_container, new CarnetSanteFragment()).commit();
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (data != null){
                String imageUri = data.getDataString();
                imageAnimal.setImageURI(Uri.parse(imageUri));
                animalList.get(animalActuel).setImage_uri(imageUri);
                animalDAO.updateAll(animalList.get(animalActuel));
            }else{
                Toast toast = Toast.makeText(getContext(), "Ajout image annulé", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_animal, container, false);
    }
}