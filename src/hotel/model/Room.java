package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int number;
    private int beds;
    private List<Reservation> reservations;
    private List<UnavailablePeriod> unavailablePeriods;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
        this.reservations = new ArrayList<>();
        this.unavailablePeriods = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public int getBeds() {
        return beds;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<UnavailablePeriod> getUnavailablePeriods() {
        return unavailablePeriods;
    }
}
