package hotel.commands;

import hotel.core.Hotel;
import hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public class AvailabilityCommand implements Command{

    private Hotel hotel;

    public AvailabilityCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) {
        LocalDate date;

        if(args.length == 1){
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(args[1]);
        }

        List<Room> availableRooms = hotel.getAvailableRooms(date);

        if(availableRooms.isEmpty()){
            System.out.println("No available rooms.");
            return;
        }

        System.out.println("Available rooms on " + date + " : ");
        for (Room room : availableRooms){
            System.out.println("Room " + room.getNumber() + " (" + room.getBeds() + " beds).");
        }
    }
}
