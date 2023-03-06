package WhereTheBattleBegins;

import Antrenor.Pokemon;

public class BasicAttackHandler extends AttackHandler {
    @Override
    public boolean AcceptMove(MoveDTO move)
    {
        return (move.damage != 0);
    }

    @Override
    public AttackResultDTO HandleDamage(Pokemon attacker, MoveDTO attackMove, Pokemon defender, MoveDTO defendMove) {
        int actualDamage = attackMove.damage - defender.getDefense();
        actualDamage = Math.max(actualDamage, 0);

        AttackResultDTO attackResult = AttackResultDTO.DamageDealt(actualDamage);
        attackResult.AddAttackLog("\t\t" + attacker.getName() + " attacked " + defender.getName() + " using basic attack (-"+actualDamage+"HP).\n");
        return attackResult;
    }
}
