
package mage.cards.s;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.target.Target;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author fireshoes
 */
public final class SkeletonShard extends CardImpl {
    
    private static final FilterCard filter = new FilterCard("artifact creature card from your graveyard");
    
    static {
        filter.add(CardType.ARTIFACT.getPredicate());
        filter.add(CardType.CREATURE.getPredicate());
    }

    public SkeletonShard(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{3}");

        // {3}, {tap} or {B}, {tap}: Return target artifact creature card from your graveyard to your hand.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ReturnToHandTargetEffect(), new ManaCostsImpl("{3}"));
        ability.addCost(new TapSourceCost());
        Target target = new TargetCardInYourGraveyard(filter);
        ability.addTarget(target);
        this.addAbility(ability);
        
        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ReturnToHandTargetEffect(), new ManaCostsImpl("{B}"));
        ability.addCost(new TapSourceCost());
        target = new TargetCardInYourGraveyard(filter);
        ability.addTarget(target);
        this.addAbility(ability);
    }

    private SkeletonShard(final SkeletonShard card) {
        super(card);
    }

    @Override
    public SkeletonShard copy() {
        return new SkeletonShard(this);
    }
}
