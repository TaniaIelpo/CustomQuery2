package co.develhope.CustomQueries2.services;


import co.develhope.CustomQueries2.entities.Flight;
import co.develhope.CustomQueries2.entities.FlightStatus;
import co.develhope.CustomQueries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    /**
     * store same flights in db
     * all the string values are randomly generated (using random.ints())
     * the status is generated randomly
     * @param provisioning number of fligth to save, if absent store 100 flights
     */

    public void save(Optional<Integer> provisioning){
        int n;
        if(!provisioning.isPresent())
            n=100;
        else n= provisioning.get();
        for (int i = 0; i < n; i++) {
            Flight flight=new Flight(generateString(),generateString(),generateString(),FlightStatus.randomStatus());
            flightRepository.saveAndFlush(flight);
        }

    }

    /**
     *
     *for retrieving all the flights in the db using pagination
     * and returning them in ascending order by fromAirport
     * @param page number of page
     * @param size number of flights in a page
     * @return all flights in database
     */



    public Page<Flight> findAll(int page, int size){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "fromAirport"));

            Pageable pageable = PageRequest.of(page, size,sort);
            return flightRepository.findAll(pageable);

    }

    /**
     * Generates a random alphabetic string with 10 characters
     * @return a random String
     */
    private String generateString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * retrieving all the flights that are ONTIME
     *
     * @return a list of flights in status ONTIME
     */

    public List<Flight> getByStatus() {
        List<Flight> flightList=new ArrayList<>();

        for (Flight item:flightRepository.findAll()) {
            if(item.getFlightStatus().equals(FlightStatus.ONTIME)) flightList.add(item);
        }
        return flightList;
    }

    /**
     * retrieving all the flights that are in p1 or in p2 status
     * @param p1 FlightStatus
     * @param p2 FlightStatus
     * @return a Flights list
     */

    public List<Flight> getCustomFlight(FlightStatus p1, FlightStatus p2){

        List<Flight> flightList=new ArrayList<>();

            for (Flight item : flightRepository.findAll()) {
                if (item.getFlightStatus().equals(p1) || item.getFlightStatus().equals(p2)) flightList.add(item);
            }

        return flightList;
    }
}
