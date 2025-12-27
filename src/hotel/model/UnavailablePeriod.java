package hotel.model;

import java.time.LocalDate;

/**
 * Класът UnavailablePeriod описва период от време,
 * в който дадена стая е временно недостъпна.
 */
public class UnavailablePeriod {

    /** Начална дата на недостъпността */
    private LocalDate from;

    /** Крайна дата на недостъпността */
    private LocalDate to;

    /** Причина за недостъпността */
    private String note;


    /**
     * Създава нов период на недостъпност.
     *
     * @param from начална дата
     * @param to крайна дата
     * @param note причина за недостъпността
     */
    public UnavailablePeriod(LocalDate from, LocalDate to, String note) {
        this.from = from;
        this.to = to;
        this.note = note;
    }

    /**
     * Връща началната дата на недостъпността.
     *
     * @return начална дата
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Връща крайната дата на недостъпността.
     *
     * @return крайна дата
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Връща бележката за недостъпността.
     *
     * @return бележка
     */
    public String getNote() {
        return note;
    }
}
