package hotel.commands;

import hotel.core.Hotel;
import hotel.model.Room;

import java.io.IOException;

/**
 * Команда за извеждане на програмата на стая.
 */
public class ProgramCommand implements Command{

    private Hotel hotel;

    public ProgramCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) throws IOException {
        if(args.length < 2){
            System.out.println("Usage: program <room>");
            return;
        }

        int roomNumber = Integer.parseInt(args[1]);
        Room room = hotel.getRoomByNumber(roomNumber);

        if(room == null){
            System.out.println("Room not found.");
            return;
        }
        room.printProgram();
    }
}
