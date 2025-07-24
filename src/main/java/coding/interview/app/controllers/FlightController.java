package coding.interview.app.controllers;

import coding.interview.app.entities.Flight;
import coding.interview.app.requests.UpdateFlightRequest;
import coding.interview.app.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
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
        // TODO: this endpoint is not working as expected
        final Optional<Flight> flight = flightService.findById(id);
        return ResponseEntity.ok(flight.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody UpdateFlightRequest request) {
        // TODO: this endpoint is not working as expected
        final Optional<Flight> flight = flightService.findById(id);
        final Flight updatedFlight = new Flight(flight.get().getId(), request.code(), request.origin(),
                request.destination(), request.status());
        flightService.save(updatedFlight);
        return ResponseEntity.ok(updatedFlight);
    }
}
