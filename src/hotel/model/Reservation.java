package hotel.model;

import java.time.LocalDate;

public class Reservation {

    private LocalDate from;
    private LocalDate to;
    private String note;
    private int guests;

    public Reservation(LocalDate from, LocalDate to, String note, int guests) {
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public String getNote() {
        return note;
    }

    public int getGuests() {
        return guests;
    }
}
