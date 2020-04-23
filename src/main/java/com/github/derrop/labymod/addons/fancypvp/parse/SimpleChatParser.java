package com.github.derrop.labymod.addons.fancypvp.parse;

import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import com.github.derrop.labymod.addons.fancypvp.log.TextLogEntry;
import com.github.derrop.labymod.addons.fancypvp.parse.chat.ChatPattern;

import java.util.function.Function;
import java.util.regex.Matcher;

public class SimpleChatParser extends ChatParser {

    protected Function<Matcher, String> resultFunction;

    public SimpleChatParser(String name, Function<Matcher, String> resultFunction) {
        super(name);
        this.resultFunction = resultFunction;
    }

    @Override
    public LogEntry parseChatMessage(ChatPattern pattern, Matcher matcher, String rawMessage, String message) {
        String result = this.resultFunction.apply(matcher);
        return result != null ? new TextLogEntry(result) : null;
    }
}
