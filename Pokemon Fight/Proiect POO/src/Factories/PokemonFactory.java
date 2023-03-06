package Factories;

import Antrenor.Pokemon;
import Antrenor.PokemonAddons.Abilities;
import Antrenor.PokemonAddons.Items;

import static Factories.ItemFactory.createItem;

public class PokemonFactory {
    public static PokemonFactory pokemonFactory;
    private PokemonFactory(){}
    public static PokemonFactory GetInstance() {
        if (pokemonFactory == null) {
            pokemonFactory = new PokemonFactory();
        }
        return pokemonFactory;
    }
    public static Pokemon createPokemon(String name, String item1, String item2, String item3){
        Abilities ability1, ability2;
        Items item11 = createItem(item1);
        Items item21 = createItem(item2);
        Items item31 = createItem(item3);
        switch(name){
            case("Neutrel1") : return new Pokemon.PokemonBuilder().HP(10).Attack(3).Special_Attack(0).Defense(1).Special_Defense(1).abilities(null).abilities(null).build();
            case("Neutrel2") : return new Pokemon.PokemonBuilder().HP(20).Attack(4).Special_Attack(0).Defense(1).Special_Defense(1).abilities(null).abilities(null).build();
            case("Pikachu") :
                ability1 = new Abilities.AbilitiesBuilder().damage(6).stun(0).dodge(0).cooldown(4).build();
                ability2 = new Abilities.AbilitiesBuilder().damage(4).stun(1).dodge(1).cooldown(5).build();
                return new Pokemon.PokemonBuilder().name(name).HP(35).Attack(0).Special_Attack(4).Defense(2).Special_Defense(3).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Bulbasaur") :
                ability1 = new Abilities.AbilitiesBuilder().damage(6).stun(0).dodge(0).cooldown(4).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(5).stun(0).dodge(0).cooldown(3).build();
                return new Pokemon.PokemonBuilder().name(name).HP(42).Attack(0).Special_Attack(5).Defense(3).Special_Defense(1).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Charmander") :
                ability1 = new Abilities.AbilitiesBuilder().damage(4).stun(1).dodge(0).cooldown(4).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(7).stun(0).dodge(0).cooldown(6).build();
                return new Pokemon.PokemonBuilder().name(name).HP(50).Attack(4).Special_Attack(0).Defense(3).Special_Defense(2).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Squirtle") :
                ability1 = new Abilities.AbilitiesBuilder().damage(4).stun(0).dodge(0).cooldown(3).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(2).stun(1).dodge(0).cooldown(2).build();
                return new Pokemon.PokemonBuilder().name(name).HP(60).Attack(0).Special_Attack(3).Defense(5).Special_Defense(5).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Snorlax") :
                ability1 = new Abilities.AbilitiesBuilder().damage(4).stun(1).dodge(0).cooldown(5).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(0).stun(0).dodge(1).cooldown(5).build();
                return new Pokemon.PokemonBuilder().name(name).HP(62).Attack(3).Special_Attack(0).Defense(6).Special_Defense(4).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Vulpix") :
                ability1 = new Abilities.AbilitiesBuilder().damage(8).stun(1).dodge(0).cooldown(6).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(2).stun(0).dodge(1).cooldown(5).build();
                return new Pokemon.PokemonBuilder().name(name).HP(36).Attack(5).Special_Attack(0).Defense(2).Special_Defense(4).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Eevee") :
                ability1 = new Abilities.AbilitiesBuilder().damage(5).stun(0).dodge(0).cooldown(3).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(3).stun(1).dodge(0).cooldown(3).build();
                return new Pokemon.PokemonBuilder().name(name).HP(39).Attack(0).Special_Attack(4).Defense(3).Special_Defense(3).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Jigglepuff") :
                ability1 = new Abilities.AbilitiesBuilder().damage(4).stun(1).dodge(0).cooldown(4).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(3).stun(1).dodge(0).cooldown(4).build();
                return new Pokemon.PokemonBuilder().name(name).HP(34).Attack(4).Special_Attack(0).Defense(2).Special_Defense(3).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Meowth") :
                ability1 = new Abilities.AbilitiesBuilder().damage(5).stun(0).dodge(1).cooldown(4).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(1).stun(0).dodge(1).cooldown(3).build();
                return new Pokemon.PokemonBuilder().name(name).HP(41).Attack(3).Special_Attack(0).Defense(2).Special_Defense(3).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            case("Psyduck") :
                ability1 = new Abilities.AbilitiesBuilder().damage(2).stun(0).dodge(0).cooldown(4).build();
                ability2 = new  Abilities.AbilitiesBuilder().damage(2).stun(1).dodge(0).cooldown(5).build();
                return new Pokemon.PokemonBuilder().name(name).HP(43).Attack(3).Special_Attack(0).Defense(3).Special_Defense(3).abilities(ability1).abilities(ability2).items(item11).items(item21).items(item31).build();
            default:throw new IllegalArgumentException("Nu exista pokemonul cu numele: " + name);
        }
    }
}
