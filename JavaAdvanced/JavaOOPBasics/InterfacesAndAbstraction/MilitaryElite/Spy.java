package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

public class Spy extends Soldier implements ISpy {
    private String codeNumber;

    public Spy(int id, String firstName, String lastName, String codeName) {
        super(id, firstName, lastName);
        this.codeNumber = codeName;
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Name: %s %s Id: %d", this.getFirstName(), this.getLastName(), this.getId()))
                .append(System.lineSeparator())
                .append(String.format("Code Number: %s", this.getCodeNumber()));

        return result.toString();
    }
}
