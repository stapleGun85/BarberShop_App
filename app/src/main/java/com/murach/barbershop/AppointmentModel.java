package com.murach.barbershop;

public class AppointmentModel {

    private String haircut;
    private String style;
    private String color;
    private String shave;
    private String date;
    private String time;
    private int id;

    public AppointmentModel(int id, String haircut, String style, String color, String shave, String date, String time){

        this.id = id;
        this.haircut = haircut;
        this.style = style;
        this.color = color;
        this.shave = shave;
        this.date = date;
        this.time = time;

    }

    public AppointmentModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String isHaircut() {
        return haircut;
    }

    public void setHaircut(String haircut) {
        this.haircut = haircut;
    }

    public String isStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String isColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String isShave() {
        return shave;
    }

    public void setShave(String shave) {
        this.shave = shave;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment Number: " + id +
                "\nHaircut: " + haircut +
                "\nStyle: " + style +
                "\nColor: " + color +
                "\nShave: " + shave +
                "\nDate: " + date +
                "\nTime: " + time;
    }
}
