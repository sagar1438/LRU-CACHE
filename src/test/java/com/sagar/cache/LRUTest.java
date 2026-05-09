package com.sagar.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LRUTest {

    @Test
    public void testBasicPutAndGet() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(2, 20);

        assertEquals(10, lru.get(1));  
        assertEquals(20, lru.get(2));  
    }

    @Test
    public void testEvictionPolicy() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30); 

        assertEquals(-1, lru.get(1)); 
        assertEquals(20, lru.get(2)); 
        assertEquals(30, lru.get(3)); 
    }

    @Test
    public void testUpdateValue() {
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(1, 99); 

        assertEquals(99, lru.get(1));
    }
}
