package hotel;

import hotel.commands.*;
import hotel.core.Hotel;
import hotel.io.HotelFileManager;
import hotel.model.Room;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * Текущият файл, с който работи приложението.
     * Използва се при командите open, save и close.
     */
    private static File currentFile = null;

    /**
     * Мениджър за работа с файлове – отговаря за
     * зареждането и записването на данните за хотела.
     */
    private static HotelFileManager fileManager = new HotelFileManager();

    public static void main(String[] args) {


        Hotel hotel = new Hotel();



        hotel.addRoom(new Room(101,2));
        hotel.addRoom(new Room(102,3));
        hotel.addRoom(new Room(201,4));
        hotel.addRoom(new Room(202, 5));

        Map<String, Command> commands = new HashMap<>();
        commands.put("checkin", new CheckInCommand(hotel));
        commands.put("checkout", new CheckOutCommand(hotel));
        commands.put("availability", new AvailabilityCommand(hotel));
        commands.put("report", new ReportCommand(hotel));
        commands.put("find", new FindCommand(hotel));
        commands.put("unavailable", new UnavailableCommand(hotel));
        commands.put("find!", new FindEmergencyCommand(hotel));
        commands.put("open", new OpenCommand(hotel, fileManager));
        commands.put("save", new SaveCommand(hotel, fileManager, currentFile));
        commands.put("saveas", new SaveAsCommand(hotel, fileManager));
        commands.put("close", new CloseCommand());
        commands.put("activity", new ActivityCommand(hotel));
        commands.put("program", new ProgramCommand(hotel));
        commands.put("activitylist", new ActivityListCommand(hotel));
        commands.put("help", new HelpCommand());



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
