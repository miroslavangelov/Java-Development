package JavaOOPBasics.DefiningClasses.DefineClassPerson;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Class person = Person.class;
        Field[] fields = person.getDeclaredFields();
        System.out.println(fields.length);

        Person pesho = new Person("Pesho", 20);
        Person gosho = new Person("Gosho", 18);
        Person stamat = new Person("Stamat", 43);
    }
}
