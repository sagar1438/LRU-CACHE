package com.sagar.cache;

public class ConcurrentMemorySensitiveLRU {
    private final MemorySensitiveLRU lru;

    public ConcurrentMemorySensitiveLRU(int maxEntries, long maxMemory) {
        this.lru = new MemorySensitiveLRU(maxEntries, maxMemory);
    }

    public synchronized int get(int key) {
        return lru.get(key);
    }

    public synchronized void put(int key, int value, long size) {
        lru.put(key, value, size);
    }
}
