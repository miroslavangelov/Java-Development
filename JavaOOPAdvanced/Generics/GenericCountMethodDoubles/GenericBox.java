package JavaOOPAdvanced.Generics.GenericCountMethodDoubles;

import java.util.List;

public class GenericBox<T extends Comparable<T>> {
    public int getGreaterElementsCount(List<T> list, T element) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            T currentElement = list.get(i);
            if (currentElement.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }
}
