package co.develhope.CustomQueries2.entities;

import java.util.List;
import java.util.Random;

/**
 * @author Tania Ielpo
 */

public enum FlightStatus {
    //the possible values are ONTIME, DELAYED and CANCELLED

    ONTIME,
    DELAYED,
    CANCELLED;

    private static final List<FlightStatus> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * generate a random status
     * @return a random status
     */
    public static FlightStatus randomStatus()  {

        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
