package dataAccess;

import model.ProductOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * database access from table Order
 * @author david
 */
public class OrderDAO extends AbstractDAO<ProductOrder>{

    /**
     * @return columns and rows of a table
     */
    public ArrayList<ArrayList<String>> getColumnsAndRows(){
        List<ProductOrder> orders = super.findAll();
        return super.getTableColumnAndRows(orders);
    }

    public ProductOrder insert(ProductOrder order){
        ProductOrder orderToInsert = order;

        super.insert(orderToInsert);

        return orderToInsert;
    }

}
