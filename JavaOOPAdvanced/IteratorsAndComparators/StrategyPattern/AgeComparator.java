package JavaOOPAdvanced.IteratorsAndComparators.StrategyPattern;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        return firstPerson.getAge() - secondPerson.getAge();
    }
}
