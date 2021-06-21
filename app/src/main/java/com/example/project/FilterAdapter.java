package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.project.BeforeYouStartActivity.s;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder>{

    private ArrayList<String> filterNames;


    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private Button button_on;
        private Button button_off;
        private TextView filterNameItem;
        private String filterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button_on = (Button) itemView.findViewById(R.id.button_on);
            button_off = (Button) itemView.findViewById(R.id.button_off);
            filterNameItem = itemView.findViewById(R.id.super_effect_name);
            button_on.setOnClickListener(this);
            button_off.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_on){
                s.send(new FilterCommand(filterNameItem.getText().toString(), true));
            }
            if(v.getId() == R.id.button_off){
                s.send(new FilterCommand(filterNameItem.getText().toString(), false));
            }
        }
    }

    public FilterAdapter(ArrayList<String> fiterNames) {
        this.filterNames = fiterNames;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_filter_item, parent, false);


        return new FilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, int position) {
        holder.filterNameItem.setText(filterNames.get(position));
    }

    @Override
    public int getItemCount() {
        return filterNames.size();
    }
}
