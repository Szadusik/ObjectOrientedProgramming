package Menu;

import MapDetails.Map;
import Visualize.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    //
    private static final int HEIGHT = 600;
    private static final int WIDTH = 600;
    //Fields for data entry
    private final JTextField delay;
    private final JTextField animalsStartEnergy;
    private final JTextField numOfSpawnedAnimals;
    private final JTextField grassEatingEnergyProfit;
    private final JTextField mapWidth;
    private final JTextField mapHeight;
    private final JTextField jungleWidth;
    private final JTextField jungleHeight;
    private final JTextField dayEnergyCost;
    private final JTextField grassSpawnedInEachDay;
    private final JTextField daysToSaveFile;

    public Panel(Integer[] defaultMapProperties) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //button
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);

        //LABELS
        //Labels to identify the fields
        JLabel delayLabel = new JLabel("Real refresh time (ms):           ");
        JLabel animalsStartEnergyLabel = new JLabel("Animal start energy:              ");
        JLabel numOfSpawnedAnimalsLabel = new JLabel("Animals spawning at the start:    ");
        JLabel grassEatingEnergyProfitLabel = new JLabel("Grass energy profit:              ");
        JLabel mapHeightLabel = new JLabel("Map height:                       ");
        JLabel mapWidthLabel = new JLabel("Map width:                        ");
        JLabel jungleWidthLabel = new JLabel("Jungle width:                     ");
        JLabel jungleHeightLabel = new JLabel("Jungle height:                    ");
        JLabel dayEnergyCostLabel = new JLabel("Daily energy cost:                ");
        JLabel grassSpawnedInEachDayLabel = new JLabel("Grass spawned in each day         ");
        JLabel daysToSaveFileLabel = new JLabel("Day to save statistics         ");
        //TEXT FIELDS
        int a = 11;
        delay = new JTextField();
        delay.setColumns(a);
        delay.setText(defaultMapProperties[8].toString());

        animalsStartEnergy = new JTextField();
        animalsStartEnergy.setColumns(a);
        animalsStartEnergy.setText(defaultMapProperties[6].toString());

        numOfSpawnedAnimals = new JTextField();
        numOfSpawnedAnimals.setColumns(a);
        numOfSpawnedAnimals.setText(defaultMapProperties[7].toString());

        grassEatingEnergyProfit = new JTextField();
        grassEatingEnergyProfit.setColumns(a);
        grassEatingEnergyProfit.setText(defaultMapProperties[4].toString());

        mapHeight = new JTextField();
        mapHeight.setColumns(a);
        mapHeight.setText(defaultMapProperties[1].toString());

        mapWidth = new JTextField();
        mapWidth.setColumns(a);
        mapWidth.setText(defaultMapProperties[0].toString());

        jungleWidth = new JTextField();
        jungleWidth.setColumns(a);
        jungleWidth.setText(defaultMapProperties[2].toString());

        jungleHeight = new JTextField();
        jungleHeight.setColumns(a);
        jungleHeight.setText(defaultMapProperties[3].toString());

        dayEnergyCost = new JTextField();
        dayEnergyCost.setColumns(a);
        dayEnergyCost.setText(defaultMapProperties[5].toString());

        grassSpawnedInEachDay = new JTextField();
        grassSpawnedInEachDay.setColumns(a);
        grassSpawnedInEachDay.setText(defaultMapProperties[9].toString());

        daysToSaveFile = new JTextField();
        daysToSaveFile.setColumns(a);
        daysToSaveFile.setText(defaultMapProperties[10].toString());

        //Labels to text fields
        delayLabel.setLabelFor(delay);
        animalsStartEnergyLabel.setLabelFor(animalsStartEnergy);
        numOfSpawnedAnimalsLabel.setLabelFor(numOfSpawnedAnimals);
        grassEatingEnergyProfitLabel.setLabelFor(grassEatingEnergyProfit);
        mapHeightLabel.setLabelFor(mapHeight);
        mapWidthLabel.setLabelFor(mapWidth);
        jungleWidthLabel.setLabelFor(jungleWidth);
        jungleHeightLabel.setLabelFor(jungleHeight);
        dayEnergyCostLabel.setLabelFor(dayEnergyCost);
        grassSpawnedInEachDayLabel.setLabelFor(grassSpawnedInEachDay);
        daysToSaveFileLabel.setLabelFor(daysToSaveFile);

        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();
        JPanel l7 = new JPanel();
        JPanel l8 = new JPanel();
        JPanel l9 = new JPanel();
        JPanel l10 = new JPanel();
        JPanel l11 = new JPanel();
        JPanel l12 = new JPanel();


        l1.add(delayLabel);
        l2.add(animalsStartEnergyLabel);
        l4.add(numOfSpawnedAnimalsLabel);
        l5.add(grassEatingEnergyProfitLabel);
        l6.add(mapHeightLabel);
        l7.add(mapWidthLabel);
        l8.add(jungleWidthLabel);
        l9.add(jungleHeightLabel);
        l10.add(dayEnergyCostLabel);
        l11.add(grassSpawnedInEachDayLabel);
        l12.add(daysToSaveFileLabel);

        l1.add(delay);
        l2.add(animalsStartEnergy);
        l4.add(numOfSpawnedAnimals);
        l5.add(grassEatingEnergyProfit);
        l6.add(mapHeight);
        l7.add(mapWidth);
        l8.add(jungleWidth);
        l9.add(jungleHeight);
        l10.add(dayEnergyCost);
        l11.add(grassSpawnedInEachDay);
        l12.add(daysToSaveFile);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(new JLabel("Map properties"));
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(new JLabel("Energy properties"));
        add(l5);
        add(l2);
        add(l10);

        add(new JLabel("Spawning properties"));
        add(l4);
        add(l11);
        add(new JLabel("Others"));
        add(l1);
        add(l12);
        add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map map = new Map(
                Integer.parseInt(mapWidth.getText()),
                Integer.parseInt(mapHeight.getText()),
                Integer.parseInt(jungleWidth.getText()),
                Integer.parseInt(jungleHeight.getText()),
                Integer.parseInt(grassEatingEnergyProfit.getText()),
                Integer.parseInt(dayEnergyCost.getText()),
                Integer.parseInt(animalsStartEnergy.getText())
        );
        Map map2 = new Map(
                Integer.parseInt(mapWidth.getText()),
                Integer.parseInt(mapHeight.getText()),
                Integer.parseInt(jungleWidth.getText()),
                Integer.parseInt(jungleHeight.getText()),
                Integer.parseInt(grassEatingEnergyProfit.getText()),
                Integer.parseInt(dayEnergyCost.getText()),
                Integer.parseInt(animalsStartEnergy.getText())
        );
        Simulation simulation = new Simulation(
                map,map2, Integer.parseInt(delay.getText()),
                Integer.parseInt(numOfSpawnedAnimals.getText()),
                Integer.parseInt(grassSpawnedInEachDay.getText()),
                Integer.parseInt(daysToSaveFile.getText()));
        simulation.startSimulation();
    }
}
