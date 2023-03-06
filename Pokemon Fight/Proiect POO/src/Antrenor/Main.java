package Antrenor;

import WhereTheBattleBegins.WhereTheBattleBegins;
import WhereTheBattleBegins.Antrenor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Factories.PokemonFactory.createPokemon;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Antrenor.AntrenorBuilder antrenorBuilder1 = new Antrenor.AntrenorBuilder();
        Scanner reader = new Scanner(new FileReader("C:\\Users\\Savu\\Desktop\\Piele_Mihai_Teodor_321CB_Proiect_POO\\Proiect POO/TrainerData/Fight1.txt"));
        antrenorBuilder1.name(reader.nextLine()).age(reader.nextInt());
        reader.nextLine();

        for(int i = 0; i < 3; i++) {
            String line = reader.nextLine();
            String[] elements = line.split(";");
            antrenorBuilder1.pokemons(createPokemon(elements[0],elements[1],elements[2],elements[3]), i+1);
        }
        Antrenor antrenor1 = antrenorBuilder1.build();

        Antrenor.AntrenorBuilder antrenorBuilder2 = new Antrenor.AntrenorBuilder();
        antrenorBuilder2.name(reader.nextLine()).age(reader.nextInt());
        reader.nextLine();

        for(int i = 0; i < 3; i++) {
            String line = reader.nextLine();
            String[] elements = line.split(";");
            antrenorBuilder2.pokemons(createPokemon(elements[0],elements[1],elements[2],elements[2]), i+1);
        }
        Antrenor antrenor2 = antrenorBuilder2.build();


        //let the fight begin
        Scanner sc = new Scanner(System.in);
        String path = "";
        System.out.println("Where do you wish the logger? Options: stdout/file (for file, write output/file_name before :)");
        System.out.print("Please, write here:");
        path = sc.nextLine();
        //threads start
        ExecutorService startThread = Executors.newCachedThreadPool();
        startThread.execute(new WhereTheBattleBegins(antrenor1, antrenor2, path));
        //threads shutdown
        startThread.shutdown();
    }
}
