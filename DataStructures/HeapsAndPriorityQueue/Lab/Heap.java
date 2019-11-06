package DataStructures.HeapsAndPriorityQueue.Lab;

public class Heap {
    public static <E extends Comparable<E>> void sort(E[] array) {
        int length = array.length;

        for (int i = length / 2; i >= 0; i--) {
            heapifyDown(array, i, length);
        }

        for (int i = length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapifyDown(array, 0, i);
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] array, int index, int length) {
        while (index < length / 2) {
            int childIndex = (2 * index) + 1;

            if (childIndex + 1 < length && array[childIndex].compareTo(array[childIndex + 1]) < 0) {
                childIndex++;
            }

            if (array[childIndex].compareTo(array[index]) < 0) {
                break;
            }

            swap(array, childIndex, index);
            index = childIndex;
        }
    }

    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
