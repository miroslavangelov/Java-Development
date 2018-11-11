package JavaOOPAdvanced.UnitTesting.BubbleSort;

import javax.naming.OperationNotSupportedException;

public final class BubbleSort {
    private BubbleSort() {

    }

    public static <T extends Comparable<T>> void sort(T[] elements) throws OperationNotSupportedException {
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = 0; i < elements.length - 1; i++) {
                if (elements[i] == null || elements[i + 1] == null) {
                    throw new OperationNotSupportedException("Element can not be null!");
                }

                if (elements[i].compareTo(elements[i + 1]) > 0) {
                    swapped = true;
                    T temp = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = temp;
                }
            }
        }
    }
}
