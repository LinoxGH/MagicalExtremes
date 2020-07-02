package io.github.linoxgh.magicalextremes.API.Data;

public class DataStorage {

    private static DataStorage instance;

    private final ChunkStorage chunkStorage;

    public DataStorage() {
        instance = this;

        chunkStorage = new ChunkStorage();
    }

    public ChunkStorage getChunkStorage() {
        return chunkStorage;
    }

    public static DataStorage getInstance() {
        return instance;
    }

    public void saveData() {
        chunkStorage.saveData();
    }
}
