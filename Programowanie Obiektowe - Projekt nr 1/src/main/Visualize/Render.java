package Visualize;
import MapDetails.Map;
import Objects.Animal;
import Objects.Grass;
import Objects.Vector2d;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel{
    private final Map map;
    private final Simulation simulation;
    private final int which;
    public Render(Map map, Simulation simulation,int which) {
        this.map = map;
        this.simulation = simulation;
        this.which = which;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (this.simulation.getFrame().getWidth() * 0.5), (int) (simulation.getFrame().getHeight() * 0.4));
        if(this.which==0)
            this.setLocation((int) (0.5 * this.simulation.getFrame().getWidth()), 38);
        else
            this.setLocation((int) (0.5 * this.simulation.getFrame().getWidth()), 400);

        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        int widthScale = Math.round(width / this.map.getWidth());
        int heightScale = height / this.map.getHeight();

        g.setColor(new Color(170, 224, 103));
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 160, 7));
        g.fillRect(this.map.getJungleLowerLeft().x * widthScale,
                this.map.getJungleLowerLeft().y * heightScale,
                this.map.getJungleWidth() * widthScale,
                this.map.getJungleHeight() * heightScale);

        for (Grass grass : this.map.getGrass()) {
            g.setColor(grass.toColor());
            int y = this.map.toNoBoundedPosition(grass.getPosition()).y * heightScale;
            int x = this.map.toNoBoundedPosition(grass.getPosition()).x * widthScale;
            g.fillRect(x, y, widthScale, heightScale);
        }
        for (Animal a : map.getAnimals()) {
            g.setColor(a.toColor());
            int y = this.map.toNoBoundedPosition(a.getPosition()).y * heightScale;
            int x = this.map.toNoBoundedPosition(a.getPosition()).x * widthScale;
            g.fill3DRect(x, y, widthScale, heightScale,true);
        }
    }

    public Vector2d getPositionFromMap(Vector2d pos){
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / this.map.getWidth());
        int heightScale = height / this.map.getHeight();
        Vector2d closest;
        if(pos.y<500)
            pos = pos.subtract(new Vector2d((int) (0.5*this.simulation.getFrame().getWidth()),38));
        else{
            pos = pos.subtract(new Vector2d((int) (0.5*this.simulation.getFrame().getWidth()),500));
        }
        double distance;

        distance = this.map.getAnimals().get(0).getPosition().euklidesDistance(pos);
        closest = this.map.getAnimals().get(0).getPosition();

        Vector2d current;
        for(Animal a: this.map.getAnimals()){
            current = this.map.toNoBoundedPosition(a.getPosition());
            current = new Vector2d((current.x*widthScale),(current.y * heightScale));
            if(current.euklidesDistance(pos) < distance){
                closest = a.getPosition();
                distance = current.euklidesDistance(pos);
            }
        }
        return closest;
    }
}
