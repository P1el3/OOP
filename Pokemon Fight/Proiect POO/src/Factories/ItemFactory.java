package Factories;

import Antrenor.PokemonAddons.Items;

public class ItemFactory {
    public static ItemFactory itemFactory;
    //Singleton
    private ItemFactory() {}
    public static ItemFactory GetInstance() {
        if (itemFactory == null) {
            itemFactory = new ItemFactory();
        }
        return itemFactory;
    }
    public static Items createItem(String name){
        switch(name){
            case("Scut") : return new Items.ItemsBuilder().name(name).Defense(2).Special_Defense(2).build();
            case("Vesta") : return new Items.ItemsBuilder().name(name).HP(10).build();
            case("Sabiuta") : return new Items.ItemsBuilder().name(name).Attack(3).build();
            case("Bagheta Magica") : return new Items.ItemsBuilder().name(name).Special_Attack(3).build();
            case("Vitamine") : return new Items.ItemsBuilder().name(name).HP(2).Attack(2).Special_Attack(2).build();
            case("Brad de Craciun") : return new Items.ItemsBuilder().name(name).Attack(3).Defense(1).build();
            case("Pelerina") : return  new Items.ItemsBuilder().name(name).Special_Defense(3).build();
            default:throw new IllegalArgumentException("Nu exista item-ul cu numele: " + name);
        }
    }
}
