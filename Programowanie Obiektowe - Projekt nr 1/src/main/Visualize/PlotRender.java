package Visualize;

import MapDetails.Map;
import Objects.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PlotRender extends JPanel {
    private final Map map;
    private final Map map2;
    private final Simulation simulation;
    private final int daysToSaveFile;
    private int totalDays=0;
    private int totalAnimals=0;
    private int totalGrasses=0;
    private float avgChildren=0;
    private float avgLifeSpanDead=0;
    private final int[] gensDomination;

    private int totalAnimals2=0;
    private int totalGrasses2=0;
    private float avgChildren2=0;
    private float avgLifeSpanDead2=0;
    private final int[] gensDomination2;

    public PlotRender(Map map,Map map2, Simulation simulation,int daysToSaveFile) {
        this.map = map;
        this.map2 = map2;
        this.simulation = simulation;
        this.daysToSaveFile = daysToSaveFile;
        this.gensDomination = new int[8];
        this.gensDomination2 = new int[8];
    }

    @Override
    protected void paintComponent(Graphics g) {
        int dominating,dominating2;
        File fOut;
        int width = this.getWidth();
        int height = this.getHeight();
        super.paintComponent(g);
        this.setSize((int) (simulation.getFrame().getWidth() * 0.4), simulation.getFrame().getHeight() - 38); //38 is toolbar size
        this.setLocation(0, 0);
        this.totalDays++;
        this.totalAnimals += this.map.getAnimals().size();
        this.totalGrasses += this.map.getGrass().size();
        this.avgChildren += this.map.getAverageChildrens();
        this.avgLifeSpanDead += this.map.getAverageLifespanDead();
        this.gensDomination[map.getDominatingGen()]++;

        this.totalAnimals2 += this.map2.getAnimals().size();
        this.totalGrasses2 += this.map2.getGrass().size();
        this.avgChildren2 += this.map2.getAverageChildrens();
        this.avgLifeSpanDead2 += this.map2.getAverageLifespanDead();
        this.gensDomination2[map2.getDominatingGen()]++;

        if(this.totalDays==this.daysToSaveFile){
            try {
                fOut = new File("Statistics.txt");
                FileOutputStream fos = new FileOutputStream(fOut);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                dominating = getTheMostDominating(gensDomination);
                dominating2 = getTheMostDominating(gensDomination2);
                bw.write("Number of days passed is " + totalDays);
                bw.newLine();
                bw.newLine();
                bw.write("Data for the first map");
                bw.newLine();
                bw.newLine();
                bw.write("Average amount of animals on map is " + (totalAnimals/totalDays));
                bw.newLine();
                bw.write("Average amount of grass on map is " + (totalGrasses/totalDays));
                bw.newLine();
                bw.write("Average amount of alive children of alive parents between days is " + (avgChildren/totalDays));
                bw.newLine();
                bw.write("Average amount of lifespan for dead animals between days is " + (avgLifeSpanDead/totalDays));
                bw.newLine();
                bw.write("The most dominating gene is " + dominating);
                bw.newLine();
                bw.newLine();
                bw.write("Data for the second map");
                bw.newLine();
                bw.newLine();
                bw.write("Average amount of animals on map is " + (totalAnimals2/totalDays));
                bw.newLine();
                bw.write("Average amount of grass on map is " + (totalGrasses2/totalDays));
                bw.newLine();
                bw.write("Average amount of alive children of alive parents between days is " + (avgChildren2/totalDays));
                bw.newLine();
                bw.write("Average amount of lifespan for dead animals between days is " + (avgLifeSpanDead2/totalDays));
                bw.newLine();
                bw.write("The most dominating gene is " + dominating2);
                bw.close();

            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

        g.drawString("Number of animals alive after n-days: " + map.getAnimals().size(),10,15);
        g.drawString("Number of plants on map after n-days: " + map.getGrass().size(),10,30);
        g.drawString("Average number of kids: " + map.getAverageChildrens(),10,45);
        g.drawString("Average number of energy: " + map.getAverageEnergy(),10,60);
        g.drawString("Average number of days survived for dead animals: " + map.getAverageLifespanDead(),10,75);
        g.drawString("Dominating gene: " + map.getDominatingGen(),10,90);
        g.drawString("Total days: " + totalDays, 10, 105);
        g.drawString("Data for second map: ", 10, 130);
        g.drawString("Number of animals alive after n-days: " + map2.getAnimals().size(),10,145);
        g.drawString("Number of plants on map after n-days: " + map2.getGrass().size(),10,160);
        g.drawString("Average number of kids: " + map2.getAverageChildrens(),10,175);
        g.drawString("Average number of energy: " + map2.getAverageEnergy(),10,190);
        g.drawString("Average number of days survived for dead animals: " + map2.getAverageLifespanDead(),10,205);
        g.drawString("Dominating gene: " + map2.getDominatingGen(),10,220);
        g.drawString("Total days: " + totalDays, 10, 235);
        Plot p1 = new Plot(width - 10, height * 2 / 3 - 50, 5, height * 2 / 3 - 30);

        g.setColor(new Color(0, 0, 0));
        g.drawString("Legend to know what is going on: ", p1.cpx, p1.cpy + height / 10);
        int yp = p1.cpy + height / 10;
        int a = height / 22;
        g.drawString("Map outside of Jungle", p1.cpx + width / 10, yp + a);
        g.drawString("Jungle", p1.cpx + width / 10, yp + 2 * a);
        g.drawString("Animal (Darker -> More energy to use)", p1.cpx + width / 10, yp + 3 * a);
        g.drawString("Grass", p1.cpx + width / 10, yp + 4 * a);

        g.setColor(new Color(170, 224, 103));
        g.fillRect(p1.cpx, yp + a - 10, width / 20, height / 40);

        g.setColor(new Color(0, 160, 7));
        g.fillRect(p1.cpx, yp + 2 * a - 10, width / 20, height / 40);

        g.setColor(new Color(146, 82, 73));
        g.fill3DRect(p1.cpx, yp + 3 * a - 10, width / 20, height / 40,false);

        g.setColor(new Color(181, 222, 31));
        g.fillRect(p1.cpx, yp + 4 * a - 10, width / 20, height / 40);
    }
    private int getTheMostDominating(int[] occurrences){
        int i,index,maxC;
        index = 0;
        maxC = 0;
        for(i=0;i<occurrences.length;i++){
            if(occurrences[i]>maxC){
                index = i;
                maxC = occurrences[i];
            }
        }
        return index;
    }
}
