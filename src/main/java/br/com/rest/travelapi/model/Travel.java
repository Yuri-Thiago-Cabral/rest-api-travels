package br.com.rest.travelapi.model;

import br.com.rest.travelapi.model.enums.TravelStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
public class Travel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull @NotEmpty
    private String startingLocation;

    @NotNull @NotEmpty
    private String destinyLocation;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    @OneToOne
    @JoinColumn(name = "id_collaborator")
    @NotNull @NotEmpty
    private Collaborator collaborator;

    public Travel() {
        this.status = TravelStatus.MARCADA;
    }

    public Travel(String startingLocation, String destinyLocation, Collaborator collaborator) {
        this.status = TravelStatus.MARCADA;
        this.startingLocation = startingLocation;
        this.destinyLocation = destinyLocation;
        this.collaborator = collaborator;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
}