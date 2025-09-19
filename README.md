# LRU Cache Project

## Overview
This project implements a **production-style LRU (Least Recently Used) cache** in Java with four variants:

1. **SimpleLRU** – Standard LRU cache with entry count limit.
2. **MemorySensitiveLRU** – LRU with both entry count and memory usage limits.
3. **ConcurrentLRU** – Thread-safe LRU cache using coarse-grained synchronization.
4. **ConcurrentMemorySensitiveLRU** – Thread-safe LRU with memory and count limits.

It demonstrates **O(1) get/put** using a combination of HashMap + Doubly Linked List.

---

## Features

- O(1) `get` and `put` operations.
- Automatic eviction based on capacity (entries) and/or memory usage.
- Thread-safe implementations for concurrent environments.
- Full **JUnit 5** test coverage for correctness.

---

## Setup

### Prerequisites
- Java 17+
- Maven 3.9+

### Build
```bash
mvn clean package
