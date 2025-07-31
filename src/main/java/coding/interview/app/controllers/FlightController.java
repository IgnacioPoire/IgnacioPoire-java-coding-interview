package coding.interview.app.controllers;

import coding.interview.app.entities.Flight;
import coding.interview.app.requests.UpdateFlightRequest;
import coding.interview.app.services.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        final Optional<Flight> flight = flightService.findById(id);

        if (flight.isPresent()) {
            return ResponseEntity.ok(flight.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody UpdateFlightRequest request) {
        try {
            final Optional<Flight> flight = flightService.findById(id);

            if (flight.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            final Flight updatedFlight = new Flight(flight.get().getId(), request.code(), request.origin(),
                    request.destination(), request.status());

            flightService.save(updatedFlight);

            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException e) {
            log.error("Error updating flight with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
