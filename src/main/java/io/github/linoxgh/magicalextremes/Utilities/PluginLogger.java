package io.github.linoxgh.magicalextremes.Utilities;

import io.github.linoxgh.magicalextremes.MagicalExtremesPlugin;

public class PluginLogger {

    public static void error(String... messages) {
        for (String message : messages) {
            MagicalExtremesPlugin.getInstance().getLogger().severe(message);
        }
    }
}
