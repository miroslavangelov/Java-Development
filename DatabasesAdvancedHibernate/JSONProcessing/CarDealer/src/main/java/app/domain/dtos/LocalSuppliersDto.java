package app.domain.dtos;

import com.google.gson.annotations.Expose;

public class LocalSuppliersDto {
    @Expose
    private Integer Id;

    @Expose
    private String Name;

    @Expose
    private Integer partsCount;

    public LocalSuppliersDto() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
