import MapDetails.PropertiesLoader;
import Menu.Settings;

import java.io.FileNotFoundException;

public class World {
    public static void main(String[] args) {
        try {
            PropertiesLoader p= PropertiesLoader.loadPropFromFile();
            Integer[] defaultMapProperties = {p.getMapWidth(), p.getMapHeight(),
                    p.getJungleWidth(), p.getJungleHeight(),
                    p.getGrassEatingEnergyProfit(), p.getDayEnergyCost(),
                    p.getAnimalsStartEnergy(), p.getNumOfSpawnedAnimals(),
                    p.getDelay(), p.getGrassSpawnedInEachDay(),
                    p.getDaysToSaveFile()
            };
            Settings menu = new Settings();
            menu.startSimulation(defaultMapProperties);

        } catch (IllegalArgumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
