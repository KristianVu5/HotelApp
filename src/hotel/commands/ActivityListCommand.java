package hotel.commands;

import hotel.core.Hotel;

import java.io.IOException;

public class ActivityListCommand implements Command{
    private Hotel hotel;

    public ActivityListCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length < 2){
            System.out.println("Usage: activitylist <activity>");
            return;
        }

        String activityName = args[1];
        hotel.printActivityParticipants(activityName);
    }
}
