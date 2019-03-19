package app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "cats")
public class Cat extends BaseEntity {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addedOn;
    private Boolean hasPassport;

    public Cat() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "breed")
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "added_on")
    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    @Column(name = "has_passport")
    public Boolean getHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
