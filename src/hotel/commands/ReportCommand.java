package hotel.commands;

import hotel.Main;
import hotel.core.Hotel;

import java.time.LocalDate;
import java.util.Map;

/**
 * Команда за справка за използването на стаите.
 */
public class ReportCommand implements Command{
    /** Референция към хотела */
    private Hotel hotel;

    /**
     * Създава нова report команда.
     * @param hotel хотелът
     */
    public ReportCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата report.
     * Формат:
     * report from to;
     * @param args аргументи от командния ред
     */
    @Override
    public void execute(String[] args) {
        if(args.length != 3){
            System.out.println("Usage: report <from> <to>");
            return;
        }
        LocalDate from = LocalDate.parse(args[1]);
        LocalDate to = LocalDate.parse(args[2]);

        Map<Integer, Long> report = hotel.getUsageReport(from, to);

        if(report.isEmpty()){
            System.out.println("No room usage in this period.");
            return;
        }

        System.out.println("Room usage report: ");
        for(Map.Entry<Integer, Long> entry : report.entrySet()) {
            System.out.println("Room " + entry.getKey() + " used for " + entry.getValue() + " days.");
        }
    }
}
