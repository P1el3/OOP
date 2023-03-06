package WhereTheBattleBegins;

import Antrenor.Pokemon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WhereTheBattleBegins implements Runnable{
    Antrenor antrenor1;
    Antrenor antrenor2;
    StringBuilder logger;

    String path;

    public WhereTheBattleBegins(Antrenor antrenor1, Antrenor antrenor2, String path) {
        this.antrenor1 = antrenor1;
        this.antrenor2 = antrenor2;
        logger = new StringBuilder("");
        this.path = path;
    }

    @Override
    public void run() {
        while (antrenor1.canFight() && antrenor2.canFight()) {
            Pokemon pokemon1 = antrenor1.PokemonIChoseYou();
            Pokemon pokemon2 = antrenor2.PokemonIChoseYou();

            while (pokemon1.getHP() > 0 && pokemon2.getHP() > 0) {
                MoveDTO move1 = antrenor1.movement(pokemon2);
                MoveDTO move2 = antrenor2.movement(pokemon1);
                AttackHandler attack1 = new BasicAttackHandler();
                AttackHandler attack2 = new BasicAttackHandler();

                AttackResultDTO attatck1Result = attack1.HandleAttack(pokemon1, move1, pokemon2, move2);
                AttackResultDTO attatck2Result = attack2.HandleAttack(pokemon2, move2, pokemon1, move1);

                pokemon1.DecreaseHP(attatck2Result.damage);
                if (attatck2Result.stunned != 0)
                    pokemon1.setStun(1);

                pokemon2.DecreaseHP(attatck1Result.damage);
                if (attatck1Result.stunned != 0)
                    pokemon2.setStun(1);

                for (int i = 0; i < pokemon1.getAbilities().size(); i++)
                    pokemon1.getAbilities().get(i).DecreaseCoolddown();
                for (int i = 0; i < pokemon2.getAbilities().size(); i++)
                    pokemon2.getAbilities().get(i).DecreaseCoolddown();

                logger.append(attatck1Result.attackLog);
                logger.append(attatck2Result.attackLog);
                logger.append(pokemon1.getName() + "{HP=" + pokemon1.getHP() + ", stunned=" + pokemon1.getStun() + "}\n");
                logger.append(pokemon2.getName() + "{HP=" + pokemon2.getHP() + ", stunned=" + pokemon2.getStun() + "}\n");
            }

            if (pokemon1.getHP() > 0) {
                logger.append(pokemon1.getName() + " won the match.\n");
                pokemon1.setStun(0);
            } else if (pokemon2.getHP() > 0) {
                logger.append(pokemon2.getName() + " won the match.\n");
                pokemon2.setStun(0);
            } else logger.append("Draw, GG.\n");
        }
        if (antrenor1.canFight())
            logger.append(antrenor1.name + " is the winner.\n");
        else if (antrenor2.canFight())
            logger.append(antrenor2.name + " is the winner.\n");
        else logger.append("Nice draw, GG.\n");

        antrenor1.pokemon1.setHP(antrenor1.pokemon1.getMaxHP());
        antrenor1.pokemon2.setHP(antrenor1.pokemon2.getMaxHP());
        antrenor1.pokemon3.setHP(antrenor1.pokemon3.getMaxHP());
        antrenor2.pokemon1.setHP(antrenor2.pokemon1.getMaxHP());
        antrenor2.pokemon2.setHP(antrenor2.pokemon2.getMaxHP());
        antrenor2.pokemon3.setHP(antrenor2.pokemon3.getMaxHP());

        if (path.equals("stdout")) {
            System.out.println(logger.toString());
        }
        else {
            PrintWriter out = null;
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
                out.println(logger.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                assert out != null;
                out.close();
            }
        }
    }
}
