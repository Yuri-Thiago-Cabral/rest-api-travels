package br.com.rest.travelapi.service;

import br.com.rest.travelapi.model.Travel;
import br.com.rest.travelapi.model.enums.TravelStatus;
import br.com.rest.travelapi.repository.TravelRepository;
import br.com.rest.travelapi.service.exceptions.InvalidStatusException;
import br.com.rest.travelapi.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public List<Travel> listAllTravels() {
        return travelRepository.findAll();
    }

    public Optional<Travel> findTravelId(Long id) {
        Optional<Travel> travel = travelRepository.findById(id);

        if (travel.isPresent()) {
            return travel;
        } else {
            throw new ResourceNotFoundException("Viagem com id " + id + " não encontrada");
        }
    }

    public Travel registerTravel(Travel travel) {
        travel.setId(null);
        travel.setStatus(TravelStatus.MARCADA);
        return travelRepository.save(travel);
    }

    public void updateTravel(Long id, Travel travel) {
        checkExistence(travel);
        travelRepository.save(travel);
    }

    public void deleteTravel(Long id) {
        try {
            Travel travel = travelRepository.findById(id).get();
            if (travel.getStatus() != TravelStatus.ANDAMENTO) {
                travelRepository.deleteById(id);
            } else {
                throw new InvalidStatusException("Não é possível deletar viagens em andamento");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Viagem com id " + id + " não encontrada");
        }
    }

    private void checkExistence(Travel travel) {
        findTravelId(travel.getId());
    }
}