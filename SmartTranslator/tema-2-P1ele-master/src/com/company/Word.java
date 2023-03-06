package com.company;

import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable <Word> {
    String word;
    String word_en;
    String type;
    List<String> singular;
    List<String> plural;
    List<Definition> definitions;

    public Word(String word, String word_en, String type) {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = new ArrayList<>();
        this.plural = new ArrayList<>();
        this.definitions = new ArrayList<>();
    }

    public Word(){}

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_en() {
        return word_en;
    }

    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSingular() {
        return singular;
    }

    public void addSingular(String singular) {
        this.singular.add(singular);
    }

    public List<String> getPlural() {
        return plural;
    }

    public void addPlural(String plural) {
        this.plural.add(plural);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void addDefinitions(Definition definitions) {
        this.definitions.add(definitions);
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word); //returnez alfabetic
    }
}
