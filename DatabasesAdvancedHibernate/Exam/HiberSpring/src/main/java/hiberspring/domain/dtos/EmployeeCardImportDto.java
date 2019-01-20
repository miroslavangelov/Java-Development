package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;

public class EmployeeCardImportDto {
    @Expose
    private String number;

    public EmployeeCardImportDto() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
