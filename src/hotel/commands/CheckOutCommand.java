package hotel.commands;

import hotel.core.Hotel;

/**
 * Команда за освобождаване на стая.
 */
public class CheckOutCommand implements Command {
    /** Референция към хотела */
    private Hotel hotel;

    /**
     * Създава нова команда за освобождаване на стая.
     * @param hotel хотелът, върху който се изпълнява командата
     */
    public CheckOutCommand(Hotel hotel) {
        this.hotel = hotel;
    }


    /**
     * Изпълнява командата checkout.
     * Формат:
     * checkout &lt;room&gt;
     * @param args аргументи от командния ред
     */
    @Override
    public void execute(String[] args) {
        if(args.length != 2){
            System.out.println("Usage: checkout <room>");
            return;
        }

        int roomNumber = Integer.parseInt(args[1]);
        boolean success = hotel.checkOut(roomNumber);

        if(success) {
            System.out.println("Check-out successful.");
        } else {
            System.out.println("Check-out failed.");
        }
    }
}
