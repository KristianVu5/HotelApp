package hotel.commands;

import hotel.core.Hotel;
import hotel.model.Room;

import java.time.LocalDate;

public class FindCommand implements Command {
    private Hotel hotel;

    public FindCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) {
        if(args.length != 4){
            System.out.println("Usage: find <beds> <from> <to>");
            return;
        }

        int beds = Integer.parseInt(args[1]);
        LocalDate from = LocalDate.parse(args[2]);
        LocalDate to = LocalDate.parse(args[3]);

        Room room = hotel.findRoom(beds, from, to);

        if(room == null){
            System.out.println("No suitable room found.");
        } else {
            System.out.println("Found room " + room.getNumber() + " with " + room.getBeds() + " beds.");
        }
    }
}
