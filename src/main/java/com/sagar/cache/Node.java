package com.sagar.cache;

public class Node {
    int key;
    int val;
    long size;  // used for memory-sensitive LRU
    Node next;
    Node prev;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.size = 0;   // default 0 unless specified in memory-based LRU
        this.next = null;
        this.prev = null;
    }
}
