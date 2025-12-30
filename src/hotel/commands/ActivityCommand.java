package hotel.commands;

import hotel.core.Hotel;
import hotel.model.Room;

import java.io.IOException;

public class ActivityCommand implements  Command   {
    private Hotel hotel;

    public ActivityCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public void execute(String[] args) {
        if(args.length < 3){
            throw new IllegalArgumentException("Usage: activity <room> <activity>");

        }
        int roomNumber = Integer.parseInt(args[1]);
        String activityName = args[2];

        Room room = hotel.getRoomByNumber(roomNumber);
        if(room == null){
            System.out.println("Room not found.");
            return;
        }

        room.addActivity(activityName);
        hotel.registerRoomForActivity(roomNumber, activityName);

        System.out.println("Room " + roomNumber + " registered for " + activityName);
    }
}
