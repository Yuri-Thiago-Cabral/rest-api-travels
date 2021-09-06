package br.com.rest.travelapi.controller;

import br.com.rest.travelapi.model.Collaborator;
import br.com.rest.travelapi.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/collaborator")
public class CollaboratorController {

    @Autowired
    CollaboratorService collaboratorService;

    @PostMapping("/register")
    public ResponseEntity<Collaborator> registerCollaborator(@RequestBody @Valid Collaborator collaborator,
                                                             UriComponentsBuilder uriBuilder) {

        collaboratorService.registerCollaborator(collaborator);
        URI uri = uriBuilder.path("/api/v1/collaborator/{id}").buildAndExpand(collaborator.getId()).toUri();
        return ResponseEntity.created(uri).body(collaborator);
    }

    @GetMapping("/listCollaborators")
    public ResponseEntity<List<Collaborator>> listAllCollaborators() {
        return ResponseEntity.ok().body(collaboratorService.listAllCollaborators());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Collaborator>> listCollaboratorById(@PathVariable Long id) {

        if (collaboratorService.findCollaboratorId(id).isPresent()) {
            return ResponseEntity.ok().body(collaboratorService.findCollaboratorId(id));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("update")
    public ResponseEntity<Collaborator> updateCollaborator(@RequestBody Collaborator collaborator) {
            collaboratorService.updateCollaborator(collaborator);
            return ResponseEntity.ok().body(collaborator);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long id) {
        collaboratorService.deleteCollaborator(id);
        return ResponseEntity.noContent().build();
    }
}