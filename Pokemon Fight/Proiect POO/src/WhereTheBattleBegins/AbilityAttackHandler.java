package WhereTheBattleBegins;

import Antrenor.Pokemon;

public class AbilityAttackHandler extends AttackHandler {
    @Override
    protected boolean AcceptMove(MoveDTO move)
    {
        return (move.abilityUsed != null);
    }

    @Override
    protected AttackResultDTO HandleDamage(Pokemon attacker, MoveDTO attackMove, Pokemon defender, MoveDTO defendMove) {
        attackMove.abilityUsed.CountCooldown();
        AttackResultDTO attackResult = AttackResultDTO.AbilityUsed(attackMove.abilityUsed);
        attackResult.AddAttackLog("\t\t" + attacker.getName() + " attacked " + defender.getName() + " using " + attackMove.abilityUsed.toString() + ".\n");
        return attackResult;
    }
}
