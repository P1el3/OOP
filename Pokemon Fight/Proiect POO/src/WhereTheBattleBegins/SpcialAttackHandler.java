package WhereTheBattleBegins;

import Antrenor.Pokemon;

public class SpcialAttackHandler extends AttackHandler
{

    @Override
    public boolean AcceptMove(MoveDTO move)
    {
        return (move.specialDamage != 0);
    }

    @Override
    public AttackResultDTO HandleDamage(Pokemon attacker, MoveDTO attackMove, Pokemon defender, MoveDTO defendMove)
    {
        int actualDamage = attackMove.specialDamage - defender.getSpecial_Defense();
        actualDamage = Math.max(actualDamage, 0);

        AttackResultDTO attackResult = AttackResultDTO.DamageDealt(actualDamage);
        attackResult.AddAttackLog("\t\t" + attacker.getName() + " attacked " + defender.getName() + " using special attack (-"+actualDamage+"HP).\n");
        return attackResult;
    }
}