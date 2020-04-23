package com.github.derrop.labymod.addons.fancypvp.log;

import net.labymod.utils.DrawUtils;
import net.minecraft.item.ItemStack;

public class TextLogEntryWithIcon extends TextLogEntry {

    private ItemStack item;

    public TextLogEntryWithIcon(String text, ItemStack item) {
        super(text);
        this.item = item;
    }

    @Override
    public int getRequiredHeight() {
        return 16;
    }

    @Override
    public void draw(DrawUtils utils, int x, int y) {
        super.draw(utils, x, y + 8 - (utils.fontRenderer.FONT_HEIGHT / 2)); // 8 -> half height
        int iconX = x - utils.fontRenderer.getStringWidth(super.text) - 10; // 10 -> space between icon and text
        utils.drawItem(item, iconX - 16, y, ""); // 16 -> width
    }
}
