package com.sagar.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentMemorySensitiveLRUTest {

    @Test
    void testBasicPutAndGet() {
        ConcurrentMemorySensitiveLRU lru = new ConcurrentMemorySensitiveLRU(3, 50);
        lru.put(1, 10, 10);
        lru.put(2, 20, 15);

        assertEquals(10, lru.get(1));
        assertEquals(20, lru.get(2));
    }

    @Test
    void testEvictionByEntryCount() {
        ConcurrentMemorySensitiveLRU lru = new ConcurrentMemorySensitiveLRU(2, 100);
        lru.put(1, 10, 10);
        lru.put(2, 20, 20);
        lru.put(3, 30, 30); // should evict key 1

        assertEquals(-1, lru.get(1));
        assertEquals(20, lru.get(2));
        assertEquals(30, lru.get(3));
    }

    @Test
    void testEvictionByMemoryLimit() {
        ConcurrentMemorySensitiveLRU lru = new ConcurrentMemorySensitiveLRU(10, 25);
        lru.put(1, 10, 10);
        lru.put(2, 20, 12);
        lru.put(3, 30, 12); // total memory > 25 → evict oldest

        assertEquals(-1, lru.get(1));
        assertEquals(20, lru.get(2));
        assertEquals(30, lru.get(3));
    }

    @Test
    void testUpdateEntry() {
        ConcurrentMemorySensitiveLRU lru = new ConcurrentMemorySensitiveLRU(2, 50);
        lru.put(1, 10, 10);
        lru.put(1, 99, 5); // update value & size

        assertEquals(99, lru.get(1));
    }
}
