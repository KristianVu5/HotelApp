package hotel;

import hotel.commands.*;
import hotel.core.Hotel;
import hotel.model.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        Hotel hotel = new Hotel();

        hotel.addRoom(new Room(101,2));
        hotel.addRoom(new Room(102,3));
        hotel.addRoom(new Room(201,4));

        Map<String, Command> commands = new HashMap<>();
        commands.put("checkin", new CheckInCommand(hotel));
        commands.put("checkout", new CheckOutCommand(hotel));
        commands.put("availability", new AvailabilityCommand(hotel));
        commands.put("report", new ReportCommand(hotel));
        commands.put("find", new FindCommand(hotel));
        commands.put("unavailable", new UnavailableCommand(hotel));
        commands.put("find!", new FindEmergencyCommand(hotel));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hotel system started.\n" +
                "Type 'exit to quit.");
        System.out.println("==========Welcome to our hotel!==========");



        while(true){
            System.out.println("Please type a command... ");
            String line = scanner.nextLine();

            if(line.equalsIgnoreCase("exit")) {
                break;
            }
            String[] tokens = line.split("\\s+");
            Command command = commands.get(tokens[0]);
            
            if(command == null){
                System.out.println("Unknown command.");
                continue;
            }
            try {
                command.execute(tokens);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");

    }
}
