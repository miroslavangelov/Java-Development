package JavaOOPBasics.InterfacesAndAbstraction.MooD3;

public abstract class GameObject implements IGameObject {
    private String userName;
    private String hashedPassword;
    private int level;

    public GameObject(String userName, int level) {
        this.userName = userName;
        this.hashedPassword = this.generateHashedPassword();
        this.level = level;
    }

    protected abstract String generateHashedPassword();

    protected abstract String getSpecialPoints();

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("\"%s\" | \"%s\" -> %s", this.getUserName(), this.getHashedPassword(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(this.getSpecialPoints());

        return result.toString();
    }
}
