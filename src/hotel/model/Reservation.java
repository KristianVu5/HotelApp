package hotel.model;

import java.time.LocalDate;

/**
 * Класът Reservation представя престой на гости в стая
 * за определен период от време.
 */
public class Reservation {
    /** Начална дата на престоя */
    private LocalDate from;

    /** Крайна дата на престоя */
    private LocalDate to;

    /** Бележка към резервацията */
    private String note;

    /** Брой на настанените гости */
    private int guests;

    /**
     * Създава нова резервация.
     *
     * @param from начална дата
     * @param to крайна дата
     * @param note бележка към резервацията
     * @param guests брой гости
     */

    public Reservation(LocalDate from, LocalDate to, String note, int guests) {
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
    }

    /**
     * Връща началната дата на резервацията.
     *
     * @return начална дата
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Връща крайната дата на резервацията.
     *
     * @return крайна дата
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Връща бележката към резервацията.
     *
     * @return бележка
     */
    public String getNote() {
        return note;
    }

    /**
     * Връща броя на гостите.
     *
     * @return брой гости
     */
    public int getGuests() {
        return guests;
    }
}
