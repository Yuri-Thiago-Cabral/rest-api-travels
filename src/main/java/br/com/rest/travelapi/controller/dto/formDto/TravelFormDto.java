package br.com.rest.travelapi.controller.dto.formDto;

import br.com.rest.travelapi.model.Collaborator;
import br.com.rest.travelapi.model.Travel;
import br.com.rest.travelapi.repository.CollaboratorRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class TravelFormDto {

    @NotNull @NotEmpty
    private String startingLocation;

    @NotNull @NotEmpty
    private String destinyLocation;

    @NotNull @NotEmpty
    private Long idCollaborator;

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getDestinyLocation() {
        return destinyLocation;
    }

    public void setDestinyLocation(String destinyLocation) {
        this.destinyLocation = destinyLocation;
    }

    public Long getIdCollaborator() {
        return idCollaborator;
    }

    public void setIdCollaborator(Long idCollaborator) {
        this.idCollaborator = idCollaborator;
    }

    public Travel convert(CollaboratorRepository collaboratorRepository) {
        Collaborator collaborator = collaboratorRepository.findById(idCollaborator).get();
        return new Travel(startingLocation, destinyLocation, collaborator);
    }
}