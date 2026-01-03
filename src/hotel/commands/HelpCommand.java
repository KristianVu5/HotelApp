package hotel.commands;

import java.io.IOException;

/**
 * Команда за извеждане на помощна информация.
 * Представлява вградена документация на системата и
 * описва всички налични файлови и хотелски команди,
 * както и начина им на използване.
 */
public class HelpCommand implements Command {

    @Override
    public void execute(String[] args) throws IOException {
        printFileCommands();
        printHotelCommands();
    }

    /**
     * Извежда информация за файловите команди.
     */
    private void printFileCommands() {
        System.out.println("Available file commands: ");
        System.out.println();
        System.out.println("open <file>     - Opens a file and loads hotel data.");
        System.out.println("close           - Closes the currently opened file.");
        System.out.println("save            - Saves changes to the currently opened file.");
        System.out.println("saveas <file>   - Saves data to a new file.");
        System.out.println("help            - Shows this help information.");
        System.out.println("exit            - Exits the program.");
        System.out.println();
    }

    /**
     * Извежда информация за хотелските команди.
     */
    private void printHotelCommands(){
        System.out.println("Available hotel commands: ");
        System.out.println("checkin <room> <from> <to> <note> [guests] - Checks guests into a room.");
        System.out.println("checkout <room>     - Frees an occupied room.");
        System.out.println("availability [date] - Lists available room on a given date.");
        System.out.println("report <from> <to>  - Shows room usage report for a period.");
        System.out.println("find <beds> <from> <to>  - Finds a free room with at least given beds.");
        System.out.println("find! <beds> <from> <to> - Emergency room allocation.");
        System.out.println("unavailable <room> <from> <to> <note> - Marks room as temporary unavailable.");
        System.out.println("activity <room> <activityName> - Registers guests from a room for a specific activity.");
        System.out.println("activitylist <activityName>    - Displays all rooms registered for a given activity.");
        System.out.println();
    }
}


