package com.loicdavid.testprojetbachelor.View.Fragment;

import android.view.ViewGroup;

import com.loicdavid.testprojetbachelor.Room.Rappel;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class RappelListAdapter extends ListAdapter<Rappel, RappelViewHolder> {

    FragmentManager fragmentManager;

    public RappelListAdapter(@NonNull DiffUtil.ItemCallback<Rappel> diffCallback, FragmentManager fragManager) {

        super(diffCallback);
        fragmentManager = fragManager;
    }

    @Override
    public RappelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RappelViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RappelViewHolder holder, int position) {
        Rappel current = getItem(position);
        holder.bind(current.getType_rappel(), current.getHeure(), current.getNom_rappel(), current.getDescription(), current, fragmentManager);
    }

    static class WordDiff extends DiffUtil.ItemCallback<Rappel> {

        @Override
        public boolean areItemsTheSame(@NonNull Rappel oldItem, @NonNull Rappel newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Rappel oldItem, @NonNull Rappel newItem) {
            return ( oldItem.getId_rappel() == (newItem.getId_rappel()) );
        }
    }


}
