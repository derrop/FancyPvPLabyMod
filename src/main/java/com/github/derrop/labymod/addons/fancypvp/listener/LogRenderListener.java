package com.github.derrop.labymod.addons.fancypvp.listener;

import com.github.derrop.labymod.addons.fancypvp.PvPAddon;
import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LogRenderListener {

    private PvPAddon addon;

    public LogRenderListener(PvPAddon addon) {
        this.addon = addon;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (!this.addon.isEnabled() || this.addon.getActiveLogEntries().isEmpty()) {
            return;
        }

        DrawUtils utils = LabyMod.getInstance().getDrawUtils();
        int offY = 2;
        for (LogEntry entry : this.addon.getActiveLogEntries()) {
            entry.draw(utils, utils.getWidth(), offY);
            offY += entry.getRequiredHeight(utils) + 3;
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        this.addon.getActiveLogEntries().removeIf(logEntry -> logEntry.getCreationTime() + 10000 < System.currentTimeMillis());
    }

}
