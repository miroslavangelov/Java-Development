package JavaOOPBasics.InterfacesAndAbstraction.CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class MyList implements IMyList {
    private List<String> collection;

    public MyList() {
        this.collection = new ArrayList<>();
    }

    @Override
    public int getUsed() {
        return this.collection.size();
    }

    @Override
    public String remove() {
        return this.collection.remove(0);
    }

    @Override
    public int add(String element) {
        this.collection.add(0, element);
        return 0;
    }
}
