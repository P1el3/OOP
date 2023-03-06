package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static List<Book> carti = new ArrayList<>();
    static List<Author> autori = new ArrayList<>();
    static List<Countries> tari = new ArrayList<>();
    static List<Language> limbi = new ArrayList<>();
    static List<EditorialGroup> grupEditorial = new ArrayList<>();
    static List<PublishingRetailer> retailer = new ArrayList<>();
    static List<PublishingBrand> brand = new ArrayList<>();
    static List<Book> getBooksForPublishingRetailerID(int publishingRetailerID){
        Set<Book> list = new HashSet<>();
        PublishingRetailer aux = null;
        for(var x: retailer){
            if (x.getId() == publishingRetailerID)
                aux = x;
        }
        for(var x: aux.getPublishingArtifacts()){
            if(x instanceof Book)
                list.add((Book) x);
            else if(x instanceof EditorialGroup)
                for(var y: ((EditorialGroup) x).getBooks())
                    list.add(y);
            else
                for(var y: ((PublishingBrand) x).getBooks())
                    list.add(y);
        }
        return list.stream().toList();
    }
    static List<Language> getLanguagesForPublishingRetailerID(int publishingReatilerID){
        Set<Language> list = new HashSet<>();
        PublishingRetailer aux = null;
        for(var x: retailer){
            if (x.getId() == publishingReatilerID);
                aux = x;
        }
        for(var x: aux.getPublishingArtifacts()) {
            if (x instanceof Book){
                for(var x_1: limbi)
                    if(x_1.getId() == ((Book) x).getLanguageID())
                        list.add(x_1);
            }
            else if (x instanceof EditorialGroup){
                for (var y : ((EditorialGroup) x).getBooks())
                    for(var x_1: limbi)
                        if(x_1.getId() == ((Book) y).getLanguageID())
                            list.add(x_1);
                    }
            else{
                for (var y : ((PublishingBrand) x).getBooks())
                    for(var x_1: limbi)
                        if(x_1.getId() == ((Book) y).getLanguageID())
                            list.add(x_1);
            }
        }
        return list.stream().toList();

    }
    static List<Countries> getCountriesForBookID(int bookID){
        Set<Countries> list = new HashSet<>();
        for(var x: retailer){
            List<Book> aux1 = getBooksForPublishingRetailerID(x.getId());
            for(var y: aux1){
                if(y.getID() == bookID)
                    list.addAll(x.getCountries());
            }

        }
        return list.stream().toList();
    }
    static List<Book> getCommonBooksForRetailerIDs(int retailerID1, int retailerID2){
        Set<Book> list = new HashSet<>();
        List<Book> aux1 = getBooksForPublishingRetailerID(retailerID1);
        List<Book> aux2 = getBooksForPublishingRetailerID(retailerID2);
        for(var x: aux1){
            for(var y: aux2)
                if(x.getID() == y.getID())
                    list.add(x);
        }
        return list.stream().toList();
    }
    static List<Book> getAllBooksForRetailerIDs (int retailerID1, int retailerID2){
        Set<Book> list = new HashSet<>();
        List<Book> aux1 = getBooksForPublishingRetailerID(retailerID1);
        List<Book> aux2 = getBooksForPublishingRetailerID(retailerID2);
        for(var x: aux1)
            list.add(x);
        for(var y: aux2)
            list.add(y);

        return list.stream().toList();
    }

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\books.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorBook = line.split("###");
                Book newBook = new Book();
                newBook.setID(Integer.parseInt(vectorBook[0]));
                newBook.setName(vectorBook[1]);
                newBook.setSubtitle(vectorBook[2]);
                newBook.setISBN(vectorBook[3]);
                newBook.setPageCount(Integer.parseInt(vectorBook[4]));
                for(String keyword: vectorBook[5].split("; ")){
                    newBook.addKeyword(keyword);
                }
                newBook.setLanguageID(Integer.parseInt(vectorBook[6]));
                newBook.setCreatedOn((new SimpleDateFormat("dd.MM.yyyy HH.mm:ss")).parse(vectorBook[7]));
                carti.add(newBook);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\authors.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorAuthor = line.split("###");
               Author newAuthor = new Author();
               newAuthor.setId(Integer.parseInt(vectorAuthor[0]));
               newAuthor.setFirstName(vectorAuthor[1]);
               newAuthor.setLastName(vectorAuthor[2]);
               autori.add(newAuthor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\countries.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorCountrie = line.split("###");
                Countries newCountrie = new Countries();
                newCountrie.setID(Integer.parseInt(vectorCountrie[0]));
                newCountrie.setCountryCode(vectorCountrie[1]);
                tari.add(newCountrie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\languages.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorLanguage = line.split("###");
                Language newLanguage = new Language();
                newLanguage.setId(Integer.parseInt(vectorLanguage[0]));
                newLanguage.setCode(vectorLanguage[1]);
                newLanguage.setCode(vectorLanguage[2]);
                limbi.add(newLanguage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\editorial-groups.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorEditorialGroup = line.split("###");
                EditorialGroup newEditorialGroup = new EditorialGroup();
                newEditorialGroup.setId(Integer.parseInt(vectorEditorialGroup[0]));
                newEditorialGroup.setName(vectorEditorialGroup[1]);
                grupEditorial.add(newEditorialGroup);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-brands.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorPubBrands = line.split("###");
                PublishingBrand newPubBrands = new PublishingBrand();
                newPubBrands.setId(Integer.parseInt(vectorPubBrands[0]));
                newPubBrands.setName(vectorPubBrands[1]);
                brand.add(newPubBrands);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-retailers.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorPubRetailer = line.split("###");
                PublishingRetailer newPubRetailer = new PublishingRetailer();

                newPubRetailer.setId(Integer.parseInt(vectorPubRetailer[0]));
                newPubRetailer.setName(vectorPubRetailer[1]);
                retailer.add(newPubRetailer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\books-author.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorBookAuthor = line.split("###");

                int bookid = Integer.parseInt(vectorBookAuthor[0]);
                int autorid = Integer.parseInt(vectorBookAuthor[1]);
                Book auxBook = null;
                for(Book searchBook: carti){
                    if(searchBook.getID() == bookid)
                        auxBook = searchBook;
                }
                Author auxAuthor = null;
                for(Author searchAuthor: autori){
                    if(searchAuthor.getId() == autorid)
                        auxAuthor = searchAuthor;
                }
                auxBook.addAuthor(auxAuthor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\editorial-groups-books.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorGroupBook = line.split("###");

                int groupid = Integer.parseInt(vectorGroupBook[0]);
                int bookid = Integer.parseInt(vectorGroupBook[1]);

                EditorialGroup auxgroup = null;
                for (EditorialGroup searchgroup : grupEditorial) {
                    if (searchgroup.getId() == groupid)
                        auxgroup = searchgroup;
                }

                Book auxBook = null;
                for (Book searchBook : carti) {
                    if (searchBook.getID() == bookid)
                        auxBook = searchBook;
                }
                auxgroup.addBook(auxBook);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-brands-books.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorBrandBook = line.split("###");

                int brandid = Integer.parseInt(vectorBrandBook[0]);
                int bookid = Integer.parseInt(vectorBrandBook[1]);
                 PublishingBrand auxBrand = null;
                 for(PublishingBrand searchBrand : brand){
                     if(searchBrand.getId() == brandid)
                         auxBrand = searchBrand;
                 }

                Book auxBook = null;
                for (Book searchBook : carti) {
                    if (searchBook.getID() == bookid)
                        auxBook = searchBook;
                }
                auxBrand.addBook(auxBook);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-retailers-books.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorRetailerBook = line.split("###");

                int reatilerid = Integer.parseInt(vectorRetailerBook[0]);
                int bookid = Integer.parseInt(vectorRetailerBook[1]);
                PublishingRetailer auxRetailer = null;
                for(PublishingRetailer searchRetailer : retailer){
                    if(searchRetailer.getId() == reatilerid)
                        auxRetailer = searchRetailer;
                }

                Book auxBook = null;
                for (Book searchBook : carti) {
                    if (searchBook.getID() == bookid)
                        auxBook = searchBook;
                }
                auxRetailer.addPublishingArtifact(auxBook);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-retailers-countries.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorRetailerCountrie = line.split("###");

                int reatilerid = Integer.parseInt(vectorRetailerCountrie[0]);
                int countrieid = Integer.parseInt(vectorRetailerCountrie[1]);
                PublishingRetailer auxRetailer = null;
                for(PublishingRetailer searchRetailer : retailer){
                    if(searchRetailer.getId() == reatilerid)
                        auxRetailer = searchRetailer;
                }

                Countries auxCountrie = null;
                for (Countries searchCountrie : tari) {
                    if (searchCountrie.getID() == countrieid)
                        auxCountrie = searchCountrie;
                }
                auxRetailer.addCountries((auxCountrie));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-retailers-editorial-groups.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorRetailerEditorial = line.split("###");

                int reatilerid = Integer.parseInt(vectorRetailerEditorial[0]);
                int editorialid = Integer.parseInt(vectorRetailerEditorial[1]);
                PublishingRetailer auxRetailer = null;
                for(PublishingRetailer searchRetailer : retailer){
                    if(searchRetailer.getId() == reatilerid)
                        auxRetailer = searchRetailer;
                }

                EditorialGroup auxEditorial = null;
                for (EditorialGroup searchEditorial : grupEditorial) {
                    if (searchEditorial.getId() == editorialid)
                        auxEditorial = searchEditorial;
                }
                auxRetailer.addPublishingArtifact(auxEditorial);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\init\\publishing-retailers-publishing-brands.in"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vectorRetailerBrand= line.split("###");

                int reatilerid = Integer.parseInt(vectorRetailerBrand[0]);
                int brandid = Integer.parseInt(vectorRetailerBrand[1]);
                PublishingRetailer auxRetailer = null;
                for(PublishingRetailer searchRetailer : retailer){
                    if(searchRetailer.getId() == reatilerid)
                        auxRetailer = searchRetailer;
                }

                PublishingBrand auxBrand = null;
                for (PublishingBrand searchBrand : brand) {
                    if (searchBrand.getId() == brandid)
                        auxBrand = searchBrand;
                }
                auxRetailer.addPublishingArtifact(auxBrand);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}

