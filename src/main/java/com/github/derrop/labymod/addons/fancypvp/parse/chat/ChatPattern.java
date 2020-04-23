package com.github.derrop.labymod.addons.fancypvp.parse.chat;

import java.util.Arrays;

public class ChatPattern {

    public static final ChatPattern[] DEFAULTS;

    static {
        DEFAULTS = new ChatPattern[]{
                new ChatPattern("death", new String[]{"*.claymc.net", "claymc.net"}, ""),
                new ChatPattern("kill", new String[]{"*.claymc.net", "claymc.net"}, "")
        };
    }

    private String name;
    private String[] serverAddresses;
    private String[] regex;

    public ChatPattern(String name, String[] serverAddresses, String... regex) {
        this.name = name;
        this.serverAddresses = serverAddresses;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public String[] getServerAddresses() {
        return serverAddresses;
    }

    public String[] getRegex() {
        return regex;
    }

    public boolean matchesServer(String address) {
        return Arrays.stream(this.serverAddresses).anyMatch(s -> address.matches(s.replace("*", "(.*)")));
    }

    public boolean matches(String content) {
        return Arrays.stream(this.regex).anyMatch(content::matches);
    }

}
