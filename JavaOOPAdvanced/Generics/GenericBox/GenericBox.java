package JavaOOPAdvanced.Generics.GenericBox;

public class GenericBox<T> {
    private T value;

    public GenericBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n", this.value.getClass().getName(), this.getValue());
    }
}
