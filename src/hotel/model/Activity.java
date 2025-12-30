package hotel.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Представлява дейност, за която могат да се записват стаи.
 */
public class Activity {

    private String name;
    private Set<Integer> roomNumbers;

    /**
     * Създава дейност с дадено име.
     * @param name име на дейността
     */
    public Activity(String name) {
        this.name = name;
        this.roomNumbers = new HashSet<>();
    }

    /**
     * Записва стая за дейността.
     * @param roomNumber номер на стаята
     */
    public void addRoom(int roomNumber){
        roomNumbers.add(roomNumber);
    }

    /**
     * @return името на дейността
     */
    public String getName(){
        return name;
    }


    /**
     * @return стаите, записани за дейността
     */
    public Set<Integer> getRoomNumbers(){
        return roomNumbers;
    }
}
