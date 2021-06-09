package com.example.project;

import android.util.Log;

import java.util.ArrayList;

import static com.example.project.MainActivity.spCount;

public class Scene {
    private String name;
    private ArrayList<Lamp> lamps = new ArrayList<>();

    public Scene(String name, ArrayList<Lamp> intensities) {
        this.name = name;
        for(Lamp l: intensities){
            this.lamps.add(new Lamp(l.getChannel(), l.getIntensity()));
        }

    }

    public Scene(String name, int count, int intensity) {
        this.name = name;
        for(int i = 0; i < count; i++){
            this.lamps.add(new Lamp(i, intensity));
        }
    }

    public void changeLamp(Lamp lamp){
        lamps.get(lamp.getChannel()).setIntensity(lamp.getIntensity());
        Log.i("lamp changed", lamp.getChannel() + " " + lamp.getIntensity());
    }

    public void changeScene(Scene scene){
        lamps.clear();
        ArrayList<Lamp> scene_lamps = scene.getLamps();
        for (int i = 0; i < spCount; i++){
            if (i < scene_lamps.size())
            {
                Lamp scene_lamp = scene_lamps.get(i);
                lamps.add(new Lamp(i, scene_lamp.getIntensity()));
            }
            else
            {
                lamps.add(new Lamp(i, 0));
            }
        }
    }


    public void changeLampsCount(int count){
        int difference = count - lamps.size();
        if(difference >= 0){
//            for(int i = lamps.size(); i < count; i++){
//                lamps.add(new Lamp(i, 0));
//            }
            while (lamps.size() < count) {
                //lamps.add(new Lamp(i, 0));
                lamps.add(new Lamp(lamps.size(), lamps.get(lamps.size() - 1).getIntensity()));
            }
        }
        else{
            for(Lamp l: lamps){
                Log.i("lamp", l.getChannel() + " " + l.getIntensity());
            }
//            for(int i = count; i < lamps.size(); i++){
//                lamps.remove(i);
//            }
            while (lamps.size() > count)
            {
                lamps.remove(lamps.size() - 1);
            }
        }

    }

    public String getName() {
        return name;
    }

    public ArrayList<Lamp> getLamps() {
        return lamps;
    }

    public static ArrayList<Lamp> cloneLamps(ArrayList<Lamp> lamps){
        ArrayList<Lamp> newLamps = new ArrayList<>();
        for (Lamp l: lamps){
            newLamps.add(new Lamp(l.getChannel(), l.getIntensity()));
        }
        return newLamps;
    }



}
