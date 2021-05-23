package com.example.project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.project.BeforeYouStartActivity.currentPosition;
import static com.example.project.BeforeYouStartActivity.s;
import static com.example.project.BeforeYouStartActivity.scenes;

public class SceneAdapter  extends RecyclerView.Adapter<SceneAdapter.ViewHolder>{

    private ArrayList<Scene> localScenes;


    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private final Button button;
        private String sceneName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.scene_button);
            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.scene_button){
                sceneName = ((Button) v).getText().toString();
                for(Scene scene: scenes){
                    if(scene.getName().equals(sceneName)){
                        for(Lamp l: scene.getLamps()){
                            s.send(l);
                            Log.i("lamp", l.getCannal()+ " " + l.getIntensity());
                            currentPosition.changeLamp(l);
                        }
                    }
                }
            }
        }
    }

    public SceneAdapter(ArrayList<Scene> localScenes) {
        this.localScenes = localScenes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_scene_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(localScenes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return localScenes.size();
    }



}
