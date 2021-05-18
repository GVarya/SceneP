package com.example.project;

import java.util.ArrayList;

public class Scene {
    private String name;
    private ArrayList<Lamp> lamps = new ArrayList<>();

    public Scene(String name, ArrayList<Lamp> intensities) {
        this.name = name;
        this.lamps = intensities;
    }

    public Scene(String name, int count, int intensity) {
        this.name = name;
        for(int i = 0; i < count; i++){
            this.lamps.add(new Lamp(i, intensity));
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Lamp> getLamps() {
        return lamps;
    }


}
