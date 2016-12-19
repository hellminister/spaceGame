/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacegame.world.systems.datablocks;

import spacegame.util.Utilities;
import spacegame.world.systems.BubbleSystem;
import spacegame.world.systems.CelestialBody;

import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author user
 */
public class BasicDataBlock extends DataBlock {

    private static final Logger LOG = Logger.getLogger(BasicDataBlock.class.getName());

    private static final Set<String> treatedProperties = Utilities.newUnmodifiableSet("id");


    private String name;

    BasicDataBlock() {
        super(InfoType.BASIC);
    }

    public static Set<String> treatsProperty() {
        return treatedProperties;
    }

    @Override
    Set<String> treatsInfo() {
        return treatsProperty();
    }

    @Override
    public Properties toProperties() {
        return new Properties();
    }

    @Override
    public void treatProperty(String prop, String value) {
        switch (prop) {
            case "id":
                name = value;
                break;
            default:
                LOG.warning("No treatment for property : " + prop + " with value : " + value);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean checkIntegrity(BubbleSystem system, CelestialBody me) {
        return true;
    }

}
