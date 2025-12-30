package hotel.io;

import hotel.core.Hotel;
import hotel.model.Reservation;
import hotel.model.Room;
import hotel.model.UnavailablePeriod;

import java.io.*;
import java.time.LocalDate;

/**
 * Отговаря за зареждане и записване на хотелските данни във файл.
 */
public class HotelFileManager {

    /**
     * Зарежда данните за хотела от файл.
     * @param file файлът, от който се чете
     * @return хотел с заредени данни
     * @throws IOException при проблем с файла
     */
    public Hotel load(File file, Hotel hotel) throws IOException{

        BufferedReader reader = new BufferedReader(new FileReader(file));
        hotel.clear();

        String line;
        Room currentRoom = null;

        while ((line = reader.readLine()) != null){
            String[] parts = line.split(" ");

            if(parts[0].equals("ROOM")){
                int number = Integer.parseInt(parts[1]);
                int beds = Integer.parseInt(parts[2]);
                currentRoom = new Room(number, beds);
                hotel.addRoom(currentRoom);
            } else if(parts[0].equals("RESERVATION")) {
                LocalDate from = LocalDate.parse(parts[1]);
                LocalDate to = LocalDate.parse(parts[2]);
                String note = parts[3];
                int guests = Integer.parseInt(parts[4]);

                currentRoom.addReservation(new Reservation(from, to, note, guests));

            } else if(parts[0].equals("UNAVAILABLE")){
                LocalDate from = LocalDate.parse(parts[1]);
                LocalDate to = LocalDate.parse(parts[2]);
                String note = parts[3];

                currentRoom.addUnavailablePeriod(from, to, note);
            } else if(parts[0].equals("ENDROOM")) {
                currentRoom = null;
            }
        }
        reader.close();
        return hotel;
    }

    public void save(File file, Hotel hotel) throws IOException{

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for(Room room : hotel.getRooms()){
            writer.write("ROOM " + room.getNumber() + " " + room.getBeds());
            writer.newLine();

            for(Reservation r : room.getReservations()) {
                writer.write("RESERVATION " + r.getFrom() + " "
                + r.getTo() + " "
                + r.getNote() + " "
                + r.getGuests());
                writer.newLine();
            }

            for(UnavailablePeriod u : room.getUnavailablePeriods()) {
                writer.write("UNAVAILABLE " + u.getFrom() + " "
                + u.getTo() + " "
                + u.getNote());
                writer.newLine();
            }

            writer.write("ENDROOM");
            writer.newLine();
            writer.newLine();
        }
        writer.close();
    }
}
