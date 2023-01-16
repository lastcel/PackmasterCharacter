package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dragonwrathpack.Condemn;
import thePackmaster.cards.dragonwrathpack.PenanceShock;

import java.util.ArrayList;

public class DragonwrathPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DragonwrathPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DragonwrathPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Condemn.ID);
        cards.add(PenanceShock.ID);
        return cards;
    }
}