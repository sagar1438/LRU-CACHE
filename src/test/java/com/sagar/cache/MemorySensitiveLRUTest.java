package com.sagar.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemorySensitiveLRUTest {

    @Test
    public void testBasicPutAndGet() {
        MemorySensitiveLRU lru = new MemorySensitiveLRU(3, 50);
        lru.put(1, 10, 10);
        lru.put(2, 20, 15);

        assertEquals(10, lru.get(1)); // key 1 present
        assertEquals(20, lru.get(2)); // key 2 present
    }

    @Test
    public void testEvictionByEntryCount() {
        MemorySensitiveLRU lru = new MemorySensitiveLRU(2, 100);
        lru.put(1, 10, 10);
        lru.put(2, 20, 20);
        lru.put(3, 30, 30); // should evict key 1 (maxEntries=2)

        assertEquals(-1, lru.get(1)); // key 1 evicted
        assertEquals(20, lru.get(2));
        assertEquals(30, lru.get(3));
    }

    @Test
    public void testEvictionByMemoryLimit() {
        MemorySensitiveLRU lru = new MemorySensitiveLRU(10, 25);
        lru.put(1, 10, 10);
        lru.put(2, 20, 12);
        lru.put(3, 30, 12); // total = 34 > 25 → evicts oldest (key 1)

        assertEquals(-1, lru.get(1)); // evicted
        assertEquals(20, lru.get(2));
        assertEquals(30, lru.get(3));
    }

    @Test
    public void testUpdateEntry() {
        MemorySensitiveLRU lru = new MemorySensitiveLRU(2, 50);
        lru.put(1, 10, 10);
        lru.put(1, 99, 5); // update same key with new value & memory size

        assertEquals(99, lru.get(1));
    }
}
