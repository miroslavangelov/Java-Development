package Algorithms.ExamPreparations.ExamPreparationVI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BlackMessup {
    private static Map<String, Set<Atom>> graph = new HashMap<>();
    private static Map<String, Atom> atoms = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int atomsCount = Integer.parseInt(reader.readLine());
        int connectionsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < atomsCount; i++) {
            String[] atomData = reader.readLine().split(" ");
            String name = atomData[0];
            int mass = Integer.parseInt(atomData[1]);
            int decay = Integer.parseInt(atomData[2]);
            Atom atom = new Atom(name, mass, decay);
            atoms.putIfAbsent(name, atom);
            graph.putIfAbsent(name, new HashSet<>());
        }

        for (int i = 0; i < connectionsCount; i++) {
            String[] connectionData = reader.readLine().split(" ");
            String from = connectionData[0];
            String to = connectionData[1];
            graph.get(from).add(atoms.get(to));
            graph.get(to).add(atoms.get(from));
        }

        List<Set<Atom>> molecules = findConnectedComponents();
        long max = findBestValue(molecules);
        System.out.print(max);
    }

    private static int findBestValue(List<Set<Atom>> molecules) {
        int max = 0;

        for (Set<Atom> molecule: molecules) {
            int result = getValue(molecule);

            if (result > max) {
                max = result;
            }
        }

        return max;
    }

    private static int getValue(Set<Atom> molecule) {
        int maxDecay = 1;
        int result = 0;
        int count = 0;

        for (Atom atom: molecule) {
            int currentDecay = atom.getDecay();
            int currentMass = atom.getMass();

            if (currentDecay > maxDecay) {
                maxDecay = currentDecay;
                result += currentMass;
                count++;
            } else if (maxDecay > count) {
                result += currentMass;
                count++;
            }
        }

        return result;
    }

    private static List<Set<Atom>> findConnectedComponents() {
        List<Set<Atom>> molecules = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String atom: graph.keySet()) {
            if (!visited.contains(atom)) {
                Set<Atom> molecule = new TreeSet<>(Comparator.naturalOrder());
                DFS(atom, molecule, visited);
                molecules.add(molecule);
            }
        }

        return molecules;
    }

    private static void DFS(String atom, Set<Atom> molecule, Set<String> visited) {
        visited.add(atom);
        molecule.add(atoms.get(atom));

        for (Atom child: graph.get(atom)) {
            if (!visited.contains(child.getName())) {
                DFS(child.getName(), molecule, visited);
            }
        }
    }

    private static class Atom implements Comparable<Atom> {
        private String name;
        private Integer mass;
        private Integer decay;

        Atom(String name, int mass, int decay) {
            this.name = name;
            this.mass = mass;
            this.decay = decay;
        }

        String getName() {
            return name;
        }

        Integer getMass() {
            return mass;
        }

        Integer getDecay() {
            return decay;
        }

        @Override
        public int compareTo(Atom other) {
            return other.mass.compareTo(this.mass);
        }
    }
}