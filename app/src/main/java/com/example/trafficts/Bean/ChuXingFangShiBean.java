package com.example.trafficts.Bean;

public class ChuXingFangShiBean {

    /**
     * bus : 50
     * privatecar : 20
     * subway : 20
     * walk : 10
     */

    private float bus;
    private float privatecar;
    private float subway;
    private int walk;

    public float getBus() {
        return bus;
    }

    public void setBus(float bus) {
        this.bus = bus;
    }

    public float getPrivatecar() {
        return privatecar;
    }

    public void setPrivatecar(float privatecar) {
        this.privatecar = privatecar;
    }

    public float getSubway() {
        return subway;
    }

    public void setSubway(float subway) {
        this.subway = subway;
    }

    public int getWalk() {
        return walk;
    }

    public void setWalk(int walk) {
        this.walk = walk;
    }
}
