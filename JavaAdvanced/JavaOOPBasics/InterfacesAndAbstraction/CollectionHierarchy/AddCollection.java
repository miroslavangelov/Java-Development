package JavaOOPBasics.InterfacesAndAbstraction.CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class AddCollection implements IAddCollection {
    private List<String> collection;

    public AddCollection() {
        this.collection = new ArrayList<>();
    }

    @Override
    public int add(String element) {
        this.collection.add(element);
        return this.collection.size() - 1;
    }
}
