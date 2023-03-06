package WhereTheBattleBegins;

import Antrenor.Pokemon;
import WhereTheBattleBegins.MoveDTO;
import java.util.Arrays;
import java.util.List;

public abstract class AttackHandler {
    protected abstract boolean AcceptMove(MoveDTO move);
    protected abstract AttackResultDTO HandleDamage(Pokemon attacker, MoveDTO attackMove, Pokemon defender, MoveDTO defendMove);

    private AttackHandler ChooseAttackHandler(MoveDTO move) {
        List<AttackHandler> attackHandlers = Arrays.asList(new BasicAttackHandler(), new SpcialAttackHandler(), new AbilityAttackHandler());
        for(AttackHandler attackHandler : attackHandlers)
            if(attackHandler.AcceptMove(move))
                return attackHandler;
        return null;
    }

    public AttackResultDTO HandleAttack(Pokemon attacker, MoveDTO attackMove, Pokemon defender, MoveDTO defendMove) {
        if(attacker.getStun() != 0) {
            attacker.setStun(0);
            AttackResultDTO attackResult = AttackResultDTO.NothingHappened();
            attackResult.AddAttackLog( "\t\t" + attacker.getName() + " cannot attack because it is stunned.\n");
            return  attackResult;
        }

        if( defendMove.HasDoged() ) {
            if( attackMove.abilityUsed != null )
                attackMove.abilityUsed.CountCooldown();

            AttackResultDTO attackResult = AttackResultDTO.NothingHappened();
            attackResult.AddAttackLog("\t\t" + attacker.getName() + " attacked " + defender.getName() + ", but it dodged.\n");
            return  attackResult;
        }

        AttackHandler attackHandler = ChooseAttackHandler(attackMove);
        return attackHandler.HandleDamage(attacker, attackMove, defender, defendMove);
    }
}
