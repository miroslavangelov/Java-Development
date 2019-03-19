package JavaOOPAdvanced.Generics.GenericSwapMethodStrings;

import java.util.Collections;
import java.util.List;

public class GenericSwap<T> {
    private List<T> list;

    public GenericSwap(List<T> list) {
        this.list = list;
    }

    public void swap(int i, int j) {
        Collections.swap(this.list, i, j);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            T currentElement = this.list.get(i);
            result.append(String.format("%s: %s%n", currentElement.getClass().getName(), currentElement));
        }

        return result.toString();
    }
}
