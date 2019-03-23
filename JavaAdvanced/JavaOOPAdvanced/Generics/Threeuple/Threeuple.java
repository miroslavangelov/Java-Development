package JavaOOPAdvanced.Generics.Threeuple;

public class Threeuple<K, V, E> {
    private K item1;
    private V item2;
    private E item3;

    public Threeuple(K item1, V item2, E item3) {
        this.setItem1(item1);
        this.setItem2(item2);
        this.setItem3(item3);
    }

    public K getItem1() {
        return item1;
    }

    public void setItem1(K item1) {
        this.item1 = item1;
    }

    public V getItem2() {
        return item2;
    }

    public void setItem2(V item2) {
        this.item2 = item2;
    }

    public E getItem3() {
        return item3;
    }

    public void setItem3(E item3) {
        this.item3 = item3;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", this.getItem1(), this.getItem2(), this.getItem3());
    }
}
