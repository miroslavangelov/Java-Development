package JavaOOPBasics.InterfacesAndAbstraction.CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveCollection implements IAddRemoveCollection {
    private List<String> collection;

    public AddRemoveCollection() {
        this.collection = new ArrayList<>();
    }

    @Override
    public int add(String element) {
        this.collection.add(0, element);
        return 0;
    }

    @Override
    public String remove() {
        return this.collection.remove(this.collection.size() - 1);
    }
}
