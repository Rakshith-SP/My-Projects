package projects.exercise.datastructures.hashtable;

import java.util.LinkedList;

public class HashTable {
    private static class KeyValuePair {
        private final int key;
        private String value;

        public KeyValuePair(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<KeyValuePair>[] items = new LinkedList[10];
    private int count;

    public void put(int key, String value) {
        // var bucket = getOrCreateBucket(key); ADD - Attention Deficit Disorder

        var pair = getKeyValuePair(key);
        if (pair != null) {
            pair.value = value;
            return;
        }

        getOrCreateBucket(key).addLast(new KeyValuePair(key, value));
        count++;
    }
    public String get(int key) {
        var pair = getKeyValuePair(key);
        return (pair==null) ? null : pair.value;
    }

    public void remove(int key) {
        var pair = getKeyValuePair(key);
        if (pair == null)
            throw new IllegalStateException();
        getBucket(key).remove(pair);
        count--;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");

        for (LinkedList<KeyValuePair> linkedList :
                items) {
            if (linkedList != null) {
                for (KeyValuePair pair :
                        linkedList) {
                    str.append(pair.key);
                    str.append("=");
                    str.append(pair.value);
                    str.append(", ");
                }
            }
        }
        if (!str.toString().equals("{")){
            str.delete(str.length()-2, str.length());
            str.append("}");
        }
        else
            str.append("}");
        return str.toString();
    }

    private int calculateIndex(int key) {
        return key % items.length;
    }

    private KeyValuePair getKeyValuePair(int key) {
        var bucket = getBucket(key);

        if (bucket != null) {
            for (KeyValuePair keyValuePair : bucket) {
                if (keyValuePair.key == key) {
                    return keyValuePair;
                }
            }
        }
        return null;
    }

    private LinkedList<KeyValuePair> getBucket(int key) {
        return items[calculateIndex(key)];
    }

    private LinkedList<KeyValuePair> getOrCreateBucket(int key) {
        var index = calculateIndex(key);
        if (items[index] == null)
            items[index] = new LinkedList<>();

        return items[index];
    }
}
