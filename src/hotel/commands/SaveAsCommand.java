package hotel.commands;

import hotel.core.Hotel;
import hotel.io.HotelFileManager;

import java.io.File;
import java.io.IOException;

/**
 * Команда за записване на данните в нов файл.
 */
public class SaveAsCommand implements Command{

    private Hotel hotel;
    private HotelFileManager fileManager;

    /**
     * Създава команда за запис в нов файл.
     * @param hotel текущият хотел
     * @param fileManager мениджърът за работа с файлове
     */
    public SaveAsCommand(Hotel hotel, HotelFileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }


    @Override
    public void execute(String[] args) {
        if(args.length < 2){
            System.out.println("Usage: saveas <file>");
            return;
        }

        try {
            File file = new File(args[1]);
            fileManager.save(file, hotel);
            System.out.println("File saved as: " + file.getName());
        } catch (IOException e){
            System.out.println("File can not be saved: " + e.getMessage());
        }
    }
}
