package hotel.model;

import java.time.LocalDate;

public class UnavailablePeriod {

    private LocalDate from;
    private LocalDate to;
    private String note;

    public UnavailablePeriod(LocalDate from, LocalDate to, String note) {
        this.from = from;
        this.to = to;
        this.note = note;
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
}
