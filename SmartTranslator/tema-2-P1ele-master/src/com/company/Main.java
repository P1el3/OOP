package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;


public class Main {

    static Map<String, Dictionary> Language = new HashMap<>();
    static boolean addWord(Word word, String language){
        for(int i = 0; i < Language.get(language).dictionar.size();i++)
            if(Language.get(language).dictionar.get(i).word.equals(word.getWord()))
                return false;
        //daca nu exista, il adaug
        Language.get(language).dictionar.add(word);
        return true;
           }


    static boolean removeWord(String word, String language){
        //caut daca cuvantul exista in lista
        for (int i = 0; i < Language.get(language).dictionar.size(); i++)
            if (Language.get(language).dictionar.get(i).word.equals(word)){ //daca l-am gasit
                Language.get(language).dictionar.remove(i); //il elimin
                return true;
            }
        return false;
    }

    static boolean addDefinitionForWord(String word, String language, Definition definition){
        //caut daca cuvantul exista in lista
        for (int i = 0; i < Language.get(language).dictionar.size(); i++){
            if (Language.get(language).dictionar.get(i).word.equals(word)) { //daca l-am gasit
                for (int j = 0; j < Language.get(language).dictionar.get(i).getDefinitions().size(); j++) {
                    if (Language.get(language).dictionar.get(i).getDefinitions().get(j).getDict().equals(definition.getDict()))
                        return false;//caut daca exista deja dictionarul
                }
                Language.get(language).dictionar.get(i).definitions.add(definition); //adaug definitia
                return true;
            }
        }
        return false;
    }

    static boolean removeDefinition(String word, String language, String dictionary){
        //caut daca cuvantul exista in lista
        for (int i = 0; i < Language.get(language).dictionar.size(); i++){
            if (Language.get(language).dictionar.get(i).word.equals(word)) { //daca l-am gasit
                for(int j = 0; j < Language.get(language).dictionar.get(i).definitions.size(); j++){//caut in lista de definitii
                    if(Language.get(language).dictionar.get(i).definitions.get(j).dict.equals(dictionary)){//daca am gasit definitia, o sterg
                        if(Language.get(language).dictionar.get(i).definitions.get(j).dictType.equals("definitions")){
                            Language.get(language).dictionar.get(i).definitions.remove(j);
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    static String translateWord(String word, String fromLanguage, String toLanguage) {
        String eng_form = null;
        boolean numar = true; //singular = true / plural = false
        int persoana = 0;


        for (int i = 0; i < Language.get(fromLanguage).dictionar.size(); i++) {
            if (Language.get(fromLanguage).dictionar.get(i).word.equals(word)) { //daca l-am gasit
                numar = true;
                eng_form = Language.get(fromLanguage).dictionar.get(i).word_en; //ii salvez forma din engleza
            }
            for (int j = 0; j < Language.get(fromLanguage).dictionar.get(i).singular.size(); j++){
                if (Language.get(fromLanguage).dictionar.get(i).singular.get(j).equals(word)){
                    eng_form = Language.get(fromLanguage).dictionar.get(i).word_en; //ii salvez forma din engleza
                    persoana = j;
                }
            }
            for (int j = 0; j < Language.get(fromLanguage).dictionar.get(i).plural.size(); j++){
                if (Language.get(fromLanguage).dictionar.get(i).plural.get(j).equals(word)){
                    numar = false;
                    eng_form = Language.get(fromLanguage).dictionar.get(i).word_en; //ii salvez forma din engleza
                    persoana = j;
                }
            }
        }
        if(eng_form == null) return word; //in caz ca nu am gasit cuvantul-

        for (int i = 0; i < Language.get(toLanguage).dictionar.size(); i++) { //ii caut forma in engleza din cealalta limba
            if (Language.get(toLanguage).dictionar.get(i).word_en.equals(eng_form)){
                if(numar == true) //daca e singular
                    return Language.get(toLanguage).dictionar.get(i).singular.get(persoana);
                else return Language.get(toLanguage).dictionar.get(i).plural.get(persoana); //daca e plural
            }
        }
        return word;
    }

    static String translateSentence(String sentence, String fromLanguage, String toLanguage){
        String[] eachWord =  sentence.split(" ");

        String translatedSentece = " ";
        for(int i = 0; i < eachWord.length; i++) {

            translatedSentece += translateWord(eachWord[i], fromLanguage, toLanguage) + " ";
        }
        return  translatedSentece;
    }

    static ArrayList<Definition> getDefinitionsForWord(String word, String language){
        for (int i = 0; i < Language.get(language).dictionar.size(); i++){
            if (Language.get(language).dictionar.get(i).word.equals(word)) { //daca l-am gasit
                Collections.sort(Language.get(language).dictionar.get(i).definitions);
                return (ArrayList<Definition>) Language.get(language).dictionar.get(i).definitions;//returnez vectorul cu definitiile
            }
        }
        return null;
    }

    static void exportDictionary(String language) throws IOException {
        Dictionary auxDict = Language.get(language); //iau dictionarul limbii
        for (int i = 0; i < auxDict.dictionar.size(); i++){
            Collections.sort(auxDict.dictionar.get(i).definitions);
        }
        Collections.sort(auxDict.dictionar);
        FileWriter exportedDict = new FileWriter("Dict_" + language + ".json");
        Gson o = new GsonBuilder().setPrettyPrinting().create();
        final Type dictionary = new TypeToken<List<Word>>() {
        }.getType();
        exportedDict.write(o.toJson(auxDict.dictionar, dictionary));
        exportedDict.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        final Type dictionary = new TypeToken<List<Word>>() { //chestii gasite pe stack overflow
        }.getType();
        Gson g = new Gson();
        for(File f : (new File("inputs")).listFiles()){
            Dictionary dict = new Dictionary();
            dict.dictionar = g.fromJson(new FileReader(f.getPath()), dictionary);
            Language.put(f.getName().split("_")[0],dict);
        }


        //imi definesc 2 cuvinte pentru teste
        Definition def1 = new Definition("Dex", "definitions", 2001);
        def1.text.add("negru curat");
        def1.text.add("go pick the cotton");
        Word w1 = new Word("pisică", "cat", "noun" );
        w1.definitions.add(def1);
        w1.singular.add("pisică");
        w1.plural.add("pisici");

        Definition def2 = new Definition("xeD", "definitions", 1967);
        def2.text.add("autovehicul");
        def2.text.add("mijloc de transport");
        Word w2 = new Word("mașină", "car", "noun");
        w2.definitions.add(def2);
        w2.singular.add("mașină");
        w2.plural.add("mașini");
        System.out.println();

        //testare addWord
        if(addWord(w1, "ro") == true){
            System.out.println("Cuvantul " + w1.getWord() + " a fost adaugat!");
        }
        else{
            System.out.println("Cuvantul " + w1.getWord()+ " nu a fost adaugat!");
        }
        if(addWord(w2, "ro") == true){
            System.out.println("Cuvantul " + w2.getWord() + " a fost adaugat!");
        }
        else{
            System.out.println("Cuvantul " + w2.getWord()+ " nu a fost adaugat!");
        }

        System.out.println();

        //testare removeWord

        if(removeWord( w1.getWord(), "ro") == true){
            System.out.println("Cuvantul " + w1.getWord() + " a fost eliminat!");
        }
        else{
            System.out.println("Cuvantul " + w1.getWord()+ " nu a fost eliminat!");
        }
        if(removeWord(w2.getWord(), "ro") == true){
            System.out.println("Cuvantul " + w2.getWord() + " a fost eliminat!");
        }
        else{
            System.out.println("Cuvantul " + w2.getWord() + " nu a fost eliminat!");
        }

        System.out.println();

        //testare addDefinitionForWord
        addWord(w1, "ro"); //adaug iar cuvintele, ca altfel nu merge
        addWord(w2, "ro");//comenteaza una din astea 2 si va da false
        if(addDefinitionForWord(w1.getWord(), "ro", def1) == true){
            System.out.println("Definitia cuvantului " +  w1.getWord() + " a fost adaugata!");
        }
        else{
            System.out.println("Definitia cuvantului " +  w1.getWord() + " nu a fost adaugata!");
        }

        if(addDefinitionForWord(w2.getWord(), "ro", def2) == true){ //adaug def de la celalalt sa vad daca mere
            System.out.println("Definitia cuvantului " +  w2.getWord() + " a fost adaugata!");
        }
        else{
            System.out.println("Definitia cuvantului " +  w2.getWord() + " nu a fost adaugata!");
        }

        System.out.println();

        //testarea removeDefinition

        if(removeDefinition(w1.getWord(), "ro", "Dex")){
            System.out.println("Definitia cuvantului " + w1.getWord() + " a fost stearsa cu succes!");
        }
        else{
            System.out.println("Definitia cuvantului " + w1.getWord() + " nu a fost stearsa!");
        } //testez o definitie pe care nu am dat-o

        if(removeDefinition(w2.getWord(), "ro", "xeD")){
            System.out.println("Definitia cuvantului " + w2.getWord() + " a fost stearsa cu succes!");
        }
        else{
            System.out.println("Definitia cuvantului " + w2.getWord() + " nu a fost stearsa!");
        }

        System.out.println();

        //testare translateWord
        System.out.println("Daca cuvantul are aceeasi forma, acesta nu exista in dictionar si nu poate fi tradus.");
        System.out.println("Test1: " + translateWord("chat", "fr", "ro"));

        System.out.println("Test2: " + translateWord("mașină", "ro", "fr"));

        System.out.println();

        //testare translateSentence

        Definition def3 = new Definition("Joc", "synonyms", 1000);
        def3.text.add("activitate");
        def3.text.add("chestie nebuna");
        Word w3 = new Word("joc", "game", "noun" );
        w3.definitions.add(def1);
        w3.singular.add("joc");
        w3.plural.add("jocuri");
        addWord(w3,"ro");

        //nu am mai stat sa mai adaug mai multe cuvinte care sa se potriveasca
        //recunosc ca am fost lenes si am lasat tema pe ultima seara pare rau :/

        System.out.println(translateSentence("chat jeux chats jeu", "fr", "ro"));
        System.out.println(translateSentence("jeux chats jeu chat", "fr", "ro"));
        System.out.println(translateSentence("pisică jocuri pisici joc", "ro", "fr"));
        //erau prea multe cuvinte de adaugat si greu de facut propozitia coerenta
        System.out.println();

        //testare getDefinitionsForWord

        System.out.println(getDefinitionsForWord("mașină", "ro"));
        System.out.println(getDefinitionsForWord("câine", "ro"));

        System.out.println();

        //testare exportDictionary

        try { //se exporta dictionarul in Dict_ro.json
            exportDictionary("ro");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {//se exporta dictionarul in Dict_fr.json
            exportDictionary("fr");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
