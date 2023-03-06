package WhereTheBattleBegins;

import Antrenor.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Antrenor {
    String name;
    int age;
    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon pokemon3;
    Pokemon pokemonInBattle;
    public Pokemon PokemonIChoseYou(){
        if( pokemon1.getHP() > 0){
            pokemonInBattle =  pokemon1;
            return pokemon1;}
        if (pokemon2.getHP() > 0){
            pokemonInBattle =  pokemon2;
            return  pokemon2;}
        if (pokemon3.getHP() > 0){
            pokemonInBattle =  pokemon3;
            return pokemon3;}
        else{
            pokemonInBattle = null;
            return null;
        }
    }

    public MoveDTO movement(Pokemon defender) {
        List<MoveDTO> wichAttack = new ArrayList<>();
        if(pokemonInBattle.getAttack() != 0 && pokemonInBattle.getAttack() > defender.getDefense())
            wichAttack.add(new MoveDTO(pokemonInBattle.getAttack(), 0, null));
        if(pokemonInBattle.getSpecial_Attack() != 0 && pokemonInBattle.getSpecial_Attack() > defender.getSpecial_Defense())
            wichAttack.add(new MoveDTO(0, pokemonInBattle.getSpecial_Attack(), null));
        for(int i = 0; i < pokemonInBattle.getAbilities().size(); i++) {
            if(pokemonInBattle.getAbilities().get(i).Usable())
                wichAttack.add(new MoveDTO(0 , 0, pokemonInBattle.getAbilities().get(i)));
        }

        if( wichAttack.size() == 0 )
        {
            if(pokemonInBattle.getAttack() != 0)
                wichAttack.add(new MoveDTO(pokemonInBattle.getAttack(), 0, null));
            if(pokemonInBattle.getSpecial_Attack() != 0)
                wichAttack.add(new MoveDTO(0, pokemonInBattle.getSpecial_Attack(), null));
        }

        Random getRandom = new Random();
        int index = getRandom.nextInt(wichAttack.size());
        return wichAttack.get(index);
    }

    public Boolean canFight(){
        return PokemonIChoseYou() != null;
    }

    public Antrenor(AntrenorBuilder antrenorBuilder) {
        this.name = antrenorBuilder.name;
        this.age = antrenorBuilder.age;
        this.pokemon1 = antrenorBuilder.pokemon1;
        this.pokemon2 = antrenorBuilder.pokemon2;
        this.pokemon3 = antrenorBuilder.pokemon3;
    }

    public static class AntrenorBuilder {
        String name;
        int age;
        Pokemon pokemon1;
        Pokemon pokemon2;
        Pokemon pokemon3;

        public AntrenorBuilder name(String name) {
            this.name = name;
            return this;}
        public AntrenorBuilder age(int age) {
            this.age = age;
            return this;}

        public AntrenorBuilder pokemons(Pokemon pokemon, int index) {
            if (index == 1) { pokemon1 = pokemon; }
            if (index == 2) { pokemon2 = pokemon; }
            if (index == 3) { pokemon3 = pokemon; }

            return this;
        }
        public Antrenor build() {
            return new Antrenor(this);
        }

    }
}
