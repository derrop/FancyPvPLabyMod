package com.github.derrop.labymod.addons.fancypvp.parse;

import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import com.github.derrop.labymod.addons.fancypvp.parse.chat.ChatPattern;

import java.util.regex.Matcher;

public abstract class ChatParser {

    private String name;

    public ChatParser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // rawMessage -> with color codes
    // message -> without color codes
    public abstract LogEntry parseChatMessage(ChatPattern pattern, Matcher matcher, String rawMessage, String message);

}
