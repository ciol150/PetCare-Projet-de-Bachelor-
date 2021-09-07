package com.loicdavid.testprojetbachelor.View.Fragment;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loicdavid.testprojetbachelor.R;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.loicdavid.testprojetbachelor.Room.AppDatabase;
import com.loicdavid.testprojetbachelor.Room.Poids;
import com.loicdavid.testprojetbachelor.Room.PoidsDAO;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;


public class GraphPoidsFragment extends Fragment {


    Button boutonRetour;
    Button boutonAjoutValeur;

    GraphView graphView;

    EditText editTextNouveauPoids;

    AppDatabase db;
    DataPoint[] dataPoints;

    public GraphPoidsFragment() {
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
        return inflater.inflate(R.layout.fragment_graph_poids, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();


        boutonRetour = view.findViewById(R.id.frag_graph_poids_retour);
        boutonAjoutValeur = view.findViewById(R.id.frag_graph_poids_ajout_valeur);

        graphView = view.findViewById(R.id.frag_graph_poids_graph);

        editTextNouveauPoids = view.findViewById(R.id.frag_graph_poids_editText_poids);

        editTextNouveauPoids.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editTextNouveauPoids.setKeyListener(DigitsKeyListener.getInstance("01234567890."));

        dataPoints = new DataPoint[0];

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        boutonAjoutValeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instancier BDD
                PoidsDAO poidsDAO = db.poidsDAO();
                Poids nouveauPoids= new Poids(
                        AnimalActuelViewModel.getInstance().getId(),
                        Float.parseFloat(editTextNouveauPoids.getText().toString()),
                        Calendar.getInstance().getTime().toString()
                );
                poidsDAO.insertAll(nouveauPoids);


                fillGraph();

                //getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new GraphPoidsFragment()).commit();
            }
        });


        fillGraph();

    }

    public void fillGraph(){
        graphView.removeAllSeries();//make sure graphView is empty

        PoidsDAO poidsDAO = db.poidsDAO();
        List<Poids> poidsList = poidsDAO.findAnimalWeight(AnimalActuelViewModel.getInstance().getId());

        if(poidsList.size() > 0){
            dataPoints = new DataPoint[poidsList.size()];
            int j_j = 0;
            float maxWeight = 0;
            for(int i = poidsList.size()-1; i >= 0; i--){
                DataPoint dataPoint = new DataPoint( j_j, poidsList.get(i).poids);
                dataPoints[j_j] = dataPoint;
                if(maxWeight < poidsList.get(i).poids){
                    maxWeight = poidsList.get(i).poids;
                }
                j_j++;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

            graphView.addSeries(series);
            graphView.getViewport().setMinX(0);
            graphView.getViewport().setMaxX(poidsList.size());
            graphView.getViewport().setMinY(0);
            graphView.getViewport().setMaxY(maxWeight+1);
            graphView.getViewport().setYAxisBoundsManual(true);
            graphView.getViewport().setXAxisBoundsManual(true);
        }else{
            Toast toast = Toast.makeText(getContext(), "Aucun poids enregistré", Toast.LENGTH_LONG);
            toast.show();
        }
    }



}