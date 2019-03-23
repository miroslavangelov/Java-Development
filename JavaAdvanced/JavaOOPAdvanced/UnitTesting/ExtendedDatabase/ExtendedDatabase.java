package JavaOOPAdvanced.UnitTesting.ExtendedDatabase;

import javax.naming.OperationNotSupportedException;

public class ExtendedDatabase {
    private static final int CAPACITY = 16;

    private Person[] people;

    public ExtendedDatabase(Person... elements) throws OperationNotSupportedException {
        this.people = new Person[CAPACITY];
        this.setStoredIntegers(elements);
    }

    public void add(Person person) throws OperationNotSupportedException {
        if (person.getId() < 0) {
            throw new OperationNotSupportedException("Id should have a positive value!");
        }
        for (int i = 0; i < this.people.length; i++) {
            Person currentPerson = this.people[i];

            if (currentPerson == null) {
                this.people[i] = person;
                return;
            }

            if (currentPerson.getId() == person.getId() || currentPerson.getUsername().equals(person.getUsername())) {
                throw new OperationNotSupportedException("Person should be unique!");
            }
        }
        throw new OperationNotSupportedException("Collection is full!");
    }

    public int remove() throws OperationNotSupportedException {
        for (int i = this.people.length - 1; i >= 0; i--) {
            Person currentPerson = this.people[i];
            if (currentPerson != null) {
                this.people[i] = null;
                return i;
            }
        }
        throw new OperationNotSupportedException("Collection is empty!");
    }

    public Person[] fetch() {
        return this.people;
    }

    public Person findById(long id) throws OperationNotSupportedException {
        for (int i = 0; i < this.people.length; i++) {
            Person currentPerson = this.people[i];
            if (currentPerson != null && currentPerson.getId() == id) {
                return currentPerson;
            }
        }
        throw new OperationNotSupportedException("User not found!");
    }

    public Person findByUsername(String username) throws OperationNotSupportedException {
        if (username == null) {
            throw new OperationNotSupportedException("Username should not be null!");
        }
        for (int i = 0; i < this.people.length; i++) {
            Person currentPerson = this.people[i];
            if (currentPerson != null && currentPerson.getUsername().equals(username)) {
                return currentPerson;
            }
        }
        throw new OperationNotSupportedException("User not found!");
    }

    private void setStoredIntegers(Person... elements) throws OperationNotSupportedException {
        if (this.people.length != CAPACITY) {
            throw new OperationNotSupportedException("Invalid capacity!");
        }
        for (int i = 0; i < elements.length; i++) {
            this.people[i] = elements[i];
        }
    }
}
