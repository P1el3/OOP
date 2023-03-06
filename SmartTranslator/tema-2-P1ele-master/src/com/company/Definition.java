package com.company;

import java.util.ArrayList;
import java.util.List;

public class Definition implements Comparable<Definition>{
    String dict;
    String dictType;
    int year;
    List<String> text;

    public Definition(String dict, String dictType, int year) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = new ArrayList<>();
    }
    public Definition(){}

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getText() {
        return text;
    }

    public void addText(String text) {
        this.text.add(text);
    }

    @Override
    public int compareTo(Definition o) {
        return this.year - o.year; //compar anul aparitiei si returnez - daca e mai mic, 0 daca s egale si 0 daca i mai mare
    }
}
