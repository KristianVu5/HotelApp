package hotel.commands;

import hotel.core.Hotel;

import java.time.LocalDate;

/**
 * Команда за обявяване на стая за временно недостъпна.
 */
public class UnavailableCommand implements Command  {

    /** Референция към Hotel*/
    private Hotel hotel;

    /**
     * Създава нова unavailable команда.
     * @param hotel хотелът
     */

    public UnavailableCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) {
        if(args.length < 5){
            System.out.println("Usage: unavailable <room> <from> <to> <note>");
            return;
        }

        int room = Integer.parseInt(args[1]);
        LocalDate from = LocalDate.parse(args[2]);
        LocalDate to = LocalDate.parse(args[3]);
        String note = String.join("", java.util.Arrays.copyOfRange(args, 4, args.length));
        boolean success = hotel.markRoomUnavailable(room, from, to, note);

        if(success){
            System.out.println("Room marked as unavailable.");
        } else {
            System.out.println("Room not found.");
        }
    }
}
