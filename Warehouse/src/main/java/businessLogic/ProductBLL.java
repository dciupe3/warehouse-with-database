package businessLogic;

import dataAccess.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.Vector;

/**
 * ProductBLL is the main entity we'll be using to do operations with a product
 * @author david
 */
public class ProductBLL {


    /**
     *  productDAO is used to access the database in the table Product
     */
    private ProductDAO productDAO;

    public ProductBLL(){
        productDAO = new ProductDAO();
    }

    /**
     * @param data is an ArrayList that contains all the attributes of a product
     * @return insert a Product in db
     */
    public Product insertProduct(ArrayList<String> data){
        Product product = new Product(Integer.parseInt(data.get(0)), data.get(1), Double.parseDouble(data.get(2)), Integer.parseInt(data.get(3)));
        return productDAO.insert(product);
    }

    /**
     * @param v is a Vector that contains all the attributes of a product
     * @return delete a Product from a table and return a Product
     */
    public Product deleteProduct(Vector<String> v){
        Product product = new Product(Integer.parseInt(v.get(0)), v.get(1), Double.parseDouble(v.get(2)), Integer.parseInt(v.get(3)));
        return productDAO.delete(product);
    }

    /**
     * @param v is a Vector that contains all the attributes of a product
     * @param id id of a product
     * @return update a product from a table and return a Product
     */
    public Product updateProduct(ArrayList<String> v, int id) {
        Product product = new Product(Integer.parseInt(v.get(0)), v.get(1), Double.parseDouble(v.get(2)), Integer.parseInt(v.get(3)));
        return productDAO.update(product, id);
    }

    /**
     * @return return the columns and rows of a table
     */
    public ArrayList<ArrayList<String>> getColumnAndRows(){
        return productDAO.getColumnsAndRows();
    }

}
