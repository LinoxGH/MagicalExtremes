package io.github.linoxgh.magicalextremes.API.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.linoxgh.magicalextremes.Utilities.PluginLogger;

public class ChunkStorage extends Data {
    private final Map<Chunk, Float> chunkMana;

    public ChunkStorage() {
        chunkMana = new HashMap<>();

        loadData();
    }

    /**
     * Sets the mana value of a chunk with the given value.
     *
     * @param chunk
     *              The chunk to set the mana value of
     * @param mana
     *              The mana value to set
     */
    public void setChunkMana(Chunk chunk, Float mana) {
        if (mana == null) {
            chunkMana.remove(chunk);
        } else {
            chunkMana.put(chunk, mana);
        }
    }

    /**
     * Gets the mana value of a chunk.
     *
     * @param chunk
     *              The chunk to get the mana value of
     *
     */
    public Float getChunkMana(Chunk chunk) {
        Float mana = chunkMana.get(chunk);
        return mana != null ? mana : 0F;
    }

    @Override
    public void loadData() {
        ConfigurationSection manaSection = getManaSection(YamlConfiguration.loadConfiguration(getFile()));
        for (String key : manaSection.getKeys(false)) {
            String[] keyParts = key.split("-");

            try {
                Chunk chunk = Bukkit.getWorld(UUID.fromString(keyParts[0])).getChunkAt(Integer.parseInt(keyParts[1]), Integer.parseInt(keyParts[2]));
                String manaS = manaSection.getString(key);
                float mana = manaS == null ? 0F : Float.parseFloat(manaS);

                chunkMana.put(chunk, mana);
            } catch (Exception e) {
                PluginLogger.error("Failed to load the chunk " + key + " from the chunks.yml file.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveData() {
        File dataFile = getFile();
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dataFile);
        ConfigurationSection manaSection = getManaSection(configuration);

        for (Map.Entry<Chunk, Float> chunkManaEntry : chunkMana.entrySet()) {
            manaSection.set(chunkToString(chunkManaEntry.getKey()), chunkManaEntry.getValue().toString());
        }

        try {
            configuration.save(dataFile);
        } catch (IOException e) {
            PluginLogger.error("Could not save the chunk data.");
            e.printStackTrace();
        }

    }

    private File getFile() {
        String dataFolderPath = Bukkit.getWorldContainer().getAbsolutePath();
        return new File(dataFolderPath + File.separator + "chunks.yml");
    }

    private ConfigurationSection getManaSection(YamlConfiguration configuration) {
        ConfigurationSection manaSection = configuration.getConfigurationSection("chunk-mana");
        if (manaSection == null) manaSection = configuration.createSection("chunk-mana");
        return manaSection;
    }

    private String chunkToString(Chunk chunk) {
        return chunk.getWorld().getUID().toString() + "-" + chunk.getX() + "-" + chunk.getZ();
    }
}
