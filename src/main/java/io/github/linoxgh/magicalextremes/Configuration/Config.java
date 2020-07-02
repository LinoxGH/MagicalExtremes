package io.github.linoxgh.magicalextremes.Configuration;

import java.io.File;
import java.util.Map;

import io.github.linoxgh.magicalextremes.MagicalExtremesPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents a loaded configuration file.
 *
 * @author Linox
 *
 * @see ConfigType
 *
 */
public class Config {
    private final Map<String, Object> values;

    public Config(Map<String, Object> values) {
        this.values = values;
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    /**
     * Loads a {@link Config} from a given file name in the plugin directory
     *
     * @param fileName
     *                  The configuration file name that is to be loaded.
     *
     * @return {@link Config} that is associated with the file.
     */
    public static Config loadConfiguration(String fileName) {
        String dataFolderPath = MagicalExtremesPlugin.getInstance().getDataFolder().getAbsolutePath();
        File file = new File(dataFolderPath + File.separator + fileName);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return new Config(configuration.getValues(true));
    }
}
