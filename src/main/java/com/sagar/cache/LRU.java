package com.sagar.cache;

import java.util.HashMap;

public class LRU {
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);

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

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node curr = new Node(key, value);
        map.put(key, curr);
        insert(curr);

        if (map.size() > capacity) {
            Node least = tail.prev;
            remove(least);
            map.remove(least.key);
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
