package JavaOOPAdvanced.IteratorsAndComparators.StrategyPattern;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        if (firstPerson.getName().length() - secondPerson.getName().length() == 0) {
            return firstPerson.getName().toLowerCase().compareTo(secondPerson.getName().toLowerCase());
        }

        return firstPerson.getName().length() - secondPerson.getName().length();
    }
}
