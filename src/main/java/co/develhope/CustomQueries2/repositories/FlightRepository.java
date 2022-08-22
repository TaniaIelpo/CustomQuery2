package co.develhope.CustomQueries2.repositories;


import co.develhope.CustomQueries2.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tania Ielpo
 */

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight f WHERE f.flight_status =?1 OR f.flight_status =?2",
            nativeQuery = true)
    List<Flight> getCustomFlight(int p1, int p2);
}
