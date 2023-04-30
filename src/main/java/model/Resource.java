package model;

public class Resource {
    //todo: not all of these should e 0 at the beginning
    private int wheat = 0;
    private int wood = 0;
    private int flour = 0;
    private int hop = 0;
    private int ale = 0;
    private int stone = 0;
    private int iron = 0;
    private int pitch = 0;
    private int meat  = 0;
    private int cheese= 0;
    private int bread = 0;
    private int apples= 0;
    public int getWheat() {
        return wheat;
    }

    public int getWood() {
        return wood;
    }

    public int getFlour() {
        return flour;
    }

    public int getHop() {
        return hop;
    }

    public int getAle() {
        return ale;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }

    public int getPitch() {
        return pitch;
    }

    public void changeWheat(int wheat) {
        this.wheat += wheat;
    }

    public void changeWood(int wood) {
        this.wood += wood;
    }

    public void changeFlour(int flour) {
        this.flour += flour;
    }

    public void changeHop(int hop) {
        this.hop += hop;
    }

    public void changeAle(int ale) {
        this.ale += ale;
    }

    public void changeStone(int stone) {
        this.stone += stone;
    }

    public void changeIron(int iron) {
        this.iron += iron;
    }

    public void changePitch(int pitch) {
        this.pitch += pitch;
    }

    public int getMeat() {
        return meat;
    }

    public int getCheese() {
        return cheese;
    }

    public int getBread() {
        return bread;
    }

    public int getApples() {
        return apples;
    }

    public void changeMeat(int meat) {
        this.meat += meat;
    }

    public void changeCheese(int cheese) {
        this.cheese += cheese;
    }

    public void changeBread(int bread) {
        this.bread += bread;
    }

    public void changeApples(int apples) {
        this.apples += apples;
    }
}
