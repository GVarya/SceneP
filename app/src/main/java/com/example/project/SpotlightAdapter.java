package com.example.project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.project.MainActivity.currentPosition;
import static com.example.project.BeforeYouStartActivity.s;
import static java.lang.Integer.parseInt;

public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.ViewHolder> {
    private int numbOfSpls;
    public static class ViewHolder extends RecyclerView.ViewHolder implements SeekBar.OnSeekBarChangeListener {
        private SeekBar spSeekbar;
        private TextView spCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            spSeekbar = itemView.findViewById(R.id.seek_bar);
            spCount = itemView.findViewById(R.id.spl_count);
            spSeekbar.setOnSeekBarChangeListener(this);

        }



        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i("spotlight send", "was");
            s.send(new Lamp(parseInt(spCount.getText().toString()) - 1, seekBar.getProgress()));
            currentPosition.changeLamp(new Lamp(parseInt(spCount.getText().toString()) - 1, seekBar.getProgress()));

        }

    }

    public SpotlightAdapter(int numbOfSpls) {
        this.numbOfSpls = numbOfSpls;
    }

    @NonNull
    @Override
    public SpotlightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_spotlight_item, parent, false);


        return new SpotlightAdapter.ViewHolder(view);    }




    @NonNull
    @Override
    public void onBindViewHolder(@NonNull SpotlightAdapter.ViewHolder holder, int position) {
        holder.spCount.setText(position + 1 + "");
        holder.spSeekbar.setProgress(currentPosition.getLamps().get(parseInt(holder.spCount.getText().toString()) - 1).getIntensity());

    }

    @Override
    public int getItemCount() {
        return numbOfSpls;
    }
}
