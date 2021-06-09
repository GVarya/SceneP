package com.example.project;

public class Lamp {
    private int channel;
    private int intensity;


    public Lamp(int cannal, int intensity) {
        this.channel = cannal;
        this.intensity = intensity;
    }

    public int getChannel() {
        return channel;
    }



    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "channel=" + channel +
                ", intensity=" + intensity +
                '}';
    }
}
