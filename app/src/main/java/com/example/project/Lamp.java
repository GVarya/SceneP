package com.example.project;

public class Lamp {
    private int cannal;
    private int intensity;


    public Lamp(int cannal, int intensity) {
        this.cannal = cannal;
        this.intensity = intensity;
    }

    public int getCannal() {
        return cannal;
    }



    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
