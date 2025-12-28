package hotel.commands;

import hotel.core.Hotel;

import java.time.LocalDate;

/**
 * Команда за спешно намиране на стая.
 */
public class FindEmergencyCommand implements Command {

    /** Референция към Hotel.*/
    private Hotel hotel;


    /**
     * Създава find! команда.
     * @param hotel хотелът
     */
    public FindEmergencyCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) {
        if(args.length != 4){
            System.out.println("Usage: find! <beds> <from> <to>");
            return;
        }
        int beds = Integer.parseInt(args[1]);
        LocalDate from = LocalDate.parse(args[2]);
        LocalDate to = LocalDate.parse(args[3]);
        String result = hotel.findEmergencyRoom(beds, from, to);

        System.out.println(result);
    }
}
