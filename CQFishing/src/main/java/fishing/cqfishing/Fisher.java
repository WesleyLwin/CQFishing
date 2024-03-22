/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fishing.cqfishing;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class Fisher {
    private int ID;
    private String name;
    private String phone;
    private LocalDate date;
    private double weight;
    
    //Default Constructor
    public Fisher(){
        this.ID = 0;
        this.name = "";
        this.phone = "";
        this.date = null;
        this.weight = 0.0;
    }
    
    //Parameterized Constructor
    public Fisher(int ID, String name, String phone, LocalDate date, double weight) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.weight = weight;
    }
    
    //Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Fisher{");
        sb.append("ID= ").append(ID);
        sb.append("name = ").append(name);
        sb.append("phone= ").append(phone);
        sb.append("date= ").append(date);
        sb.append("Weight= ").append(weight);
        sb.append("}");
        return sb.toString();
    }
    
}
