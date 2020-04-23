package com.github.derrop.labymod.addons.fancypvp.listener;

import com.github.derrop.labymod.addons.fancypvp.PvPAddon;
import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import com.github.derrop.labymod.addons.fancypvp.parse.ChatParser;
import com.github.derrop.labymod.addons.fancypvp.parse.chat.ChatPattern;
import net.labymod.api.events.MessageReceiveEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements MessageReceiveEvent {

    private PvPAddon addon;

    public ChatListener(PvPAddon addon) {
        this.addon = addon;
    }

    @Override
    public boolean onReceive(String rawMessage, String message) {
        for (ChatPattern pattern : this.addon.getCurrentPatterns()) {
            for (String regex : pattern.getRegex()) {
                Matcher matcher = Pattern.compile(regex).matcher(message);
                if (matcher.matches()) {
                    for (ChatParser parser : this.addon.getChatParsers()) {
                        if (parser.getName().equals(pattern.getName())) {
                            System.out.println(parser.getName());
                            System.out.println(matcher.group(1) + " " + matcher.group(2));
                            LogEntry entry = parser.parseChatMessage(pattern, matcher, rawMessage, message);
                            System.out.println(String.valueOf(entry));
                            if (entry != null) {
                                this.addon.getActiveLogEntries().add(entry);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
