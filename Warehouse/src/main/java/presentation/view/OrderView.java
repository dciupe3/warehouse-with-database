package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * View for accessing the order table
 * @author david
 */
public class OrderView extends JFrame {

    private JButton addOrderJB = new JButton("add");
    private JButton deleteOrderJB = new JButton("delete");
    private JButton updateOrderJB = new JButton("update");
    private JButton showAllClientsJB = new JButton("Show all clients");
    private JButton showAllProductsJB = new JButton("Show all products");
    private JButton showAllOrdersJB = new JButton("Show all orders");
    private JButton b = new JButton("Show all orders");


    private JTextField quantityJTextField = new JTextField();

    private JLabel quantityLabel = new JLabel("Quantity");
    private JLabel labelAiurea = new JLabel();
    private JLabel labelAiurea2 = new JLabel();

    private JTable tableClient = new JTable();
    private JScrollPane scrollPaneClient = new JScrollPane();

    private JTable tableProduct = new JTable();
    private JScrollPane scrollPaneProduct = new JScrollPane();

    private JTable tableOrder = new JTable();
    private JScrollPane scrollPaneOrder = new JScrollPane();

    DefaultTableModel tableModelClient;
    DefaultTableModel tableModelProduct;
    DefaultTableModel tableModelOrder;

    JPanel mainPanel = new JPanel();

    public OrderView(){
        setSize(1000, 1000);

        showAllClientsJB.setBackground(Color.GREEN);
        showAllProductsJB.setBackground(Color.CYAN);
        showAllOrdersJB.setBackground(Color.ORANGE);

        mainPanel.setLayout(new BorderLayout());

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(3, 3));
        upPanel.add(quantityJTextField);
        upPanel.add(quantityLabel);
        upPanel.add(labelAiurea);


        upPanel.add(addOrderJB);
        upPanel.add(labelAiurea2);
        upPanel.add(deleteOrderJB);

        upPanel.add(showAllClientsJB);
        upPanel.add(showAllProductsJB);
        upPanel.add(showAllOrdersJB);


        mainPanel.add(upPanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(1, 2));

        centerPanel.add(scrollPaneClient);
        centerPanel.add(scrollPaneProduct);

        mainPanel.add(centerPanel, BorderLayout.CENTER);


        scrollPaneOrder.setPreferredSize(new Dimension(1000, 450));
        mainPanel.add(scrollPaneOrder, BorderLayout.SOUTH);

        this.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**c
     * @return data from table client
     */
    public Vector getDataFromTableClient(){
        return tableModelClient.getDataVector().elementAt(tableClient.getSelectedRow());
    }

    /**
     * @return data from table product
     */
    public Vector getDataFromTableProduct(){
        return tableModelProduct.getDataVector().elementAt(tableProduct.getSelectedRow());
    }

    /**
     * @return data from table order
     */
    public Vector getDataFromTableOrder(){
        return tableModelOrder.getDataVector().elementAt(tableOrder.getSelectedRow());
    }

    /**
     * @return quantity from JTextField
     */
    public String getQuantity(){
        if(quantityJTextField.getText().equals(""))
            return "1";
        return quantityJTextField.getText();
    }

    /**
     * @return data from product table
     */
    public ArrayList<String> getDataFromProductArrayList(){
        ArrayList<String> data = new ArrayList<>();

        data.add("0");
        data.add(tableModelProduct.getDataVector().elementAt(tableProduct.getSelectedRow()).get(1).toString());
        data.add(tableModelProduct.getDataVector().elementAt(tableProduct.getSelectedRow()).get(2).toString());
        data.add(tableModelProduct.getDataVector().elementAt(tableProduct.getSelectedRow()).get(3).toString());
        return data;
    }

    /**
     * update the table client
     * @param colRow columns and rows
     */
    public void updateTable(ArrayList<ArrayList<String>> colRow){
        tableClient.setBackground(Color.GREEN);
        //scrollPane.removeAll();
        tableModelClient = new DefaultTableModel();
        //tableModel.setColumnIdentifiers(new ArrayList[]{colRow.get(0)});
        //tableModel.addColumn();

        for(String strings : colRow.get(0)){
            tableModelClient.addColumn(strings);
        }
        //System.out.println(colRow.get(0));

        colRow.remove(0);
        for(ArrayList<String> obj : colRow){
            String[] s = new String[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModelClient.addRow(s);
        }

        tableClient.setModel(tableModelClient);
        scrollPaneClient.setViewportView(tableClient);
    }

    /**
     * update the table product
     * @param colRow columns and rows
     */
    public void updateTableProduct(ArrayList<ArrayList<String>> colRow){
        tableProduct.setBackground(Color.CYAN);
        //scrollPane.removeAll();
        tableModelProduct = new DefaultTableModel();
        //tableModel.setColumnIdentifiers(new ArrayList[]{colRow.get(0)});
        //tableModel.addColumn();

        for(String strings : colRow.get(0)){
            tableModelProduct.addColumn(strings);
        }
        System.out.println(colRow.get(0));

        colRow.remove(0);
        for(ArrayList<String> obj : colRow){
            String[] s = new String[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModelProduct.addRow(s);
        }

        tableProduct.setModel(tableModelProduct);
        scrollPaneProduct.setViewportView(tableProduct);
    }

    /**
     * update the table order
     * @param colRow columns and rows
     */
    public void updateTableOrder(ArrayList<ArrayList<String>> colRow) {
        tableOrder.setBackground(Color.ORANGE);
        tableModelOrder = new DefaultTableModel();

        for(String strings : colRow.get(0)){
            tableModelOrder.addColumn(strings);
        }

        colRow.remove(0);
        for(ArrayList<String> obj : colRow){
            String[] s = new String[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModelOrder.addRow(s);
        }

               tableOrder.setModel(tableModelOrder);
        scrollPaneOrder.setViewportView(tableOrder);
    }

    public void addShowClientsListener(ActionListener listener){
        showAllClientsJB.addActionListener(listener);
    }

    public void addShowProductsListener(ActionListener listener){
        showAllProductsJB.addActionListener(listener);
    }

    public void addShowOrdersListener(ActionListener listener){
        showAllOrdersJB.addActionListener(listener);
    }

    public void addAddOrdersListener(ActionListener listener){
        addOrderJB.addActionListener(listener);
    }

    public void addDeleteOrderListener(ActionListener listener) {
        deleteOrderJB.addActionListener(listener);
    }

    public void open(){
        this.setTitle("Order");
        this.setVisible(true);
    }
}
