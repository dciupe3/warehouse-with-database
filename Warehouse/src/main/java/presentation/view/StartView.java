package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * first view
 * @author david
 */
public class StartView extends JFrame {
    private JButton clientJB = new JButton("Client");
    private JButton productJB = new JButton("Product");
    private JButton orderJB = new JButton("Order");
    private JPanel mainPanel = new JPanel();

    public StartView(){

        setSize(500, 300);

        mainPanel.setLayout(null);

        clientJB.setBounds(20,80, 130, 80);
        orderJB.setBounds(170,80, 130, 80);
        productJB.setBounds(320,80, 130, 80);


        mainPanel.add(clientJB);
        mainPanel.add(productJB);
        mainPanel.add(orderJB);
        this.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void open(){
        this.setTitle("Warehouse");
        this.setVisible(true);
    }

    public void addClientListener(ActionListener listener){
        clientJB.addActionListener(listener);
    }

    public void addProductListener(ActionListener listener){
        productJB.addActionListener(listener);
    }

    public void addOrderListener(ActionListener listener){
        orderJB.addActionListener(listener);
    }
}
