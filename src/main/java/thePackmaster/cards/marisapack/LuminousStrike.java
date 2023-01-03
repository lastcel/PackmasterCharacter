package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.marisapack.AmplifyPatches;
import thePackmaster.util.Wiz;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LuminousStrike extends AbstractPackmasterCard implements AmplifyCard{
    public final static String ID = makeID(LuminousStrike.class.getSimpleName());
    private static final int DMG = 6, UPG_DMG = 1;
    private static final int MAGIC = 3, UPG_MAGIC = 1;

    public LuminousStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new ExplosionSmallEffect(m.hb.cX, m.hb.cY));
    }

    @Override
    public int getAmplifyCost() {
        return 2;
    }

    @Override
    public void applyPowers() {
        boolean amp = AmplifyPatches.shouldAmplify(this);
        int bd = baseDamage;
        if(amp) {
            baseDamage = baseDamage + EnergyPanel.totalCount * magicNumber;
        }
        super.applyPowers();
        if(amp) isDamageModified = true;
        baseDamage = bd;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        boolean amp = AmplifyPatches.shouldAmplify(this);
        int bd = baseDamage;
        if(amp) {
            baseDamage = baseDamage + EnergyPanel.totalCount * magicNumber;
        }
        super.calculateCardDamage(mo);
        if(amp) isDamageModified = true;
        baseDamage = bd;
    }
}
