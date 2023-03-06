package com.company;

public class Language {
    private int ID;
    String code;
    String name;

    public Language() {
    }

    public Language(int id, String code, String name)
    {
        this.ID = id;
        this.code = code;
        this.name = name;
    }

    public int getId() { return ID; }

    public void setId(int id) {
        this.ID = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ID + "-" + code;
    }
}
