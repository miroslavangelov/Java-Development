package app.domain.models.view;

import app.domain.entities.Sector;

public class JobApplicationHomeViewModel {
    private Long id;
    private Sector sector;
    private String profession;

    public JobApplicationHomeViewModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
