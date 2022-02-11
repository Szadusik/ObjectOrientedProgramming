package Menu;

import javax.swing.*;

public class Settings {
    public JFrame menuFrame;
    public Settings() {
        menuFrame = new JFrame("Settings");
        menuFrame.setSize(500, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
    }

    public void startSimulation(Integer[] defaultMapProperties){
        menuFrame.add(new Panel(defaultMapProperties));
        menuFrame.setVisible(true);
    }
}
