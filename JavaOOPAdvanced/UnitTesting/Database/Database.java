package JavaOOPAdvanced.UnitTesting.Database;

import javax.naming.OperationNotSupportedException;

public class Database {
    private static final int CAPACITY = 16;

    private Integer[] storedIntegers;

    public Database(Integer... elements) throws OperationNotSupportedException {
        this.storedIntegers = new Integer[CAPACITY];
        this.setStoredIntegers(elements);
    }

    public void add(Integer element) throws OperationNotSupportedException {
        if (element == null) {
            throw new OperationNotSupportedException("Element can not be null!");
        }
        for (int i = 0; i < this.storedIntegers.length; i++) {
            Integer currentElement = this.storedIntegers[i];

            if (currentElement == null) {
                this.storedIntegers[i] = element;
                return;
            }
        }
        throw new OperationNotSupportedException("Collection is full!");
    }

    public int remove() throws OperationNotSupportedException {
        for (int i = this.storedIntegers.length - 1; i >= 0; i--) {
            Integer currentElement = this.storedIntegers[i];
            if (currentElement != null) {
                this.storedIntegers[i] = null;
                return i;
            }
        }
        throw new OperationNotSupportedException("Collection is empty!");
    }

    public Integer[] fetch() {
        return this.storedIntegers;
    }

    private void setStoredIntegers(Integer... elements) throws OperationNotSupportedException {
        if (this.storedIntegers.length != CAPACITY) {
            throw new OperationNotSupportedException("Invalid capacity!");
        }
        for (int i = 0; i < elements.length; i++) {
            this.storedIntegers[i] = elements[i];
        }
    }
}
