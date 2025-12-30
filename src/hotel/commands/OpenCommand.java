package hotel.commands;

import hotel.core.Hotel;
import hotel.io.HotelFileManager;


import java.io.File;
import java.io.IOException;

/**
 * Команда за отваряне на файл и зареждане на данните за хотела.
 */
public class OpenCommand implements Command{

    private Hotel hotel;
    private HotelFileManager fileManager;

    /**
     * Създава команда за отваряне на файл.
     * @param hotel текущият хотел
     * @param fileManager мениджърът за работа с файлове
     */
    public OpenCommand(Hotel hotel, HotelFileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }


    @Override
    public void execute(String[] args) {
        if(args.length < 2){
            System.out.println("Usage: open <file>");
            return;
        }

        File file = new File(args[1]);
        try {
            fileManager.load(file, hotel);
            System.out.println("File opened successfully.");
        }catch (IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}
