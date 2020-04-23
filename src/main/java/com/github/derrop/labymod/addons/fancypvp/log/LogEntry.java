package com.github.derrop.labymod.addons.fancypvp.log;

import net.labymod.utils.DrawUtils;

public abstract class LogEntry {

    private long creationTime = System.currentTimeMillis();

    public long getCreationTime() {
        return this.creationTime;
    }

    public int getRequiredHeight() {
        return 10;
    }

    public abstract void draw(DrawUtils utils, int x, int y);

}
