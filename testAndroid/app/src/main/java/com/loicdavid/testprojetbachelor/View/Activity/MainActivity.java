package com.loicdavid.testprojetbachelor.View.Activity;

import com.loicdavid.testprojetbachelor.Room.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.View.Fragment.AnimalCreationFragment;
import com.loicdavid.testprojetbachelor.View.Fragment.ProfilAnimalFragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private AlarmReceiver receiver = new AlarmReceiver();

    //Launch new page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SAVOIR SI A L'AUTORISATION DE STOCKAGE
        requestPermissions(this);



        //TROUVER SI UN ANIMAL EXISTE
        //instancier BDD
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();

        //instancier DAO (permet manipuler données)
        AnimalDAO animalDAO = db.animalDAO();
        RappelDAO rappelDAO = db.rappelDAO();


        this.registerReceiver(this.receiver, new IntentFilter());

        if (animalDAO.isAnimalEmpty() != 0){//si animaux, programmer rappels, puis lancer app
            System.out.println("Animaux trouvés");
            System.out.println("TEEEESSSTTT ?????");

            for (Animal animal: animalDAO.getAll()) {//pour chaque animaux
                System.out.println("Animal");
                for(Rappel rappel: rappelDAO.findAnimalRappelsNotif(animal.id_animal)){//pour chaque rappel avec notif activée
                    System.out.println("Rappel");



                    Context context = this;
                    alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(context, AlarmReceiver.class); //on définit quelle classe recevra l'alerte

                    //Permet de passer des paramètres à AlarmReceiver qui créera la notif
                    intent.putExtra("nom", 100500);
                    intent.putExtra("titre", 100500);

                    alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                    if (alarmIntent != null && alarmMgr != null) {
                        alarmMgr.cancel(alarmIntent);
                    }


                    // Set the alarm to start at 8:30 a.m.
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 15);
                    calendar.set(Calendar.MINUTE, 28);
                    System.out.println("Lets GO");
                    // setRepeating() lets you specify a precise custom interval--in this case,
                    // 24 hours.
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            1000000, alarmIntent);

                }
            }



            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new ProfilAnimalFragment()).commit();
        }else{//si aucun animal, ouvrir la création d'animaux
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new AnimalCreationFragment()).commit();
        }
    }

    public static void requestPermissions(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    0);
        }

        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                    1);
        }
    }
}