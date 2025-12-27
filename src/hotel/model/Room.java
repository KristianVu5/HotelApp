package hotel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класът Room представя стая в хотел.
 * <p>
 * Всяка стая има уникален номер, определен брой легла и
 * списъци с периоди на заетост (резервации) и периоди,
 * в които стаята е временно недостъпна.
 * </p>
 */

public class Room {
    /** Уникален номер на стаята */
    private int number;

/** Брой легла в стаята */
    private int beds;

    /** Списък с всички резервации за стаята */
    private List<Reservation> reservations;

    /** Списък с периоди, в които стаята е недостъпна */
    private List<UnavailablePeriod> unavailablePeriods;

    /**
     * Създава нова стая с даден номер и брой легла.
     *
     * @param number номер на стаята
     * @param beds брой легла в стаята
     */

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
        this.reservations = new ArrayList<>();
        this.unavailablePeriods = new ArrayList<>();
    }

    /**
     * Връща номера на стаята.
     *
     * @return номер на стаята
     */
    public int getNumber() {
        return number;
    }

    /**
     * Връща броя на леглата в стаята.
     *
     * @return брой легла
     */
    public int getBeds() {
        return beds;
    }

    /**
     * Връща списък с резервациите за стаята.
     *
     * @return списък с резервации
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Връща списък с периодите, в които стаята е недостъпна.
     *
     * @return списък с недостъпни периоди
     */
    public List<UnavailablePeriod> getUnavailablePeriods() {
        return unavailablePeriods;
    }
}
