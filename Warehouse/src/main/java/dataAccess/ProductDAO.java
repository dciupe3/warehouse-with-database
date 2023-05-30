package dataAccess;

import model.Client;
import model.Product;
import presentation.view.ProductView;

import java.util.ArrayList;
import java.util.List;

/**
 * data access from table Product
 * @author david
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * @param product product
     * @return insert product in table
     */
    public Product insert(Product product){
        Product insertedProduct = product;

        super.insert(product);

        return insertedProduct;
    }

    /**
     * @return columns and rows of table
     */
    public ArrayList<ArrayList<String>> getColumnsAndRows(){
        List<Product> products = super.findAll();
        return super.getTableColumnAndRows(products);
    }

    /**
     * @param product product
     * @return delete a product from table
     */
    public Product delete(Product product){
        super.delete(product);

        return product;
    }

    /**
     * @param product product
     * @param id      id
     * @return update product from table
     */
    public Product update(Product product, int id){
        Product updatedProduct = product;

        super.update(product, id);

        return updatedProduct;
    }


}
