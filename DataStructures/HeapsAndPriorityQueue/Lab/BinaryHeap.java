package DataStructures.HeapsAndPriorityQueue.Lab;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private List<T> heap;

    public BinaryHeap() {
        heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T peek() {
        if (size() <= 0) {
            throw new IllegalArgumentException("Heap is empty!");
        }
        return heap.get(0);
    }

    public T pull() {
        if (size() <= 0) {
            throw new IllegalArgumentException("Heap is empty!");
        }

        T item = heap.get(0);
        heap.set(0, heap.get(size() - 1));
        heap.set(size() - 1, item);
        heap.remove(size() - 1);
        heapifyDown(0);

        return item;
    }

    private void heapifyDown(int index) {
        while (index < size() / 2) {
            int childIndex = (2 * index) + 1;

            if (childIndex + 1 < size() && heap.get(childIndex).compareTo(heap.get(childIndex + 1)) < 0) {
                childIndex++;
            }

            if (heap.get(childIndex).compareTo(heap.get(index)) < 0) {
                break;
            }

            T temp = heap.get(index);
            heap.set(index, heap.get(childIndex));
            heap.set(childIndex, temp);
            index = childIndex;
        }
    }

    private void heapifyUp(int index) {
        T parent = heap.get((index - 1) / 2);

        while (heap.get(index).compareTo(parent) > 0) {
            T temp = heap.get(index);
            heap.set(index, parent);
            heap.set((index - 1) / 2, temp);
            index = (index - 1) / 2;
            parent = heap.get((index - 1) / 2);
        }
    }
}
