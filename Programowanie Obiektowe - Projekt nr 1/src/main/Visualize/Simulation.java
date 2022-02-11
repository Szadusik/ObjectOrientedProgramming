package Visualize;

import MapDetails.Map;
import Objects.Animal;
import Objects.Vector2d;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Simulation implements ActionListener{
    private final Map map;
    private final Map map2;
    private final int startNumOfAnimals;
    private final int grassSpawnedInEachDay;
    private final JFrame frame;
    private final Render renderPanel;
    private final Render renderPanel2;
    private final PlotRender plotRenderPanel;
    private final Timer timer;

    public Simulation(Map map,Map map2, int delay, int startNumOfAnimals, int grassSpawnedInEachDay, int daysToSaveFile) {
        this.map = map;
        this.map2 = map2;
        this.startNumOfAnimals = startNumOfAnimals;
        this.grassSpawnedInEachDay = grassSpawnedInEachDay;
        JButton stopButton = new JButton("Stop animation");
        JButton resumeButton = new JButton("Resume animation");

        timer = new Timer(delay, this);
        frame = new JFrame("Evolution Simulator");

        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        renderPanel = new Render(map, this,0);
        renderPanel.setSize(new Dimension(1, 1));
        renderPanel2 = new Render(map2,this,1);
        renderPanel2.setSize(new Dimension(1, 1));

        plotRenderPanel = new PlotRender(map,map2, this,daysToSaveFile);
        plotRenderPanel.setSize(1, 1);

        MouseListener click = new clicker();
        frame.add(renderPanel);
        frame.add(plotRenderPanel);
        frame.add(renderPanel2);
        frame.addMouseListener(click);

        stopButton.addActionListener(new stopButton());
        resumeButton.addActionListener(new startButton());

        frame.getContentPane().add(BorderLayout.SOUTH, stopButton);
        frame.getContentPane().add(BorderLayout.NORTH, resumeButton);
    }

    public void startSimulation() {
        for (int i = 0; i < startNumOfAnimals; i++) {
            map.addAnimalOnRandomField();
            map.placeAnimalToRandomFieldInJungle();
            map2.addAnimalOnRandomField();
            map2.placeAnimalToRandomFieldInJungle();
        }
        timer.start();
    }

    @Override
    //It will executed when timer finished counting
    public void actionPerformed(ActionEvent e){
            plotRenderPanel.repaint();
            renderPanel.repaint();
            renderPanel2.repaint();
            map.removeDeadAnimals();
            map.moveRandomAllAnimals();
            map.eating();
            map.nextDay();
            map.copulation();

            map2.removeDeadAnimals();
            map2.moveRandomAllAnimals();
            map2.eating();
            map2.nextDay();
            map2.copulation();

            for (int i = 0; i < grassSpawnedInEachDay / 2; i++) {
                map.spawnGrass();
                map2.spawnGrass();
            }
    }
    private class stopButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }
    }

    private class startButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();
        }
    }

    private class clicker implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            //Click on the animal on the map to get informations about him. If no animal is clicked, the program will choose
            // the closest animal near your clickpoint. Data about animal is shown in console
            int x = e.getX();
            int y = e.getY();
            int g;
            int allps=0;
            int awithdominant=0;
            Vector2d d = renderPanel.getPositionFromMap(new Vector2d(x, y));
            Animal a = (Animal) map.objectAt(d);
            if(a!=null){
                System.out.println("Genotype: " + a.getAnimalGenes().toString());
                System.out.println("Days survived: " + a.getDayssurvived());
                System.out.println("Children of animal: " + a.getChildren().size());
                for(Animal c: a.getChildren()){
                    for(Animal p : c.getChildren()){
                        if(!p.isDead())
                            allps++;
                    }
                }
                System.out.println("Number of kids of children: " + allps);
                g = map.getDominatingGen();
                for(Animal b : map.getAnimals()){
                    if(g==b.getDominantFromGenotype())
                        awithdominant++;
                }
                System.out.println("Number of animals with dominant gene: " + awithdominant);
            }
            else
                System.out.println("No animal has been detected nearby that position");
        }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }

    }
    public JFrame getFrame(){
        return this.frame;
    }
}
