package com.company;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements IPublishingArtifact {
    int ID, pageCount, languageID;
    String name;
    String subtitle;
    String ISBN;
    List <String> keywords;
    Date createdOn;
    List <Author> authors;
    public Book(){}

    public Book(int ID, int pageCount, int languageID, String name, String subtitle, String ISBN, Date createdOn) {
        this.ID = ID;
        this.pageCount = pageCount;
        this.languageID = languageID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.keywords = new ArrayList<>();
        this.createdOn = createdOn;
        this.authors = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    @Override
    public String Publish()
    {
        String s = "";

        s+= "<xml>\n";
        s += "\t<title>" + name + "</title>\n";
        s +="\t<subtitle>" + subtitle + "</subtitle>\n";
        s +="\t<isbn>" + ISBN + "</isbn>\n";
        s +="\t<pageCount>" + pageCount + "</pageCount>\n";
        s +="\t<keywords>" + keywords + "</keywords>\n";
        s +="\t<languageID>" + languageID + "</languageID>\n";
        s +="\t<createdOn>" + (new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")).format(createdOn) + "</createdOn>\n";
        s +="\t<authors>" + authors + "</authors>\n";
        s +="</xml>\n";

        return  s;
    }



}





