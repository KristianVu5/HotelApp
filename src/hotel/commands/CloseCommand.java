package hotel.commands;

import java.io.IOException;

/**
 * Команда за затваряне на текущия файл.
 */
public class CloseCommand implements  Command {

    /**
     * Затваря текущия файл.
     */
    @Override
    public void execute(String[] args) {
        System.out.println("File closed.");
    }
}
