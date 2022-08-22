package co.develhope.CustomQueries2.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Tania Ielpo
 */

@Entity(name="flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flight")
public class Flight {

    /**
     * a primary key
     * a string description
     * a string fromAirport
     * a string toAirport
     * an enum status
     * the possible values are ONTIME, DELAYED and CANCELLED
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    private String fromAirport;
    private String toAirport;

    @Column(name = "flight_status")
    private FlightStatus flightStatus;


    public Flight(String description, String from, String to, FlightStatus status){
        this.description=description;
        this.fromAirport=from;
        this.toAirport=to;
        this.flightStatus=status;
    }

}
