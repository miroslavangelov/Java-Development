package DataStructures.AATreesAndAVLTrees.Exercise.FirstLastList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private ArrayList<T> elements = new ArrayList<>();
    private Map<T, List<T>> keyCount = new TreeMap<>();

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.keyCount.putIfAbsent(element, new ArrayList<>());
        this.keyCount.get(element).add(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            result.add(this.elements.get(i));
        }

        return result;
    }

    @Override
    public Iterable<T> last(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();

        for (int i = this.getCount() - 1; i >= this.getCount() - count; i--) {
            result.add(this.elements.get(i));
        }

        return result;
    }

    @Override
    public Iterable<T> min(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();

        for (T key: this.keyCount.keySet()) {
            for (int i = 0; i < this.keyCount.get(key).size(); i++) {
                if (count == 0) {
                    break;
                }
                result.add(this.keyCount.get(key).get(i));
                count--;
            }

            if (count == 0) {
                break;
            }
        }

        return result;
    }

    @Override
    public Iterable<T> max(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }

        List<T> result = new ArrayList<>();

        for (T key: this.keyCount.keySet()) {
            for (int i = this.keyCount.get(key).size() - 1; i >= 0; i--) {
                result.add(0, this.keyCount.get(key).get(i));
            }
        }
        result.subList(count, result.size()).clear();

        return result;
    }

    @Override
    public void clear() {
        this.elements = new ArrayList<>();
        this.keyCount = new TreeMap<>();
    }

    @Override
    public int removeAll(T element) {
        int count = 0;

        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).compareTo(element) == 0) {
                this.elements.remove(i);
                i--;
                count++;
            }
        }
        this.keyCount.remove(element);

        return count;
    }
}