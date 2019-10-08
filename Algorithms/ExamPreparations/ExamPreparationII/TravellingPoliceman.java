package Algorithms.ExamPreparations.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TravellingPoliceman {
    private static List<Street> streets = new ArrayList<>();
    private static int fuel;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fuel = Integer.parseInt(reader.readLine());
        String inputLine = reader.readLine();
        streets = new ArrayList<>();

        while (!"End".equals(inputLine)) {
            String[] streetData = inputLine.split(", ");
            String streetName = streetData[0];
            int carDamage = Integer.parseInt(streetData[1]);
            int pokemonCount = Integer.parseInt(streetData[2]);
            int streetLength = Integer.parseInt(streetData[3]);
            Street street = new Street(streetName, carDamage, pokemonCount, streetLength);

            if (street.getValue() >= 0) {
                streets.add(street);
            }
            inputLine = reader.readLine();
        }

        Deque<Street> visitedStreets = getVisitedStreets();
        StringBuilder sb = new StringBuilder();
        int pokemonsCaught = 0;
        int carDamage = 0;

        if (!visitedStreets.isEmpty()) {
            while (!visitedStreets.isEmpty()) {
                Street street = visitedStreets.pop();
                sb.append(street.getStreetName()).append(" -> ");
                pokemonsCaught += street.getPokemonCount();
                carDamage += street.getCarDamage();
            }
            sb.delete(sb.length() - 4, sb.length());
        }

        sb.append(System.lineSeparator())
                .append(String.format("Total pokemons caught -> %d", pokemonsCaught))
                .append(System.lineSeparator())
                .append(String.format("Total car damage -> %d", carDamage))
                .append(System.lineSeparator())
                .append(String.format("Fuel Left -> %d", fuel));
        System.out.print(sb);
    }

    private static Deque<Street> getVisitedStreets() {
        int[][] values = new int[streets.size() + 1][fuel + 1];
        boolean[][] isIncluded = new boolean[streets.size() + 1][fuel + 1];

        for (int streetIndex = 0; streetIndex < streets.size(); streetIndex++) {
            int row = streetIndex + 1;
            Street street = streets.get(streetIndex);

            for (int currentFuel = 0; currentFuel <= fuel; currentFuel++) {
                int excludedValue = values[row - 1][currentFuel];
                int includedValue = 0;

                if (street.getStreetLength() <= currentFuel) {
                    includedValue = street.getValue() + values[row - 1][currentFuel - street.getStreetLength()];
                }

                if (includedValue > excludedValue) {
                    values[row][currentFuel] = includedValue;
                    isIncluded[row][currentFuel] = true;
                } else {
                    values[row][currentFuel] = excludedValue;
                }
            }
        }

        Deque<Street> visitedStreets = new ArrayDeque<>();
        for (int index = streets.size(); index >= 1; index--) {
            if (isIncluded[index][fuel]) {
                Street street = streets.get(index - 1);
                visitedStreets.push(street);
                fuel -= street.getStreetLength();
            }
        }

        return visitedStreets;
    }
}

class Street {
    private String streetName;
    private int carDamage;
    private int pokemonCount;
    private int streetLength;

    Street(String streetName, int carDamage, int pokemonCount, int streetLength) {
        this.streetName = streetName;
        this.carDamage = carDamage;
        this.pokemonCount = pokemonCount;
        this.streetLength = streetLength;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getCarDamage() {
        return carDamage;
    }

    public void setCarDamage(int carDamage) {
        this.carDamage = carDamage;
    }

    public int getPokemonCount() {
        return pokemonCount;
    }

    public void setPokemonCount(int pokemonCount) {
        this.pokemonCount = pokemonCount;
    }

    public int getStreetLength() {
        return streetLength;
    }

    public void setStreetLength(int streetLength) {
        this.streetLength = streetLength;
    }

    public int getValue() {
        return (this.pokemonCount * 10) - carDamage;
    }
}