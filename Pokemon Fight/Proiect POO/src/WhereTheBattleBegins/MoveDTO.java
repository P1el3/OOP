package WhereTheBattleBegins;

import Antrenor.PokemonAddons.Abilities;

public class MoveDTO
{
    public int damage;
    public int specialDamage;
    public Abilities abilityUsed;

    public MoveDTO(int damage, int specialDamage, Abilities abilityUsed)
    {
        this.damage = damage;
        this.specialDamage = specialDamage;
        this.abilityUsed = abilityUsed;
    }

    public boolean HasDoged()
    {
        if( abilityUsed == null )
            return false;

        return abilityUsed.dodge != 0;
    }
}
