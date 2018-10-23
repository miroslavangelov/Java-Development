package JavaOOPAdvanced.EnumerationsAndAnnotations.TrafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] trafficLightsData = reader.readLine().split(" ");
        int linesCount = Integer.parseInt(reader.readLine());
        TrafficLight[] trafficLights = new TrafficLight[trafficLightsData.length];

        for (int i = 0; i < trafficLightsData.length; i++) {
            TrafficLight trafficLight = TrafficLight.valueOf(trafficLightsData[i]);
            trafficLights[i] = trafficLight;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < trafficLights.length; j++) {
                int currentTrafficLightIndex = trafficLights[j].getValue();
                if (currentTrafficLightIndex == TrafficLight.values()[TrafficLight.values().length - 1].getValue()) {
                    currentTrafficLightIndex = 0;
                } else {
                    currentTrafficLightIndex++;
                }

                TrafficLight currentTrafficLight = TrafficLight.values()[currentTrafficLightIndex];
                trafficLights[j] = currentTrafficLight;
                result.append(currentTrafficLight).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }
}
