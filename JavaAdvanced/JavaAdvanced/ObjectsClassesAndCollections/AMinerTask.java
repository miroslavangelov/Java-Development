package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AMinerTask {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String resource = reader.readLine();
        Map<String, Integer> map = new HashMap<>();

        while (!resource.equals("stop")) {
            Integer quantity = Integer.parseInt(reader.readLine());

            if (!map.containsKey(resource)) {
                map.put(resource, 0);
            }

            map.put(resource, map.get(resource) + quantity);

            resource = reader.readLine();
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(String.format("%s -> %d", entry.getKey(), entry.getValue()));
        }
    }
}
