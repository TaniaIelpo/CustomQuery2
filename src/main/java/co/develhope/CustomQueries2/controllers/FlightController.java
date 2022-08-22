package co.develhope.CustomQueries2.controllers;


import co.develhope.CustomQueries2.entities.Flight;
import co.develhope.CustomQueries2.entities.FlightStatus;
import co.develhope.CustomQueries2.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Tania Ielpo
 */

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService flightService;

    /**
     * for the provisioning of n flights (where n is an optional query param; if absent, n=100):
     * @param n Number of flight to store
     */

    @PostMapping
    public void putIn(@RequestParam(required = false) Optional<Integer> n){
        flightService.save(n);
    }
    /**
     *
     *for retrieving all the flights in the db using pagination
     * and returning them in ascending order by fromAirport
     * @param page number of page
     * @param size number of flights in a page
     * @return all flights in database
     */


    @GetMapping
    public Page<Flight> getAll(@RequestParam int page,
                          @RequestParam int size){

        return flightService.findAll(page,size);
    }

    /**
     * for retrieving all the flights that are ONTIME without using a custom query
     * @return a list of flights in status ONTIME
     */

    @GetMapping("/status")
    public List<Flight> getByStatus(){
        return flightService.getByStatus();
    }


    /**
     * for retrieving - using a custom query - all the flights that are in p1 or in p2 status
     * @param p1 a flightStatus
     * @param p2 a flightStatus
     * @return a flight List where a flight is in p1 status or in p2
     */

    @GetMapping("/custom")
    public List<Flight> getCustomFlight(@RequestParam FlightStatus p1, @RequestParam FlightStatus p2) {
        System.out.println("p1= "+p1.toString()+" p2= "+p2.toString());
        return flightService.getCustomFlight(p1,p2);
    }

}
