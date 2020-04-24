package com.github.derrop.labymod.addons.fancypvp;

import com.github.derrop.labymod.addons.fancypvp.listener.ChatListener;
import com.github.derrop.labymod.addons.fancypvp.listener.LogRenderListener;
import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import com.github.derrop.labymod.addons.fancypvp.parse.ChatParser;
import com.github.derrop.labymod.addons.fancypvp.parse.SimpleChatParserWithIcon;
import com.github.derrop.labymod.addons.fancypvp.parse.chat.ChatPattern;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.labymod.api.LabyModAddon;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;
import net.minecraft.scoreboard.ScorePlayerTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PvPAddon extends LabyModAddon {

    private final Gson gson = new Gson();
    private final Collection<LogEntry> activeLogEntries = new ArrayList<>();

    private boolean enabled = true;

    private ChatPattern[] patterns = new ChatPattern[0];

    private ChatParser[] chatParsers = new ChatParser[]{
            new SimpleChatParserWithIcon("kill", matcher -> this.getPrefix(matcher.group(1)) + matcher.group(1) + " §7by " + this.getPrefix(matcher.group(2)) + matcher.group(2), Material.IRON_SWORD),
            new SimpleChatParserWithIcon("death", matcher -> this.getPrefix(matcher.group(1)) + matcher.group(1), Material.RED_ROSE)
    };

    public Collection<LogEntry> getActiveLogEntries() {
        return this.activeLogEntries;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public ChatPattern[] getCurrentPatterns() {
        ServerData server = super.getApi().getCurrentServer();
        if (server == null) {
            return new ChatPattern[0];
        }
        return Arrays.stream(this.patterns)
                .filter(pattern -> pattern.matchesServer(server.getIp()))
                .toArray(ChatPattern[]::new);
    }

    public ChatParser[] getChatParsers() {
        return this.chatParsers;
    }

    private String getPrefix(String username) {
        ScorePlayerTeam team = LabyModCore.getMinecraft().getWorld().getScoreboard().getPlayersTeam(username);
        return team == null ? "" : team.getColorPrefix();
    }

    @Override
    public void onEnable() {
        super.getApi().registerForgeListener(new LogRenderListener(this));
        super.getApi().getEventManager().register(new ChatListener(this));
    }

    @Override
    public void loadConfig() {
        JsonObject config = super.getConfig();
        if (!config.has("patterns")) {
            config.add("patterns", this.gson.toJsonTree(ChatPattern.DEFAULTS));
            super.saveConfig();
        }

        this.patterns = this.gson.fromJson(config.get("patterns"), ChatPattern[].class);
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new BooleanElement("Enabled", this, new ControlElement.IconData(Material.IRON_SWORD), "enabled", true).addCallback(enabled -> this.enabled = enabled));
    }
}
