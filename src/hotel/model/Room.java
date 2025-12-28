package hotel.model;

import java.time.LocalDate;
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

    /**
     * Добавих отделен метод, които проверява дали два периода от време се застъпват.
     */
    private boolean periodsOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2){
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    /**
     * Проверява дали стаята е свободна в даден период.
     *
     * @param from начална дата
     * @param to крайна дата
     * @return true ако стаята е свободна
     */
    public boolean isAvailable(LocalDate from, LocalDate to){
        /** Проверка за резервации*/
        for(Reservation r : reservations){
            if(periodsOverlap(from, to, r.getFrom(), r.getTo())){
                return false;
            }
        }
        /** Проверка за недостъпни периоди*/
        for (UnavailablePeriod u : unavailablePeriods){
            if(periodsOverlap(from, to, u.getFrom(), u.getTo())){
                return false;
            }
        }
        return true;
    }

    /**
     * Добавя период, в който стаята е недостъпна.
     *
     * @param from начална дата
     * @param to крайна дата
     * @param note бележка
     */
    public void addUnavailablePeriod(LocalDate from, LocalDate to, String note){
        unavailablePeriods.add(new UnavailablePeriod(from, to, note));
    }
}
