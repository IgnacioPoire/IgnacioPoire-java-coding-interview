package coding.interview.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String origin;
    String destination;
    String status;

    public Flight(Long id, String code, String origin, String destination, String status) {
        this.id = id;
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public Flight() {

    }
}
