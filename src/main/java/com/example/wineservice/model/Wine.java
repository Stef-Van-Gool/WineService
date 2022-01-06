package com.example.wineservice.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wines")
public class Wine {
    //Variables
    @Id
    private String id;
    private String name;
    private String region;
    private String country;
    private double score;
    private String grapeName;

    //Constructors
    public Wine(){
    }

    public Wine(String name, String region, String country, double score, String grapeName){
        setName(name);
        setRegion(region);
        setCountry(country);
        setScore(score);
        setGrapeName(grapeName);
    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getGrapeName() {
        return grapeName;
    }

    public void setGrapeName(String grapeName) {
        this.grapeName = grapeName;
    }
}