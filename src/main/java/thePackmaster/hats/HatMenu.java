package thePackmaster.hats;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

import static thePackmaster.hats.Hats.currentHat;

public class HatMenu {

    public boolean isOpen = false;

    private final DropdownMenu dropdown;
    public final ArrayList<String> hats = new ArrayList<>();

    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HatMenu")).TEXT;
    private static final TextureRegion MENU_BG = new TextureRegion(ImageMaster.loadImage("img/ModPanelBg.png"));

    //positions
    private static final float BG_X_SCALE = Settings.scale * 0.275f;
    private static final float BG_Y_SCALE = Settings.scale * 0.8f;
    private static final float BG_X = 525f * Settings.xScale;
    private static final float BG_Y = Settings.HEIGHT - 40f * Settings.yScale - MENU_BG.getRegionHeight() * BG_Y_SCALE;
    private static final float DROPDOWN_X = 590f * Settings.xScale;
    private static final float DROPDOWN_Y = Settings.HEIGHT - 160f * Settings.yScale;
    private static final float PREVIEW_X = BG_X + (210 * Settings.scale);
    private static final float PREVIEW_Y = BG_Y + (200 * Settings.scale);

    public static AbstractPlayer dummy;

    private static final boolean UNLOCK_ALL_HATS = false;

    public static int currentHatIndex;


    public HatMenu() {
        ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();

        ArrayList<String> optionNames = new ArrayList<>();
        optionNames.add(TEXT[0]);
        for (AbstractCardPack s : SpireAnniversary5Mod.unfilteredAllPacks) {
            if (Gdx.files.internal(Hats.getImagePathFromHatID(s.packID)).exists()) {
                if (UNLOCK_ALL_HATS || unlockedHats.contains(s.packID)) {
                    hats.add(s.packID);
                    optionNames.add(s.getHatName());
                } else {
                    hats.add(s.packID);
                    optionNames.add(TEXT[1]);
                }
            }
        }

        dropdown = new DropdownMenu(((dropdownMenu, index, s) -> setCurrentHat(index, s)),
                optionNames, FontHelper.tipBodyFont, Settings.CREAM_COLOR);


        dummy = new ThePackmaster("", ThePackmaster.Enums.THE_PACKMASTER);
        dummy.drawX = PREVIEW_X;
        dummy.drawY = PREVIEW_Y;

        dummy.animX = dummy.animY = 0;

        setCurrentHat(0, optionNames.get(0));
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    private void open() {
        isOpen = true;
    }

    private void close() {
        isOpen = false;
    }

    public void setCurrentHat(int index, String name) {
        currentHatIndex = index;
        if (index == 0) {
            BaseMod.logger.info("Removing hat.");
            Hats.removeHat(false);
        } else if (name.equals(TEXT[1])) {
            BaseMod.logger.info("Selected a locked hat.");
            Hats.addHat(false, "LockedHat");
        } else {
            BaseMod.logger.info("Add new hat at index " + index);
            currentHat = hats.get(index - 1);
            Hats.addHat(false, currentHat);
        }
    }


    public void update() {
        dropdown.update();
        FontHelper.cardTitleFont.getData().setScale(1f);
    }

    public void render(SpriteBatch sb) {
        sb.draw(MENU_BG, BG_X, BG_Y, 0f, 0f, MENU_BG.getRegionWidth(), MENU_BG.getRegionHeight(), BG_X_SCALE, BG_Y_SCALE, 0f);

        dummy.renderPlayerImage(sb);

        dropdown.render(sb, DROPDOWN_X, DROPDOWN_Y);
    }

}
