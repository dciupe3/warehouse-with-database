package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The view for accessing the client table
 * @author david
 */
public class ClientView extends JFrame {

    private JButton addClientJB = new JButton("add");
    private JButton deleteClientJB = new JButton("delete");
    private JButton updateClientJB = new JButton("update");
    private JButton showAllClientsJB = new JButton("Show all clients");

    private JTextField name = new JTextField();
    private JTextField address = new JTextField();
    private JTextField email = new JTextField();
    private JTextField id = new JTextField();

    private JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane();

    private JLabel l1 = new JLabel("name");
    private JLabel l2 = new JLabel("addres");
    private JLabel l3 = new JLabel("email");
    private JLabel l4 = new JLabel("id");


    DefaultTableModel tableModel;

    JPanel mainPanel = new JPanel();


    public ClientView(){
        this.setSize(500, 500);

        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        addClientJB.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.setLayout(new GridLayout(4, 3));
        leftPanel.add(name);
        leftPanel.add(l1);
        leftPanel.add(addClientJB);
        leftPanel.add(address);
        leftPanel.add(l2);
        leftPanel.add(deleteClientJB);
        leftPanel.add(email);
        leftPanel.add(l3);
        leftPanel.add(showAllClientsJB);
        leftPanel.add(id);
        leftPanel.add(l4);
        leftPanel.add(updateClientJB);

        mainPanel.add(leftPanel, BorderLayout.PAGE_START);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addShowClientsListener(ActionListener listener){
        showAllClientsJB.addActionListener(listener);
    }

    public void addAddClientListener(ActionListener listener){
        addClientJB.addActionListener(listener);
    }

    public void addDeleteClientListener(ActionListener listener){
        deleteClientJB.addActionListener(listener);
    }

    public void addUpdateClientListener(ActionListener listener){
        updateClientJB.addActionListener(listener);
    }

    public void open(){
        this.setTitle("Client");
        this.setVisible(true);
    }

    /**
     * @param colRow columns and rows of a table
     * update the table
     */
    public void updateTable(ArrayList<ArrayList<String>> colRow){
        table.setBackground(Color.GREEN);
        //scrollPane.removeAll();
        tableModel = new DefaultTableModel();
        //tableModel.setColumnIdentifiers(new ArrayList[]{colRow.get(0)});
        //tableModel.addColumn();

        for(String strings : colRow.get(0)){
            tableModel.addColumn(strings);
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
            tableModel.addRow(s);
        }

        table.setModel(tableModel);
        scrollPane.setViewportView(table);
    }

    /**
     * @return get the data from the JTextFields
     */
    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();

        if(!id.getText().equals(""))
            data.add(id.getText());
        else
            data.add(new String("0"));

        if(name.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(1).toString());
        else
            data.add(name.getText());

        if(email.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(2).toString());
        else
            data.add(email.getText());

        if(address.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(3).toString());
        else
            data.add(address.getText());

        return data;
    }

    /**
     * @return get the data from the table
     */
    public Vector getDataFromTable(){
        return tableModel.getDataVector().elementAt(table.getSelectedRow());
    }
}
