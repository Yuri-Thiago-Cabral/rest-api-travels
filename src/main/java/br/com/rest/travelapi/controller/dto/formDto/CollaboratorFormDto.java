package br.com.rest.travelapi.controller.dto.formDto;

import br.com.rest.travelapi.model.Collaborator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CollaboratorFormDto {

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String surname;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collaborator convert() {
        return new Collaborator(name, surname, phone);
    }
}