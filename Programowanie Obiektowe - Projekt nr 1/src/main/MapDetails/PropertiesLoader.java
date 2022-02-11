package MapDetails;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesLoader {

    private int mapWidth;
    private int mapHeight;
    private int jungleWidth;
    private int jungleHeight;
    private int grassEatingEnergyProfit;
    private int dayEnergyCost;
    private int animalsStartEnergy;
    private int numOfSpawnedAnimals;
    private int delay;
    private int grassSpawnedInEachDay;
    private int daysToSaveFile;

    static public PropertiesLoader loadPropFromFile() throws FileNotFoundException,IllegalArgumentException {
        Gson gson = new Gson();
        File f = new File("");
        System.out.println(f.getAbsolutePath());
        PropertiesLoader properties = gson.fromJson(new FileReader("src\\main\\MapDetails\\config.json"), PropertiesLoader.class);
        properties.validate();
        return properties;
    }

    public void validate() throws IllegalArgumentException{
        if(this.mapWidth <= 0){
            throw new IllegalArgumentException("Invalid mapWidth");
        }
        if(this.mapHeight <= 0){
            throw new IllegalArgumentException("Invalid mapHeight");
        }
        if(this.jungleHeight <= 0){
            throw new IllegalArgumentException("Invalid jungleWidth");
        }
        if(this.jungleWidth<= 0){
            throw new IllegalArgumentException("Invalid jungleHeight");
        }
        if(this.animalsStartEnergy < 0){
            throw new IllegalArgumentException("Invalid animalsStartEnergy");
        }
        if(this.numOfSpawnedAnimals < 0){
            throw new IllegalArgumentException("Invalid numOfSpawnedAnimals");
        }
        if(this.grassSpawnedInEachDay < 0){
            throw new IllegalArgumentException("Invalid grassSpawnedInEachDay");
        }
        if(this.daysToSaveFile < 0){
            throw new IllegalArgumentException("Invalid daysToSaveFile");
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public void setJungleWidth(int jungleWidth) {
        this.jungleWidth = jungleWidth;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public void setJungleHeight(int jungleHeight) {
        this.jungleHeight = jungleHeight;
    }

    public int getGrassEatingEnergyProfit() {
        return grassEatingEnergyProfit;
    }

    public void setGrassEatingEnergyProfit(int grassEatingEnergyProfit) {
        this.grassEatingEnergyProfit = grassEatingEnergyProfit;
    }

    public int getDayEnergyCost() {
        return dayEnergyCost;
    }

    public void setDayEnergyCost(int dayEnergyCost) {
        this.dayEnergyCost = dayEnergyCost;
    }

    public int getAnimalsStartEnergy() {
        return animalsStartEnergy;
    }

    public void setAnimalsStartEnergy(int animalsStartEnergy) {
        this.animalsStartEnergy = animalsStartEnergy;
    }

    public int getNumOfSpawnedAnimals() {
        return numOfSpawnedAnimals;
    }

    public void setNumOfSpawnedAnimals(int numOfSpawnedAnimals) {
        this.numOfSpawnedAnimals = numOfSpawnedAnimals;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getGrassSpawnedInEachDay() {
        return grassSpawnedInEachDay;
    }

    public void setGrassSpawnedInEachDay(int grassSpawnedInEachDay) {
        this.grassSpawnedInEachDay = grassSpawnedInEachDay;
    }

    public void setDaysToSaveFile(int daysToSaveFile){
        this.daysToSaveFile = daysToSaveFile;
    }

    public int getDaysToSaveFile(){
        return this.daysToSaveFile;
    }
}
