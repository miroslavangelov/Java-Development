package DataStructures.HashTables.Lab;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<TKey  extends Comparable, TValue> implements Iterable<KeyValue<TKey, TValue>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<KeyValue<TKey, TValue>>[] slots;
    private int size;
    private int capacity;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.slots = new LinkedList[capacity];
        this.size = 0;
    }

    public void add(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey,TValue> element: this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.growIfNeeded();
        int slotNumber = findSlotNumber(key);

        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<TKey,TValue> element: this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                element.setValue(value);

                return false;
            }
        }

        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;

        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> element = this.find(key);

        if (element == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }

        return element.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        return this.find(key) != null;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;
                }
            }
        }

        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int slotNumber = this.findSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];

        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    elements.remove(element);
                    this.size--;

                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        this.capacity = INITIAL_CAPACITY;
        this.setSize(0);
        this.slots = new LinkedList[capacity];
    }

    public Iterable<TKey> keys() {
        LinkedList<TKey> keys = new LinkedList<>();

        for (LinkedList<KeyValue<TKey,TValue>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<TKey, TValue> pair: slot) {
                    keys.add(pair.getKey());
                }
            }
        }

        return keys;
    }

    public Iterable<TValue> values() {
        LinkedList<TValue> values = new LinkedList<>();

        for (LinkedList<KeyValue<TKey,TValue>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<TKey, TValue> pair: slot) {
                    values.add(pair.getValue());
                }
            }
        }

        return values;
    }

    public Iterable<KeyValue<TKey, TValue>> sortPairs() {
        LinkedList<KeyValue<TKey,TValue>> result = new LinkedList<>();

        for (LinkedList<KeyValue<TKey,TValue>> slot : this.slots) {
            if (slot != null) {
                result.addAll(slot);
            }
        }

        result.sort(Comparator.comparing(KeyValue::getKey));

        return result;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        LinkedList<KeyValue<TKey,TValue>> elements = new LinkedList<>();

        for (LinkedList<KeyValue<TKey,TValue>> element: this.slots) {
            if (element != null) {
                elements.addAll(element);
            }
        }

        return elements.iterator();
    }

    public LinkedList<KeyValue<TKey, TValue>>[] getSlots() {
        return slots;
    }

    public void setSlots(LinkedList<KeyValue<TKey, TValue>>[] slots) {
        this.slots = slots;
    }

    private void growIfNeeded() {
        if (this.size() + 1 > this.capacity() * LOAD_FACTOR) {
            grow();
        }
    }

    private void grow() {
        HashTable<TKey, TValue> newTable = new HashTable<>(this.capacity * 2);
        this.capacity *= 2;

        for (KeyValue<TKey, TValue> element: this) {
            newTable.add(element.getKey(), element.getValue());
        }
        this.slots = newTable.slots;
        this.size = newTable.size;
    }

    private int findSlotNumber(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }
}
