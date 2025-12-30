package hotel.core;

import hotel.model.Activity;
import hotel.model.Reservation;
import hotel.model.Room;
import hotel.model.UnavailablePeriod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Класът Hotel представлява хотел и управлява всички стаи в него.
 * Отговаря за настаняване, освобождаване, търсене на стаи и справки.
 */
public class Hotel {

    private List<Room> rooms;
    private Map<String, Activity> activities;

    /**
     * Създава нов хотел без стаи.
     */
    public Hotel() {
        this.rooms = new ArrayList<>();
        this.activities = new HashMap<>();
    }

    /** Втръща стая*/
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Добавя стая към хотела.
     * @param room стая за добавяне
     */
    public void addRoom(Room room){
        rooms.add(room);
    }

    /**
     * Намира стая по номер.
     * @param roomNumber номер на стаята
     * @return стаята или null ако не е намерена
     */
    public Room findRoom(int roomNumber){
        for (Room room : rooms){
            if(room.getNumber() == roomNumber){
                return room;
            }
        }
        return null;
    }

    /**
     * Регистрира престой в дадена стая.
     * @param roomNumber номер на стаята
     * @param from начална дата
     * @param to крайна дата
     * @param note бележка
     * @param guests брой гости
     * @return true ако настаняването е успешно
     */
    public boolean checkIn(int roomNumber, LocalDate from, LocalDate to,
                           String note, int guests){

        Room room = findRoom(roomNumber);
        if(room == null){
            return false;
        }

        if(!room.isAvailable(from, to)){
            return false;
        }

        room.getReservations().add(new Reservation(from, to, note, guests));
        return true;
    }

    /**
     * Освобождава дадена стая (премахва текущата резервация).
     * @param roomNumber номер на стаята
     * @return true ако освобождаването е успешно
     */
    public boolean checkOut(int roomNumber){
        Room room = findRoom(roomNumber);
        if(room == null){
            return false;
        }

        LocalDate today = LocalDate.now();

        return room.getReservations().removeIf(r -> !today.isBefore(r.getFrom()) && !today.isAfter(r.getTo()));
    }


    /**
     * Връща списък със свободните стаи на дадена дата.
     * @param date дата за проверка
     * @return списък със свободни стаи
     */
    public List<Room> getAvailableRooms(LocalDate date){
        List<Room> result = new ArrayList<>();

        for(Room room : rooms){
            if(room.isAvailable(date,date)){
                result.add(room);
            }
        }
        return result;
    }

    /**
     * Генерира справка за използването на стаите в даден период.
     * @param from начална дата
     * @param to крайна дата
     * @return текстов отчет
     */
    public List<String> report(LocalDate from, LocalDate to){
        List<String> result = new ArrayList<>();

        for(Room room : rooms){
            int usedDays = 0;

            for(Reservation r : room.getReservations()) {
                LocalDate start = r.getFrom().isBefore(from) ? from : r.getFrom();
                LocalDate end = r.getTo().isAfter(to) ? to : r.getTo();

                if(!start.isAfter(end)){
                    usedDays += start.until(end).getDays() + 1;
                }
            }

            if(usedDays > 0) {
                result.add("Room" + room.getNumber() + ": " + usedDays + " days");
            }
        }
        return result;
    }

    /**
     * Намира подходяща свободна стая с поне даден брой легла.
     * @param beds минимален брой легла
     * @param from начална дата
     * @param to крайна дата
     * @return подходяща стая или null
     */
    public Room find(int beds, LocalDate from, LocalDate to){
        return rooms.stream()
                .filter(r -> r.getBeds() >= beds)
                .filter(r -> r.isAvailable(from, to))
                .min(Comparator.comparing(Room::getBeds))
                .orElse(null);
    }

    /**
     * Обявява стая за временно недостъпна.
     * @param roomNumber номер на стаята
     * @param from начална дата
     * @param to крайна дата
     * @param note причина
     * @return true ако операцията е успешна
     */
    public boolean markUnavailable(int roomNumber, LocalDate from, LocalDate to, String note){
        Room room = findRoom(roomNumber);
        if(room == null){
            return false;
        }

        room.getUnavailablePeriods().add(new UnavailablePeriod(from, to ,note));
        return true;
    }

    /**
     * Генерира справка за използването на стаите в даден период.
     * @param from начална дата
     * @param to крайна дата
     * @return карта със стая и брой използвани дни
     */
    public Map<Integer, Long> getUsageReport(LocalDate from, LocalDate to){

        Map<Integer, Long> result = new HashMap<>();

        for(Room room : rooms){
            long usedDays = 0;

            for(Reservation r : room.getReservations()){
                LocalDate start = r.getFrom().isAfter(from) ? r.getFrom() : from;

                LocalDate end = r.getTo().isBefore(to) ? r.getTo() : to;

                if(!start.isAfter(end)){
                    usedDays += ChronoUnit.DAYS.between(start, end) + 1;
                }
            }

            if(usedDays > 0){
                result.put(room.getNumber(), usedDays);
            }
        }
        return result;
    }

    /**
     * Намира подходяща свободна стая с поне даден брой легла
     * за определен период.
     * @param beds минимален брой легла
     * @param from начална дата
     * @param to крайна дата
     * @return намерената стая или null ако няма подходяща
     */

    public Room findRoom(int beds, LocalDate from, LocalDate to){
        Room bestRoom = null;

        for(Room room : rooms){
            if(room.getBeds() < beds){
                continue;
            }

            if(!room.isAvailable(from, to)){
                continue;
            }
            if(bestRoom == null || room.getBeds() < bestRoom.getBeds()){
                bestRoom = room;
            }

        }
        return bestRoom;
    }

    /**
     * Обявява стая за временно недостъпна.
     * @param roomNumber номер на стаята
     * @param from начална дата
     * @param to крайна дата
     * @param note бележка
     * @return true ако операцията е успешна
     */
    public boolean markRoomUnavailable(int roomNumber, LocalDate from, LocalDate to, String note){
        Room room = getRoomByNumber(roomNumber);
        if(room == null){
            return false;
        }
        room.addUnavailablePeriod(from, to, note);
        return true;
    }

    /**
     * Намира стая по нейн номер.
     * @param roomNumber номер на стаята
     * @return стаята или null ако не съществува
     */
    public Room getRoomByNumber(int roomNumber){
        for(Room room : rooms){
            if(room.getNumber() == roomNumber){
                return room;
            }
        }
        return null;
    }

    public String findEmergencyRoom(int beds, LocalDate from, LocalDate to){
        /** Опит за нормално намиране на стая*/
        Room direct = findRoom(beds, from, to);
        if(direct != null){
            return "Room " + direct.getNumber() + " is available without rearrangement.";
        }

        /** Опит за разместване*/
        for(Room occupied : rooms){
            if(occupied.getBeds() < beds){
                continue;
            }

            /** Търся друга стая за преместване*/
            for(Room alternative : rooms){
                if(alternative == occupied ){
                    continue;
                }

                if(!alternative.isAvailable(from, to)){
                    continue;
                }

                if(alternative.getBeds() >= occupied.getBeds()) {
                    return "Emergency solution: move guests from room " + occupied.getNumber()
                    + " to room " + alternative.getNumber() + " and free room " + occupied.getNumber();
                }
            }
        }
        return "No emergency solution found.";
    }

    public void clear(){
        rooms.clear();
    }

    /**
     * Записва стая за дейност.
     */
    public void registerRoomForActivity(int roomNumber, String activityName){
        Activity activity = activities.get(activityName);

        if(activity == null){
            activity = new Activity(activityName);
            activities.put(activityName, activity);
        }

        activity.addRoom(roomNumber);
    }

    /**
     * Извежда всички стаи, записани за дадена дейност.
     */
    public void printActivityParticipants(String activityName) {
        Activity activity = activities.get(activityName);

        if(activity == null){
            System.out.println("No such activity.");
            return;
        }

        System.out.println("Rooms registered for " + activityName + ":" );
        for(int room : activity.getRoomNumbers()){
            System.out.println("Room " + room);
        }
    }
}
