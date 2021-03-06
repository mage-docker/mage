
package mage.cards.q;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.LimitedTimesPerTurnActivatedAbility;
import mage.abilities.costs.common.ReturnToHandChosenControlledPermanentCost;
import mage.abilities.effects.common.UntapTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.target.common.TargetControlledPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author jonubuu
 */
public final class QuirionRanger extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("a Forest");

    static {
        filter.add(SubType.FOREST.getPredicate());
    }

    public QuirionRanger(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{G}");
        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.RANGER);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Return a Forest you control to its owner's hand: Untap target creature. Activate this ability only once each turn.
        Ability ability = new LimitedTimesPerTurnActivatedAbility(Zone.BATTLEFIELD, new UntapTargetEffect(), new ReturnToHandChosenControlledPermanentCost(new TargetControlledPermanent(filter)));
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private QuirionRanger(final QuirionRanger card) {
        super(card);
    }

    @Override
    public QuirionRanger copy() {
        return new QuirionRanger(this);
    }
}
