/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacegame.world.player;

import spacegame.world.ships.Fleet;
import spacegame.world.ships.Ship;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * This class contains the information specific to the player Examples of
 * information to put in this file : Character's names, the current game date,
 * The character's Structures (like buildings and ships) etc.
 *
 * @author user
 */
public class PlayerInfo implements Serializable {

    private static final Logger LOG = Logger.getLogger(PlayerInfo.class.getName());
    private static final long serialVersionUID = 1L;

    private String name;
    private String firstName;
    private Species species;
    private Gender gender;

    // move this to game state also
    private StarDate currentDate;
    
    // remove transient once the Ship class is fixed
    private transient Fleet fleet;

    // move this to game state
    private String currentSystem;

    /**
     * Instanciate a new player infos
     * @param name
     * @param firstName
     * @param species
     * @param gender 
     */
    public PlayerInfo(String name, String firstName, Species species, Gender gender) {
        this.name = name;
        this.firstName = firstName;
        this.species = species;
        this.gender = gender;
        currentDate = new StarDate();
        currentSystem = "test";
        fleet = new Fleet();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(name);
        s.writeUTF(firstName);
        s.writeObject(species);
        s.writeObject(gender);
        s.writeObject(currentDate);
        // will need to refactor ship to be able to save and load them
        //    s.writeObject(fleet);
        s.writeUTF(currentSystem);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        name = s.readUTF();
        firstName = s.readUTF();
        species = (Species) s.readObject();
        gender = (Gender) s.readObject();
        currentDate = (StarDate) s.readObject();
        // will need to refactor ship to be able to save and load them
    //    fleet = (Fleet) s.readObject();
        currentSystem = s.readUTF();
    }

    // this will need to be removed once the Ship class is refactored
    public void createFleet(){
        fleet = new Fleet();
    }

    public String getInfo() {
        return firstName + " " + name + "\n" + gender + " " + species + "\n" + "In System : " + currentSystem + "\n" + currentDate.toLongString();
    }

    public String getFullName() {
        return firstName + " " + name;
    }

    public String getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(String system) {
        currentSystem = system;
    }

    public StarDate getStarDate() {
        return currentDate;
    }

    public Ship getFlagship(){
        return fleet.getFlagship();
    }

    public Fleet getFleet() {
        return fleet;
    }
}
