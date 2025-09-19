package com.sagar.cache;

import java.util.HashMap;

public class MemorySensitiveLRU {
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int maxEntries;
    private long maxMemory;
    private int currentEntries;
    private long currentMemory;

    public MemorySensitiveLRU(int maxEntries, long maxMemory) {
        this.maxEntries = maxEntries;
        this.maxMemory = maxMemory;
        this.currentEntries = 0;
        this.currentMemory = 0;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            remove(curr);
            insert(curr);
            return curr.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value, long memorySize) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            remove(old);
            currentEntries--;
            currentMemory -= old.size;
        }

        Node curr = new Node(key, value);
        curr.size = memorySize;
        map.put(key, curr);
        insert(curr);
        currentEntries++;
        currentMemory += curr.size;

        // Evict if needed
        while ((maxEntries > 0 && currentEntries > maxEntries) ||
               (maxMemory > 0 && currentMemory > maxMemory)) {
            Node least = tail.prev;
            remove(least);
            map.remove(least.key);
            currentEntries--;
            currentMemory -= least.size;
        }
    }

    private void insert(Node node) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
}
