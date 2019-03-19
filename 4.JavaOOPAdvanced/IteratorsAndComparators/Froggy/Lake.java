package JavaOOPAdvanced.IteratorsAndComparators.Froggy;

import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer> {
    private List<Integer> jumps;

    public Lake(List<Integer> jumps) {
        this.jumps = jumps;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private class Frog implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < jumps.size();
        }

        @Override
        public Integer next() {
            Integer currentJump = jumps.get(index);
            index += 2;
            if (!this.hasNext() && this.index % 2 == 0) {
                this.index = 1;
            }

            return currentJump;
        }
    }
}
