package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

public class TownSeedDto {
    @Expose
    private String name;

    public TownSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
