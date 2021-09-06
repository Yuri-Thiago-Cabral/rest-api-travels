package br.com.rest.travelapi.service;

import br.com.rest.travelapi.model.Collaborator;
import br.com.rest.travelapi.repository.CollaboratorRepository;
import br.com.rest.travelapi.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public List<Collaborator> listAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Optional<Collaborator> findCollaboratorId(Long id) {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(id);

        if (collaborator.isPresent()) {
            return collaborator;
        } else {
            throw new ResourceNotFoundException("Colaborador com id " + id + " não encontrado");
        }
    }

    public Collaborator registerCollaborator(@Valid Collaborator collaborator) {
        collaborator.setId(null);
        return collaboratorRepository.save(collaborator);
    }

    public void updateCollaborator(Collaborator collaborator) {
        checkExistence(collaborator);
        collaboratorRepository.save(collaborator);
    }

    public void deleteCollaborator(Long id) {
        try {
            collaboratorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Colaborador com id" + id + " não encontrado");
        }
    }

    private void checkExistence(Collaborator collaborator) {
        findCollaboratorId(collaborator.getId());
    }
}
