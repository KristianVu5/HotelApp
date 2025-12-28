package hotel.commands;

import hotel.core.Hotel;

import java.time.LocalDate;

/**
 * Команда за настаняване в стая.
 * Обработва входните аргументи от командния ред
 * и извиква съответната логика в класа Hotel.
 */
public class CheckInCommand implements Command{
    /** Референция към хотела */
    private Hotel hotel;

    /**
     * Създава нова команда за настаняване.
     *
     * @param hotel хотелът, върху който се изпълнява командата
     */
    public CheckInCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата checkin.
     *
     * Формат:
     * checkin &lt;room&gt; &lt;from&gt; &lt;to&gt; &lt;note&gt; [guests]
     *
     * @param args аргументи от командния ред
     */
    @Override
    public void execute(String[] args) {
        if(args.length < 5){
            System.out.println("Usage: checkin <room> <from> <to> <note> [guests]");
            return;
        }

        int roomNumber = Integer.parseInt(args[1]);
        LocalDate from = LocalDate.parse(args[2]);
        LocalDate to = LocalDate.parse(args[3]);
        String note = args[4];

        int guests;
        if(args.length >= 6){
            guests = Integer.parseInt(args[5]);
        } else {
            guests = 0; //трябва да се оправи
        }

        boolean success = hotel.checkIn(roomNumber, from, to, note, guests);

        if(success){
            System.out.println("Check-in successful.");
        } else {
            System.out.println("Check-in failed.");
        }

    }
}
