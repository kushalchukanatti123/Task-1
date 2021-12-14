package com.example.task1.Models;

public class FacilitiesModel {
    String name,icon,id;

    public FacilitiesModel(String name, String icon, String id) {
        this.name = name;
        this.icon = icon;
        this.id = id;
    }

    public FacilitiesModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
