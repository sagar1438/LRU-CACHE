package com.sagar.cache;

public class Main {
    public static void main(String[] args) {
        System.out.println("---- Simple LRU ----");
        LRU lru = new LRU(2);
        lru.put(1, 10);
        lru.put(2, 20);
        System.out.println(lru.get(1)); 
        lru.put(3, 30); 
        System.out.println(lru.get(2)); 
        lru.put(4, 40); 
        System.out.println(lru.get(1)); 
        System.out.println(lru.get(3)); 
        System.out.println(lru.get(4)); 

        System.out.println("\n---- MemorySensitiveLRU ----");
        MemorySensitiveLRU memLRU = new MemorySensitiveLRU(2, 25);
        memLRU.put(1, 10, 10);
        memLRU.put(2, 20, 12);
        memLRU.put(3, 30, 12); 
        System.out.println(memLRU.get(1)); 
        System.out.println(memLRU.get(2)); 
        System.out.println(memLRU.get(3)); 

        System.out.println("\n---- ConcurrentLRU ----");
        ConcurrentLRU concLRU = new ConcurrentLRU(2);
        concLRU.put(1, 10);
        concLRU.put(2, 20);
        System.out.println(concLRU.get(1)); 
        concLRU.put(3, 30); 
        System.out.println(concLRU.get(2)); 

        System.out.println("\n---- ConcurrentMemorySensitiveLRU ----");
        ConcurrentMemorySensitiveLRU concMemLRU = new ConcurrentMemorySensitiveLRU(2, 25);
        concMemLRU.put(1, 10, 10);
        concMemLRU.put(2, 20, 12);
        concMemLRU.put(3, 30, 12); 
        System.out.println(concMemLRU.get(1)); 
        System.out.println(concMemLRU.get(2)); 
        System.out.println(concMemLRU.get(3)); 
    }
}
