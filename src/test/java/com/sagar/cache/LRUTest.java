package com.sagar.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUTest {

    @Test
    public void testBasicPutAndGet() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(2, 20);

        assertEquals(10, lru.get(1));  // key 1 should be present
        assertEquals(20, lru.get(2));  // key 2 should be present
    }

    @Test
    public void testEvictionPolicy() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30); // should evict key 1

        assertEquals(-1, lru.get(1)); // key 1 evicted
        assertEquals(20, lru.get(2)); // key 2 still present
        assertEquals(30, lru.get(3)); // key 3 present
    }

    @Test
    public void testUpdateValue() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(1, 99); // update value for key 1

        assertEquals(99, lru.get(1));
    }
}
