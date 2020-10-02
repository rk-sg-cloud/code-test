package com.test.axway.hashmap;

public class CustomHashMap<K, V> {

    private int occupiedIndexes = 0;
    private int capacity = 16;
    private float loadFactor = 0.75f;
    private Entry<K, V>[] table;

    public CustomHashMap() {
        table = new Entry[capacity];
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public CustomHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry newEntry = new Entry(key, value, null);
        if (isIndexEmpty(index)) {
            if (!(occupiedIndexes < capacity * loadFactor)) {
                expandTable();
            }
            table[index] = newEntry;
            occupiedIndexes++;
        } else {
            Entry<K, V> previousNode = null;
            Entry<K, V> currentNode = table[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null) {
                previousNode.setNext(newEntry);
            }
        }
    }

    public V get(K key) {
        V value = null;
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }

    public void delete(K key) {
        int index = getIndex(key);
        Entry previous = null;
        Entry entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (previous == null) {
                    entry = entry.getNext();
                    table[index] = entry;
                    occupiedIndexes--;
                } else {
                    previous.setNext(entry.getNext());
                }
                return;
            }
            previous = entry;
            entry = entry.getNext();
        }
    }

    public V getAt(int index) {
        if(table != null && table.length>0) {
            for (Entry entry : table) {
                if (entry != null && entry.getKey()!=null && entry.getValue()!=null
                        && index == getIndex((K)entry.getKey())) {
                    return (V)entry.getValue();
                }
            }
        }
        return null;
    }

    private void expandTable() {
        int oldCapacity = capacity;
        capacity *= 2;
        Entry<K, V> tempTable[] = new Entry[capacity];
        System.arraycopy(table, 0, tempTable, 0, oldCapacity);
        table = tempTable;
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        int hashcode = key.hashCode();
        int hash = hashcode ^ (hashcode >>> 16);
        return (table.length - 1) & hash;
    }

    private boolean isIndexEmpty(int index) {
        return table[index] == null;
    }
}