package com.example.task1.Models;

public class FacilitiesMotherModel {
    String facility_id;
    String name;
    Object options;

    public FacilitiesMotherModel(String facility_id, String name, Object options) {
        this.facility_id = facility_id;
        this.name = name;
        this.options = options;
    }

    public FacilitiesMotherModel() {

    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }
}
