package app.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedDto {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    public UserSeedDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "Last name cannot be null.")
    @Size(min = 3, message = "Last name must be at least 3 symbols long.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
