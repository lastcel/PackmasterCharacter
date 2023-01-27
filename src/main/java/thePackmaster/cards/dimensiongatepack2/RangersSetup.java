package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RangersSetup extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("RangersSetup");

    public RangersSetup() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.atb(new PutOnDeckAction(p, p, 2, false));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}