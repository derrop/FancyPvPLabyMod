package com.github.derrop.labymod.addons.fancypvp.parse;

import com.github.derrop.labymod.addons.fancypvp.log.LogEntry;
import com.github.derrop.labymod.addons.fancypvp.log.TextLogEntryWithIcon;
import com.github.derrop.labymod.addons.fancypvp.parse.chat.ChatPattern;
import net.labymod.utils.Material;
import net.minecraft.item.ItemStack;

import java.util.function.Function;
import java.util.regex.Matcher;

public class SimpleChatParserWithIcon extends SimpleChatParser {

    private ItemStack item;

    public SimpleChatParserWithIcon(String name, Function<Matcher, String> resultFunction, Material material) {
        super(name, resultFunction);
        this.item = material.createItemStack();
    }

    @Override
    public LogEntry parseChatMessage(ChatPattern pattern, Matcher matcher, String rawMessage, String message) {
        String result = super.resultFunction.apply(matcher);
        return result == null ? null : new TextLogEntryWithIcon(result, this.item);
    }
}
