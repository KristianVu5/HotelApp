package hotel.commands;

import hotel.core.Hotel;
import hotel.io.HotelFileManager;

import java.io.File;
import java.io.IOException;

/**
 * Команда за записване на данните в текущия файл.
 */
public class SaveCommand implements Command {

    private Hotel hotel;
    private HotelFileManager fileManager;
    private File currentFile;

    /**
     * Създава команда за запис.
     *
     * @param hotel текущият хотел
     * @param fileManager мениджърът за работа с файлове
     * @param currentFile текущият отворен файл
     */
    public SaveCommand(Hotel hotel, HotelFileManager fileManager, File currentFile) {
        this.hotel = hotel;
        this.fileManager = fileManager;
        this.currentFile = currentFile;
    }

    @Override
    public void execute(String[] args) {
        if(currentFile == null){
            System.out.println("No file is currently open,");
            return;
        }
        try {
            fileManager.save(currentFile, hotel);
            System.out.println("File saved.");
        }catch (IOException e){
            System.out.println("Unable to save the file: " + e.getMessage());
        }
    }
}
