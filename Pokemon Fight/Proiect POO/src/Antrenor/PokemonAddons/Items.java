package Antrenor.PokemonAddons;

public class Items {
    public String name;
    public int HP;
    public int Attack;
    public int Special_Attack;
    public int Defense;
    public int Special_Defense;

    public Items() {}

    public Items(ItemsBuilder itemsBuilder){
        name = itemsBuilder.name;
        HP = itemsBuilder.HP;
        Attack = itemsBuilder.Attack;
        Special_Attack = itemsBuilder.Special_Attack;
        Defense = itemsBuilder.Defense;
        Special_Defense = itemsBuilder.Special_Defense;
    }

    public static class ItemsBuilder{
        String name;
        int HP = 0;
        int Attack = 0;
        int Special_Attack = 0;
        int Defense = 0;
        int Special_Defense = 0;

        public ItemsBuilder name(String name){
            this.name = name;
            return this;}
        public ItemsBuilder HP(int HP){
            this.HP = HP;
            return this;}
        public ItemsBuilder Attack(int Attack){
            this.Attack = Attack;
            return this;}
        public ItemsBuilder Special_Attack(int Special_Attack){
            this.Special_Attack = Special_Attack;
            return this;}
        public ItemsBuilder Defense(int Defense){
            this.Defense = Defense;
            return this;}
        public ItemsBuilder Special_Defense(int Special_Defense){
            this.Special_Defense = Special_Defense;
            return this;}
        public Items build(){ return new Items(this); }
    }

}
