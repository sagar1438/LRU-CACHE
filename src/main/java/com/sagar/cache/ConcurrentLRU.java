package com.sagar.cache;

public class ConcurrentLRU {
    private final LRU lru;

    public ConcurrentLRU(int capacity) {
        this.lru = new LRU(capacity);
    }

    public synchronized int get(int key) {
        return lru.get(key);
    }

    public synchronized void put(int key, int value) {
        lru.put(key, value);
    }
}
