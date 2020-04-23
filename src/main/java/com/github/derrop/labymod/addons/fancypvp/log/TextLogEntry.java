package com.github.derrop.labymod.addons.fancypvp.log;

import net.labymod.utils.DrawUtils;

public class TextLogEntry extends LogEntry {

    protected String text;

    public TextLogEntry(String text) {
        this.text = text;
    }

    @Override
    public void draw(DrawUtils utils, int x, int y) {
        utils.drawRightString(this.text, x - 5, y);
    }
}
