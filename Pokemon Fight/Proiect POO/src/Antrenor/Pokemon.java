package Antrenor;

import Antrenor.PokemonAddons.Abilities;
import Antrenor.PokemonAddons.Items;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    String name;
    int HP;
    int maxHP;
    int Attack;
    int Special_Attack;
    int Defense;
    int Special_Defense;
    int stun = 0;
    List<Items> items = new ArrayList<>();
    List<Abilities> abilities = new ArrayList<>();

    public int getHP() {
        int auxHP = HP;
        for(int i = 0; i < items.size(); i++)
            auxHP += items.get(i).HP;
        return auxHP;
    }

    public void DecreaseHP(int damage)
    {
        this.HP -= damage;
    }

    public int getAttack() {
        int auxAttack = Attack;
        for(int i = 0; i < items.size(); i++)
            auxAttack += items.get(i).Attack;
        return auxAttack;
    }

    public int getSpecial_Attack() {
        int auxSpecial_Attack = Special_Attack;
        for(int i = 0; i < items.size(); i++)
            auxSpecial_Attack += items.get(i).Special_Attack;
        return auxSpecial_Attack;
    }

    public int getDefense() {
        int auxDefense = Defense;
        for(int i = 0; i < items.size(); i++)
            auxDefense += items.get(i).Defense;
        return auxDefense;
    }

    public int getSpecial_Defense() {
        int auxSpecial_Defense = Special_Defense;
        for(int i = 0; i < items.size(); i++)
            auxSpecial_Defense += items.get(i).Special_Defense;
        return auxSpecial_Defense;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public String getName() {
        return name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getStun() {
        return stun;
    }
    public void setStun(int stun) {
        this.stun = stun;
    }

    public Pokemon(PokemonBuilder pokemonBuilder){
        name = pokemonBuilder.name;
        HP = pokemonBuilder.HP;
        maxHP = pokemonBuilder.maxHP;
        maxHP = pokemonBuilder.maxHP;
        Attack = pokemonBuilder.Attack;
        Special_Attack = pokemonBuilder.Special_Attack;
        Defense = pokemonBuilder.Defense;
        Special_Defense = pokemonBuilder.Special_Defense;
        items = pokemonBuilder.items;
        abilities = pokemonBuilder.abilities;
    }

    public static class PokemonBuilder{
        String name;
        int HP;
        int maxHP;
        int Attack;
        int Special_Attack;
        int Defense;
        int Special_Defense;
        List<Items> items = new ArrayList<>();
        List<Abilities> abilities = new ArrayList<>();
        public PokemonBuilder name (String name){
            this.name = name;
            return this;}
        public PokemonBuilder HP(int HP){
            this.HP = HP;
            this.maxHP = HP;
            return this;}
        public PokemonBuilder maxHP(int maxHP){
            this.maxHP = maxHP;
            return this;}
        public PokemonBuilder Attack(int Attack){
            this.Attack = Attack;
            return this;}

        public PokemonBuilder Special_Attack(int Special_Attack){
            this.Special_Attack = Special_Attack;
            return this;}

        public PokemonBuilder Defense(int Defense){
            this.Defense = Defense;
            return this;}

        public PokemonBuilder Special_Defense(int Special_Defense){
            this.Special_Defense = Special_Defense;
            return this;}

        public PokemonBuilder items(Items items){
            this.items.add(items);
            return this;}

        public PokemonBuilder abilities(Abilities abilities){
            this.abilities.add(abilities);
            return this;}

        public Pokemon build(){ return new Pokemon(this); }
    }
}

