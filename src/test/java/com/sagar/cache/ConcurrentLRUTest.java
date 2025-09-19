package com.sagar.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentLRUTest {

    @Test
    void testConcurrentLRUBasic() {
        ConcurrentLRU lru = new ConcurrentLRU(2);

        lru.put(1, 10);
        lru.put(2, 20);

        assertEquals(10, lru.get(1)); // should return 10

        lru.put(3, 30); // evicts key 2
        assertEquals(-1, lru.get(2));
        assertEquals(10, lru.get(1));
        assertEquals(30, lru.get(3));
    }

    @Test
    void testConcurrentLRUOverwrite() {
        ConcurrentLRU lru = new ConcurrentLRU(2);

        lru.put(1, 10);
        lru.put(1, 15); // overwrite
        assertEquals(15, lru.get(1));
    }

    @Test
    void testConcurrentLRUEviction() {
        ConcurrentLRU lru = new ConcurrentLRU(2);

        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30); // evicts 1
        assertEquals(-1, lru.get(1));
        assertEquals(20, lru.get(2));
        assertEquals(30, lru.get(3));
    }
}
