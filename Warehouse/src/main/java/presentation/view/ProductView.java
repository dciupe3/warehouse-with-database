package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * the view for accessing the table of products
 * @author david
 */
public class ProductView extends JFrame {

    private JButton addProductJB = new JButton("add");
    private JButton deleteProductJB = new JButton("delete");
    private JButton updateProductJB = new JButton("update");
    private JButton showAllProductsJB = new JButton("Show all products");

    private JTextField name = new JTextField();
    private JTextField price = new JTextField();
    private JTextField currentStock = new JTextField();
    private JTextField id = new JTextField();

    private JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane();

    private JLabel l1 = new JLabel("name");
    private JLabel l2 = new JLabel("price");
    private JLabel l3 = new JLabel("currentStock");
    private JLabel l4 = new JLabel("id");


    DefaultTableModel tableModel;

    JPanel mainPanel = new JPanel();
    
    public ProductView(){
        setSize(500, 500);

        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new GridLayout(4, 3));
        leftPanel.add(name);
        leftPanel.add(l1);
        leftPanel.add(addProductJB);
        leftPanel.add(price);
        leftPanel.add(l2);
        leftPanel.add(deleteProductJB);
        leftPanel.add(currentStock);
        leftPanel.add(l3);
        leftPanel.add(showAllProductsJB);
        leftPanel.add(id);
        leftPanel.add(l4);
        leftPanel.add(updateProductJB);

        mainPanel.add(leftPanel, BorderLayout.PAGE_START);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void open(){
        this.setTitle("Product");
        this.setVisible(true);
    }
    public void addShowProductsListener(ActionListener listener){
        showAllProductsJB.addActionListener(listener);
    }

    public void addAddProductListener(ActionListener listener){
        addProductJB.addActionListener(listener);
    }

    public void addDeleteProductListener(ActionListener listener){
        deleteProductJB.addActionListener(listener);
    }

    public void addUpdateProductListener(ActionListener listener){
        updateProductJB.addActionListener(listener);
    }

    /**
     * update the table
     * @param colRow columns and rows
     */
    public void updateTable(ArrayList<ArrayList<String>> colRow){
        table.setBackground(Color.CYAN);
        //scrollPane.removeAll();
        tableModel = new DefaultTableModel();
        //tableModel.setColumnIdentifiers(new ArrayList[]{colRow.get(0)});
        //tableModel.addColumn();

        for(String strings : colRow.get(0)){
            tableModel.addColumn(strings);
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
            tableModel.addRow(s);
        }

        table.setModel(tableModel);
        scrollPane.setViewportView(table);
    }

    /**
     * @return get data from JTextFields
     */
    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();

        if(!id.getText().equals(""))
            data.add(id.getText());
        else
            data.add("0");
        if(name.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(1).toString());
        else
            data.add(name.getText());

        if(price.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(2).toString());
        else
            data.add(price.getText());

        if(currentStock.getText().equals(""))
            data.add(tableModel.getDataVector().elementAt(table.getSelectedRow()).get(3).toString());
        else
            data.add(currentStock.getText());

        return data;
    }

    /**
     * @return get data from selected row in table
     */
    public Vector getDataFromTable(){
        return tableModel.getDataVector().elementAt(table.getSelectedRow());
    }
}
