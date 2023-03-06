package Antrenor.PokemonAddons;

public class Abilities {
    public int damage;
    public int dodge;
    public int cooldown;
    public int stun;
    public int cooldownDecrease;

    public Abilities(AbilitiesBuilder abilitiesBuilder){
        damage = abilitiesBuilder.damage;
        dodge = abilitiesBuilder.dodge;
        cooldown = abilitiesBuilder.cooldown;
        stun = abilitiesBuilder.stun;
        cooldownDecrease = 0;
    }

    public String toString()
    {
        return "Ability{dmg="+this.damage+", stun="+stun+"}";
    }

    public void CountCooldown(){
        cooldownDecrease = cooldown;}

    public Boolean Usable(){
        if (cooldownDecrease != 0){ return false; }
        else return true;
    }
    public void DecreaseCoolddown(){
        if(cooldownDecrease > 0)
            cooldownDecrease--;
    }


    public static class AbilitiesBuilder{
        int damage;
        int dodge;
        int cooldown;
        int stun;

        public AbilitiesBuilder damage(int damage){
            this.damage = damage;
            return this;}
        public AbilitiesBuilder dodge(int dodge){
            this.dodge = dodge;
            return this;}
        public AbilitiesBuilder cooldown(int cooldown){
            this.cooldown = cooldown;
            return this;}
        public AbilitiesBuilder stun(int stun){
            this.stun = stun;
            return this;}
        public Abilities build(){ return new Abilities(this); }
    }
}
