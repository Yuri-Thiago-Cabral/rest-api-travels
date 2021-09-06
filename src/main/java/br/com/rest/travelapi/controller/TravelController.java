package br.com.rest.travelapi.controller;

import br.com.rest.travelapi.model.Travel;
import br.com.rest.travelapi.service.CollaboratorService;
import br.com.rest.travelapi.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<Travel> registerTravel(@RequestBody Travel travel,
                                                 UriComponentsBuilder uriBuilder) {

        travelService.registerTravel(travel);
        URI uri = uriBuilder.path("/api/v1/travel/{id}").buildAndExpand(travel.getId()).toUri();
        return ResponseEntity.created(uri).body(travel);
    }

    @GetMapping("/listTravels")
    public ResponseEntity<List<Travel>> listAllTravels() {
        return ResponseEntity.ok().body(travelService.listAllTravels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Travel>> listById(@PathVariable Long id) {
        if (travelService.findTravelId(id).isPresent()) {
            return ResponseEntity.ok().body(travelService.findTravelId(id));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Travel> updateCollaborator(@RequestBody Travel travel) {
            travelService.updateTravel(travel);
            return ResponseEntity.ok().body(travel);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long id) {
        travelService.deleteTravel(id);
        return ResponseEntity.noContent().build();
    }
}
