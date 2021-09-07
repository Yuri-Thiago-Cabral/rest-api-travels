package br.com.rest.travelapi.controller.dto;

import br.com.rest.travelapi.model.Collaborator;

import java.util.List;
import java.util.stream.Collectors;

public class CollaboratorDto {

    private Long id;
    private String name;
    private String surname;
    private String phone;

    public CollaboratorDto(Collaborator collaborator) {
        this.id = collaborator.getId();
        this.name = collaborator.getName();
        this.surname = collaborator.getSurname();
        this.phone = collaborator.getPhone();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public static List<CollaboratorDto> convert(List<Collaborator> collaborators) {
        return collaborators.stream().map(CollaboratorDto::new).collect(Collectors.toList());
    }
}