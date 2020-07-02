package io.github.linoxgh.magicalextremes.Implementation.Listeners;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;

import io.github.linoxgh.magicalextremes.API.Data.ChunkStorage;
import io.github.linoxgh.magicalextremes.API.Data.DataStorage;
import io.github.linoxgh.magicalextremes.MagicalExtremesPlugin;

public class ChunkListener implements Listener {

    private final Map<Biome, Double> biomeMultipliers = new EnumMap<>(Biome.class);

    public ChunkListener(MagicalExtremesPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        for (Biome biome : Biome.values()) {
            switch (biome) {
                case MOUNTAINS:
                case SNOWY_MOUNTAINS:
                case MOUNTAIN_EDGE:
                    biomeMultipliers.put(biome, 1.10);
                    break;

                case DESERT:
                case DESERT_HILLS:
                case DESERT_LAKES:
                    biomeMultipliers.put(biome, 1.15);
                    break;

                case NETHER_WASTES:
                case SOUL_SAND_VALLEY:
                    biomeMultipliers.put(biome, 1.20);
                    break;

                case END_BARRENS:
                case END_MIDLANDS:
                case END_HIGHLANDS:
                case THE_END:
                case SMALL_END_ISLANDS:
                case THE_VOID:
                    biomeMultipliers.put(biome, 1.30);
                    break;

                default:
                    biomeMultipliers.put(biome, 1.00);
            }
        }
    }

    @EventHandler
    public void onChunkPopulate(ChunkPopulateEvent event) {
        addMana(event.getChunk());
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        ChunkStorage chunkStorage = DataStorage.getInstance().getChunkStorage();
        if (chunkStorage.getChunkMana(event.getChunk()) < 0.05F) {
            addMana(event.getChunk());
        }
    }

    private void addMana(Chunk chunk) {
        float mana = 0F;
        for (int i = 1; i < chunk.getWorld().getMaxHeight();) {
            double gaussian = ThreadLocalRandom.current().nextGaussian();
            mana += (gaussian * 50 * biomeMultipliers.get(chunk.getBlock(chunk.getX() * 16 + 1, i, chunk.getZ() * 16 + 1).getBiome()));
            i += 16;
        }
        DataStorage.getInstance().getChunkStorage().setChunkMana(chunk, mana);
    }
}
