/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author godwin.duah
 */

@JsonIgnoreProperties
public class PlacesRequest {
    private String location;
    private String radius;
    private String types;
    private String key;

    public PlacesRequest() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getTypes() {
        return types;
    }

    public void setType(String types) {
        this.types = types;
    }
    
    
    
}
