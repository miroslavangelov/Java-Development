package JavaOOPBasics.InterfacesAndAbstraction.BorderControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Citizen {
    private String id;

    public Citizen(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isFakedId(String fakeId) {
        Pattern pattern = Pattern.compile(fakeId + "$");
        Matcher matcher = pattern.matcher(this.getId());

        return matcher.find();
    }
}
