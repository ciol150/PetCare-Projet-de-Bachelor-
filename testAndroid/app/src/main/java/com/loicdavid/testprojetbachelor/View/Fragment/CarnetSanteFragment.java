package com.loicdavid.testprojetbachelor.View.Fragment;

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

import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.AppDatabase;
import com.loicdavid.testprojetbachelor.Room.Rappel;
import com.loicdavid.testprojetbachelor.Room.RappelDAO;
import com.loicdavid.testprojetbachelor.ViewModel.AnimalActuelViewModel;
import com.loicdavid.testprojetbachelor.ViewModel.RappelListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CarnetSanteFragment extends Fragment {

    Button BoutonRetour;
    Button boutonAjouter;

    public CarnetSanteFragment() {
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
        View v = inflater.inflate(R.layout.fragment_carnet_sante, null);

        //instancier BDD
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "MyDB").allowMainThreadQueries().build();
       //        //instancier DAO (permet manipuler données)
        RappelDAO rappelDAO = db.rappelDAO();

        //trouver tous les rappels de l'animal actuellement sélectionné de type RDV
        List<Rappel> rappelList = rappelDAO.findAnimalRappelsRDV(AnimalActuelViewModel.getInstance().getId());
        RappelListViewModel.getInstance().setRappelList(rappelList);
        RecyclerView recyclerViewRDV = v.findViewById(R.id.frag_carnet_sante_recyclerview_visites);
        final RappelListAdapter adapterRDV = new RappelListAdapter(new RappelListAdapter.WordDiff(), getFragmentManager());

        recyclerViewRDV.setAdapter(adapterRDV);
        recyclerViewRDV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterRDV.submitList(rappelList);

        //trouver tous les rappels de l'animal actuellement sélectionné de type traitements
        rappelList = rappelDAO.findAnimalRappelsTraitements(AnimalActuelViewModel.getInstance().getId());
        RappelListViewModel.getInstance().setRappelList(rappelList);
        RecyclerView recyclerViewTraitement = v.findViewById(R.id.frag_carnet_sante_recyclerview_traitements);
        final RappelListAdapter adapterTraitement = new RappelListAdapter(new RappelListAdapter.WordDiff(), getFragmentManager());
        recyclerViewTraitement.setAdapter(adapterTraitement);
        recyclerViewTraitement.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTraitement.submitList(rappelList);

        //trouver tous les rappels de l'animal actuellement sélectionné de type vaccins
        rappelList = rappelDAO.findAnimalRappelsVaccins(AnimalActuelViewModel.getInstance().getId());
        RappelListViewModel.getInstance().setRappelList(rappelList);
        RecyclerView recyclerViewVaccin = v.findViewById(R.id.frag_carnet_sante_recyclerview_vaccins);
        final RappelListAdapter adapterVaccin = new RappelListAdapter(new RappelListAdapter.WordDiff(), getFragmentManager());
        recyclerViewVaccin.setAdapter(adapterVaccin);
        recyclerViewVaccin.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterVaccin.submitList(rappelList);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BoutonRetour = view.findViewById(R.id.frag_carnet_sante_retour);
        boutonAjouter = view.findViewById(R.id.frag_carnet_sante_ajouter);

        BoutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("pageCarnetSante").replace(R.id.main_activity_fragment_container, new AjouterMedicalRappelFragment()).commit();
            }
        });

    }
}