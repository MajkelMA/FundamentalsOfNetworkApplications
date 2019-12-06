package com.lapciakbilicki.pas2.model.Role;


import com.lapciakbilicki.pas2.model.IsIdentified;

public class Role implements IsIdentified {

    private String id;
    private String name;
    private String description;

    public Role() {

    }

    public Role(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
