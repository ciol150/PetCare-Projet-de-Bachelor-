package com.loicdavid.testprojetbachelor.View.Fragment;

import com.loicdavid.testprojetbachelor.Room.*;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;
import com.loicdavid.testprojetbachelor.ViewModel.RappelListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class pageRappelFragment extends Fragment {


    Button boutonRetour;
    Button boutonFiltre;
    Button boutonAjout;


    ScrollView scrollView;

    public pageRappelFragment() {
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
        //inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_page_rappel, null);

        //instancier BDD
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();

        //instancier DAO (permet manipuler données)
        RappelDAO rappelDAO = db.rappelDAO();

        //trouver tous les rappels de l'animal actuellement sélectionné
        List<Rappel> rappelList = rappelDAO.findAnimalRappels(AnimalActuelViewModel.getInstance().getId());
        RappelListViewModel.getInstance().setRappelList(rappelList);

        RecyclerView recyclerView = v.findViewById(R.id.frag_rappels_recyclerview);
        final RappelListAdapter adapter = new RappelListAdapter(new RappelListAdapter.WordDiff(), getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.submitList(rappelList);

        // Display the view
        return v;
        //return inflater.inflate(R.layout.fragment_page_rappel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boutonRetour = view.findViewById(R.id.frag_rappels_retour);
        boutonFiltre = view.findViewById(R.id.frag_rappels_filtre);
        boutonAjout = view.findViewById(R.id.frag_rappels_add);

        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("pageRappel").replace(R.id.main_activity_fragment_container, new AjoutRappelFragment()).commit();
            }
        });



        //List<Animal> animalList = animalDAO.getAll();



        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

}