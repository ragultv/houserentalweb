package com.houserental.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenants")
@Data
public class Tenant {
    @Id
    private String id;
    private String name;
    private String contact;
    private String preferredLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
    public Tenant(String id, String name, String contact, String preferredLocation) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.preferredLocation = preferredLocation;
    }
}