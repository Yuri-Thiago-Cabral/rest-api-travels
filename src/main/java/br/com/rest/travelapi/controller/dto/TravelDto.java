package br.com.rest.travelapi.controller.dto;

import br.com.rest.travelapi.model.Collaborator;
import br.com.rest.travelapi.model.Travel;
import br.com.rest.travelapi.model.enums.TravelStatus;

import java.util.List;
import java.util.stream.Collectors;

public class TravelDto {

    private Long id;
    private String startingLocation;
    private String destinyLocation;
    private TravelStatus status;
    private Collaborator collaborator;

    public TravelDto(Travel travel) {
        this.id = travel.getId();
        this.startingLocation = travel.getStartingLocation();
        this.destinyLocation = travel.getDestinyLocation();
        this.status = travel.getStatus();
        this.collaborator = travel.getCollaborator();
    }

    public Long getId() {
        return id;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getDestinyLocation() {
        return destinyLocation;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public static List<TravelDto> convert(List<Travel> travels) {
        return travels.stream().map(TravelDto::new).collect(Collectors.toList());
    }
}