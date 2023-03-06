package WhereTheBattleBegins;

import Antrenor.PokemonAddons.Abilities;

public class AttackResultDTO {
    public int damage;
    public int stunned;
    public String attackLog;

    private AttackResultDTO(int damage, int stunned) {
        this.damage = damage;
        this.stunned = stunned;
    }

    public static AttackResultDTO NothingHappened()
    {
        return new AttackResultDTO(0, 0);
    }

    public static AttackResultDTO DamageDealt(int damage)
    {
        return new AttackResultDTO(damage, 0);
    }

    public static AttackResultDTO AbilityUsed(Abilities ability) {
        return new AttackResultDTO(ability.damage, ability.stun);
    }

    public void AddAttackLog(String log)
    {
        attackLog = log;
    }
}
