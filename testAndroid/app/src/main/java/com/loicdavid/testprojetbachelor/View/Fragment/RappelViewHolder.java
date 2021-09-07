package com.loicdavid.testprojetbachelor.View.Fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.loicdavid.testprojetbachelor.R;
import com.loicdavid.testprojetbachelor.Room.Rappel;
import com.loicdavid.testprojetbachelor.ViewModel.RappelViewModel;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class RappelViewHolder extends RecyclerView.ViewHolder {
    private final TextView textViewHour;
    private final TextView textViewTitle;
    private final TextView textViewDescription;
    private final View barreHorizontale;

    private final int ORANGE;
    private final int BLUE;
    private final int GREEN;


    private RappelViewHolder(View itemView) {
        super(itemView);
        textViewHour = itemView.findViewById(R.id.recycler_view_rappel_hour);
        textViewTitle = itemView.findViewById(R.id.recycler_view_rappel_title);
        textViewDescription = itemView.findViewById(R.id.recycler_view_rappel_description);
        barreHorizontale = itemView.findViewById(R.id.recycler_view_rappel_descriptionBarre);
        ORANGE = ContextCompat.getColor(itemView.getContext(), R.color.myOrange);
        BLUE = ContextCompat.getColor(itemView.getContext(), R.color.myBlue);
        GREEN = ContextCompat.getColor(itemView.getContext(), R.color.myGreen);
    }


    public void bind(String r_type, String hour, String title, String description, Rappel rappel, FragmentManager fragmentManager) {
        switch(r_type) {
            case ("custom"): //fait par l'utilisateur
                textViewHour.setBackgroundColor(ORANGE);
                break;
            case ("defaut"): //généré par défaut
                textViewHour.setBackgroundColor(GREEN);
                break;
            default:    //autres (un rappel médical)
                textViewHour.setBackgroundColor(BLUE);
                break;
        }
        textViewHour.setText(hour);
        textViewTitle.setText(title);
        textViewDescription.setText(description);

        textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si description n'est pas visible et a du texte
                if(textViewDescription.getVisibility() != View.VISIBLE && textViewDescription.getText().toString() != null){

                    textViewDescription.setVisibility(View.VISIBLE);
                    barreHorizontale.setVisibility(View.VISIBLE);
                }else{
                    barreHorizontale.setVisibility(View.GONE);
                    textViewDescription.setVisibility(View.GONE);
                }

            }
        });
        textViewTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RappelViewModel.getInstance().setRappel(rappel);

                fragmentManager.beginTransaction().addToBackStack("pageRappel").replace(R.id.main_activity_fragment_container, new ModifierRappelFragment()).commit();
                return false;
            }
        });
    }

    static RappelViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_rappel, parent, false);
        return new RappelViewHolder(view);
    }
}
