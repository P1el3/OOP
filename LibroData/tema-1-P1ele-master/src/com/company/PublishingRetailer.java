package com.company;
import java.util.ArrayList;
import java.util.List;

public class PublishingRetailer {
    int ID;
    String name;
    List <IPublishingArtifact> publishingArtifacts;
    List <Countries> countries;

    public PublishingRetailer() {
    }

    public PublishingRetailer(int id, String name)
    {
        this.ID = id;
        this.name = name;
        this.publishingArtifacts = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IPublishingArtifact> getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void addPublishingArtifact(IPublishingArtifact publishingArtifact) {
        publishingArtifacts.add(publishingArtifact);
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public void addCountries(Countries country) {
        countries.add(country);
    }




}
