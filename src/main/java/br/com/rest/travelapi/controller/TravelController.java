package br.com.rest.travelapi.controller;

import br.com.rest.travelapi.controller.dto.TravelDto;
import br.com.rest.travelapi.controller.dto.formDto.TravelFormDto;
import br.com.rest.travelapi.model.Travel;
import br.com.rest.travelapi.repository.CollaboratorRepository;
import br.com.rest.travelapi.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<TravelDto> registerTravel(@RequestBody TravelFormDto travelFormDto,
                                                    UriComponentsBuilder uriBuilder) {

        Travel travel = travelFormDto.convert(collaboratorRepository);
        travelService.registerTravel(travel);
        URI uri = uriBuilder.path("/api/v1/travel/{id}").buildAndExpand(travel.getId()).toUri();
        return ResponseEntity.created(uri).body(new TravelDto(travel));
    }

    @GetMapping("/listTravels")
    public ResponseEntity<List<TravelDto>> listAllTravels() {
        return ResponseEntity.ok()
                .body(TravelDto.convert(travelService.listAllTravels()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelDto> listById(@PathVariable Long id) {
        Optional<Travel> travel = travelService.findTravelId(id);
        return ResponseEntity.ok().body(new TravelDto(travel.get()));
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<TravelDto> updateCollaborator(@PathVariable Long id,
                                                        @RequestBody Travel travel) {
            travelService.updateTravel(id, travel);
            return ResponseEntity.ok().body(new TravelDto(travel));
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long id) {
        travelService.deleteTravel(id);
        return ResponseEntity.noContent().build();
    }
}