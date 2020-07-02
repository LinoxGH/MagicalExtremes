package io.github.linoxgh.magicalextremes.Configuration;

public enum ConfigType {

    ITEMS("items.yml");

    private final String filename;
    private Config cfg;

    ConfigType(String filename) {
        this.filename = filename;
        loadConfig();
    }

    public void loadConfig() {
        cfg = Config.loadConfiguration(filename);
    }
    public Config getConfig() {
        return cfg;
    }
}
