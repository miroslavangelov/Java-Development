package JavaOOPBasics.InterfacesAndAbstraction.CollectionHierarchy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        int elementsToRemove = Integer.parseInt(reader.readLine());
        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyList myList = new MyList();
        IAddCollection[] addCollections = new IAddCollection[3];
        addCollections[0] = addCollection;
        addCollections[1] = addRemoveCollection;
        addCollections[2] = myList;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < addCollections.length; i++) {
            for (String element: elements) {
                result.append(addCollections[i].add(element))
                        .append(" ");
            }
            result.append(System.lineSeparator());
        }
        for (int i = 0; i < elementsToRemove; i++) {
            result.append(addRemoveCollection.remove())
                    .append(" ");
        }
        result.append(System.lineSeparator());
        for (int i = 0; i < elementsToRemove; i++) {
            result.append(myList.remove())
                    .append(" ");
        }
        result.append(System.lineSeparator());
        System.out.println(result.toString().trim());
    }
}
