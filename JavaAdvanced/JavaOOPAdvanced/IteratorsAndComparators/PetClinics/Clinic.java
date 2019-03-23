package JavaOOPAdvanced.IteratorsAndComparators.PetClinics;

import java.util.Iterator;

public class Clinic {
    private String name;
    private int rooms;
    private Pet[] pets;

    public Clinic(String name, int rooms) {
        this.name = name;
        this.setRooms(rooms);
        this.pets = new Pet[rooms];
    }

    public String getName() {
        return name;
    }

    public int getRooms() {
        return rooms;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void setRooms(int rooms) {
        if (rooms % 2 == 0) {
            throw new IllegalArgumentException("Invalid Operation!");
        }
        this.rooms = rooms;
    }

    public boolean addPet(Pet pet) {
        AddPetIterator addPetIterator = new AddPetIterator();

        while (addPetIterator.hasNext()) {
            int currentIndex = addPetIterator.next();

            if (this.pets[currentIndex] == null){
                this.pets[currentIndex] = pet;
                return true;
            }
        }

        return false;
    }

    public boolean releasePet() {
        ReleasePetIterator releasePetIterator = new ReleasePetIterator();

        while (releasePetIterator.hasNext()) {
            int currentIndex = releasePetIterator.next();

            if (this.pets[currentIndex] != null) {
                this.pets[currentIndex] = null;
                return true;
            }
        }

        return false;
    }

    public boolean hasEmptyRooms() {
        for (int i = 0; i < this.getPets().length; i++) {
            Pet currentPet = this.getPets()[i];

            if (currentPet == null) {
                return true;
            }
        }

        return false;
    }

    public void printAllRooms() {
        for (int i = 0; i < this.getPets().length; i++) {
            Pet currentPet = this.getPets()[i];

            if (currentPet != null) {
                System.out.println(currentPet.toString());
            } else {
                System.out.println("Room empty");
            }
        }
    }

    public void printRoom(int index) {
        Pet pet = this.getPets()[index - 1];

        if (pet != null) {
            System.out.println(pet.toString());
        } else {
            System.out.println("Room empty");
        }
    }

    private final class AddPetIterator implements Iterator<Integer> {
        private int roomIndex = rooms / 2;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < pets.length;
        }

        @Override
        public Integer next() {
            if (currentIndex % 2 != 0) {
                roomIndex -= currentIndex;
            } else {
                roomIndex += currentIndex;
            }
            currentIndex++;

            return roomIndex;
        }
    }

    private final class ReleasePetIterator implements Iterator<Integer> {
        private boolean isLimitReached = false;
        private boolean removeFromStart = false;
        private int currentIndex = rooms / 2;

        @Override
        public boolean hasNext() {
            return currentIndex < pets.length && !isLimitReached;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                currentIndex = 0;
                removeFromStart = true;
                return currentIndex;
            }
            if (removeFromStart && currentIndex == rooms / 2) {
                isLimitReached = true;
            }

            return currentIndex++;
        }
    }
}
