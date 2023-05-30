package model;

/**
 * ProductOrder is the main entity we'll be using to represent an order
 * @author david
 */
public class ProductOrder {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    //private String clientName;
    //private String productName;

    public ProductOrder(){

    }

    public ProductOrder(int id, int idClient, int idProduct, int quantity){
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public ProductOrder(int idClient, int idProduct, int quantity){
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient(){
        return idClient;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProduct(){
        return idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

/*    public void setClientName(String clientName) {
        this.clientName = clientName;
    }*/

/*    public void setProductName(String productName) {
        this.productName = productName;
    }*/

/*    public String getClientName(){
        return clientName;
    }*/

/*    public String getProductName(){
        return productName;
    }*/
}
