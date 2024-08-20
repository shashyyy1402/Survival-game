import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisasterManagementGUI {
    
    private JFrame frame;
    private JLabel dayLabel, populationLabel, foodLabel, waterLabel, medicalLabel, disasterLabel;
    private JButton foodButton, waterButton, medicalButton;
    private int foodSupply = 100, waterSupply = 100, medicalKits = 50, population = 1000, day = 1;
    
    public DisasterManagementGUI() {
        frame = new JFrame("Disaster Management Simulation");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        dayLabel = new JLabel("Day: " + day);
        dayLabel.setBounds(20, 20, 100, 20);
        frame.add(dayLabel);
        
        populationLabel = new JLabel("Population: " + population);
        populationLabel.setBounds(20, 50, 150, 20);
        frame.add(populationLabel);
        
        foodLabel = new JLabel("Food Supply: " + foodSupply);
        foodLabel.setBounds(20, 80, 150, 20);
        frame.add(foodLabel);
        
        waterLabel = new JLabel("Water Supply: " + waterSupply);
        waterLabel.setBounds(20, 110, 150, 20);
        frame.add(waterLabel);
        
        medicalLabel = new JLabel("Medical Kits: " + medicalKits);
        medicalLabel.setBounds(20, 140, 150, 20);
        frame.add(medicalLabel);
        
        disasterLabel = new JLabel("Disaster: None");
        disasterLabel.setBounds(20, 170, 200, 20);
        frame.add(disasterLabel);
        
        foodButton = new JButton("Use Food");
        foodButton.setBounds(20, 200, 100, 30);
        foodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleResponse("food");
            }
        });
        frame.add(foodButton);
        
        waterButton = new JButton("Use Water");
        waterButton.setBounds(130, 200, 100, 30);
        waterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleResponse("water");
            }
        });
        frame.add(waterButton);
        
        medicalButton = new JButton("Use Medical Kits");
        medicalButton.setBounds(240, 200, 120, 30);
        medicalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleResponse("medical");
            }
        });
        frame.add(medicalButton);
        
        frame.setVisible(true);
    }
    
    private void handleResponse(String response) {
        String disaster = getRandomDisaster();
        disasterLabel.setText("Disaster: " + disaster);
        
        if (response.equals("food")) {
            foodSupply -= 20;
            population -= calculatePopulationLoss(disaster, "food");
        } else if (response.equals("water")) {
            waterSupply -= 20;
            population -= calculatePopulationLoss(disaster, "water");
        } else if (response.equals("medical")) {
            medicalKits -= 10;
            population -= calculatePopulationLoss(disaster, "medical");
        }

        if (foodSupply <= 0 || waterSupply <= 0 || medicalKits <= 0 || population <= 0) {
            JOptionPane.showMessageDialog(frame, "Game Over! Resources depleted or population perished.");
            System.exit(0);
        }
        
        day++;
        updateLabels();
    }
    
    private void updateLabels() {
        dayLabel.setText("Day: " + day);
        populationLabel.setText("Population: " + population);
        foodLabel.setText("Food Supply: " + foodSupply);
        waterLabel.setText("Water Supply: " + waterSupply);
        medicalLabel.setText("Medical Kits: " + medicalKits);
    }

    private String getRandomDisaster() {
        String[] disasters = {"Earthquake", "Flood", "Pandemic", "Fire"};
        int index = (int) (Math.random() * disasters.length);
        return disasters[index];
    }

    private int calculatePopulationLoss(String disaster, String response) {
        int loss = 0;
        switch (disaster) {
            case "Earthquake":
                loss = response.equals("medical") ? 50 : 100;
                break;
            case "Flood":
                loss = response.equals("food") ? 30 : 70;
                break;
            case "Pandemic":
                loss = response.equals("medical") ? 20 : 80;
                break;
            case "Fire":
                loss = response.equals("water") ? 40 : 90;
                break;
        }
        return loss;
    }
    
    public static void main(String[] args) {
        new DisasterManagementGUI();
    }
}
