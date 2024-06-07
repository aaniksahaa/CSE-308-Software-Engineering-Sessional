package spaceship;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaceShip {
    private String name;
    private HashMap<String, Passenger>passengerHashMap = new HashMap<>();
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public SpaceShip(String name){
        this.name = name;
    }
    public void addPassenger(String name, Passenger passenger){
        passengers.add(passenger);
        passengerHashMap.put(name, passenger);
    }
    public Passenger authenticate(String name){
        if(passengerHashMap.containsKey(name)){
            return passengerHashMap.get(name);
        }
        else {
            return null;
        }
    }

}
