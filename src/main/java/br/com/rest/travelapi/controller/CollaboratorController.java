package br.com.rest.travelapi.controller;

import br.com.rest.travelapi.controller.dto.CollaboratorDto;
import br.com.rest.travelapi.controller.dto.formDto.CollaboratorFormDto;
import br.com.rest.travelapi.model.Collaborator;
import br.com.rest.travelapi.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity<CollaboratorDto> registerCollaborator(@RequestBody @Valid CollaboratorFormDto collaboratorFormDto,
                                                                    UriComponentsBuilder uriBuilder) {
        Collaborator collaborator = collaboratorFormDto.convert();
        collaboratorService.registerCollaborator(collaborator);
        URI uri = uriBuilder.path("/api/v1/collaborator/{id}").buildAndExpand(collaborator.getId()).toUri();
        return ResponseEntity.created(uri).body(new CollaboratorDto(collaborator));
    }

    @GetMapping("/listCollaborators")
    public ResponseEntity<List<CollaboratorDto>> listAllCollaborators() {
        return ResponseEntity.ok().
                body(CollaboratorDto.convert(collaboratorService.listAllCollaborators()));
    }

    @GetMapping("{id}")
    public ResponseEntity<CollaboratorDto> listCollaboratorById(@PathVariable Long id) {
        Optional<Collaborator> collaborator = collaboratorService.findCollaboratorId(id);
        return ResponseEntity.ok().body(new CollaboratorDto(collaborator.get()));
    }

    @PutMapping("update/{id}")
    @Transactional
    public ResponseEntity<CollaboratorDto> updateCollaborator(@PathVariable Long id,
                                                              @RequestBody Collaborator collaborator) {
        collaboratorService.updateCollaborator(id, collaborator);
        return ResponseEntity.ok().body(new CollaboratorDto(collaborator));
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long id) {
        collaboratorService.deleteCollaborator(id);
        return ResponseEntity.noContent().build();
    }
}