package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.dynamicvalue.common.GreatestPowerAmongControlledCreaturesValue;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.SetPowerSourceEffect;
import mage.abilities.keyword.BoastAbility;
import mage.constants.*;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author weirddan455
 */
public final class ArniBrokenbrow extends CardImpl {

    public ArniBrokenbrow(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}");

        this.addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.BERSERKER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // Boast — {1}: You may change Arni Brokenbrow's base power to 1 plus the greatest power among creatures you control until end of turn.
        this.addAbility(new BoastAbility(new ArniBrokenbrowEffect(), new GenericManaCost(1)));
    }

    private ArniBrokenbrow(final ArniBrokenbrow card) {
        super(card);
    }

    @Override
    public ArniBrokenbrow copy() {
        return new ArniBrokenbrow(this);
    }
}

class ArniBrokenbrowEffect extends OneShotEffect {

    public ArniBrokenbrowEffect() {
        super(Outcome.BoostCreature);
        staticText = "you may change {this}'s base power to 1 plus the greatest power among creatures you control until end of turn";
    }

    private ArniBrokenbrowEffect(final ArniBrokenbrowEffect effect) {
        super(effect);
    }

    @Override
    public ArniBrokenbrowEffect copy() {
        return new ArniBrokenbrowEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        MageObject mageObject = game.getObject(source.getSourceId());
        if (controller != null && mageObject != null) {
            int power = GreatestPowerAmongControlledCreaturesValue.instance.calculate(game, source, this) + 1;
            if (controller.chooseUse(outcome,"Change base power of " + mageObject.getLogName() + " to "
                    + power + " until end of turn?", source, game
            )) {
                game.addEffect(new SetPowerSourceEffect(StaticValue.get(power), Duration.EndOfTurn), source);
                return true;
            }
        }
        return false;
    }
}
