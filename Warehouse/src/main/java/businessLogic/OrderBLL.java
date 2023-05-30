package businessLogic;

import dataAccess.OrderDAO;
import model.Product;
import model.ProductOrder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

/**
 * OrderBLL is the main entity we'll be using to do operations with an Order
 * @author david
 */
public class OrderBLL {


    public File file = new File("file.txt");
    public PrintWriter writer = null;
    int nr;

    /**
     * OrderDAO is used to access the database in the table Order
     */
    private OrderDAO orderDAO;
    private ProductBLL productBLL = new ProductBLL();

    public OrderBLL() {
        orderDAO = new OrderDAO();
        nr = 0;
    }

    /**
     * @return the column and the rows of a table in an ArrayList of Strings
     */
    public ArrayList<ArrayList<String>> getColumnAndRows(){
        return orderDAO.getColumnsAndRows();
    }

    /**
     * @param clientString is a Vector that contains all the attributes of a client
     * @param productString is a Vector that contains all the attributes of a product
     * @param quantityString is a String that contains the quantity
     * @param dataProductArray is an ArrayList that contains all the attributes of a product
     */
    public void insertOrder(Vector<String> clientString, Vector<String> productString, String quantityString, ArrayList<String> dataProductArray){
        ProductOrder order = new ProductOrder(0, Integer.parseInt(clientString.get(0)), Integer.parseInt(productString.get(0)), Integer.parseInt(quantityString));

        if(Integer.valueOf(productString.get(3)) < Integer.parseInt(quantityString)){
            System.out.println("Cantitate prea mare");
            JOptionPane.showMessageDialog(null, "Cantitate prea mare");
        }
        else{
            orderDAO.insert(order);

            openFile();
            writer.println(clientString.get(1) + " ordered " + quantityString + " " + dataProductArray.get(1));
            System.out.println(clientString.get(1) + " ordered " + quantityString + " " + dataProductArray.get(1));

            Integer modifiedQuantity = Integer.parseInt(dataProductArray.get(3)) - Integer.parseInt(quantityString);
            dataProductArray.set(3, modifiedQuantity.toString());
            System.out.println(dataProductArray);
            productBLL.updateProduct(dataProductArray, Integer.parseInt(productString.get(0)));

            writer.close();
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param v is a Vector that contains all the attributes of a client
     * @return a ProductOrder
     */
    public ProductOrder deleteOrder(Vector<String> v){
        ProductOrder order = new ProductOrder(Integer.parseInt(v.get(0)), Integer.parseInt(v.get(1)), Integer.parseInt(v.get(2)), Integer.parseInt(v.get(3)));
        return orderDAO.delete(order);
    }

    public void openFile(){
        try {
            nr++;
            file = new File("file" + nr + ".txt");
            System.out.println(file.getName());
            writer = new PrintWriter(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
