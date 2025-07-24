package coding.interview.app.services;

import coding.interview.app.entities.Flight;
import coding.interview.app.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    public Flight save(Flight flight) {
        return flight;
    }
}
