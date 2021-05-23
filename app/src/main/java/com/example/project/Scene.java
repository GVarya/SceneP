package com.example.project;

import android.util.Log;

import java.util.ArrayList;

public class Scene {
    private String name;
    private ArrayList<Lamp> lamps = new ArrayList<>();

    public Scene(String name, ArrayList<Lamp> intensities) {
        this.name = name;
        for(Lamp l: intensities){
            this.lamps.add(new Lamp(l.getCannal(), l.getIntensity()));
        }

    }

    public Scene(String name, int count, int intensity) {
        this.name = name;
        for(int i = 0; i < count; i++){
            this.lamps.add(new Lamp(i, intensity));
        }
    }

    public void changeLamp(Lamp lamp){
        lamps.get(lamp.getCannal()).setIntensity(lamp.getIntensity());
        Log.i("lamp changed", lamp.getCannal() + " " + lamp.getIntensity());
    }

    public String getName() {
        return name;
    }

    public ArrayList<Lamp> getLamps() {
        return lamps;
    }


}
