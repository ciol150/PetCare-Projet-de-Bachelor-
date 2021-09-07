package com.loicdavid.testprojetbachelor.View.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.AppDatabase;
import com.loicdavid.testprojetbachelor.Room.Rappel;
import com.loicdavid.testprojetbachelor.Room.RappelDAO;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;


public class AjouterMedicalRappelFragment extends Fragment {

    Button boutonRetour;
    Button boutonSave;

    EditText editTextNom;
    EditText editTextDate;
    EditText editTextHeure;

    EditText editTextIntervalle_nombre;

    Spinner spinnerIntervalle_unite;
    Spinner spinnerTypeRappel;

    DatePickerDialog pickerDate;
    TimePickerDialog pickerHour;
    CheckBox notificationActive;

    public AjouterMedicalRappelFragment() {
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
        return inflater.inflate(R.layout.fragment_ajouter_medical_rappel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boutonRetour = view.findViewById(R.id.frag_ajout_medical_rappel_retour);
        boutonSave = view.findViewById(R.id.frag_ajout_medical_rappel_save);

        editTextNom = view.findViewById(R.id.frag_ajout_medical_rappel_nom);

        editTextDate = view.findViewById(R.id.frag_ajout_medical_rappel_date);
        editTextHeure = view.findViewById(R.id.frag_ajout_medical_rappel_heure);

        notificationActive = view.findViewById(R.id.frag_ajout_medical_rappel_notification_check);

        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextHeure.setInputType(InputType.TYPE_NULL);

        spinnerIntervalle_unite = view.findViewById(R.id.frag_ajout_medical_rappel_intervalle_unite);
        spinnerTypeRappel = view.findViewById(R.id.frag_ajout_medical_rappel_type);

        editTextIntervalle_nombre = view.findViewById(R.id.frag_ajout_medical_rappel_intervalle_nombre);



        editTextIntervalle_nombre.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextIntervalle_nombre.setKeyListener(DigitsKeyListener.getInstance("01234567890"));


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

        editTextHeure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                pickerHour = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editTextHeure.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickerHour.show();
            }
        });



        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        boutonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instancier BDD
                AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();

                //instancier DAO (permet manipuler données)
                RappelDAO rappelDAO = db.rappelDAO();

                Rappel nouveauRappel = new Rappel(
                        AnimalActuelViewModel.getInstance().getId(),
                        spinnerTypeRappel.getSelectedItem().toString(),
                        editTextNom.getText().toString(),
                        "",
                        editTextDate.getText().toString(),
                        editTextHeure.getText().toString(),
                        notificationActive.isChecked(),
                        Integer.parseInt(editTextIntervalle_nombre.getText().toString()),
                        spinnerIntervalle_unite.getSelectedItem().toString());


                rappelDAO.insertAll(nouveauRappel);

                getFragmentManager().popBackStack();
            }
        });

    }
}