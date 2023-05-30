package model;

/**
 *  Product is the main entity we'll be using to represent a product
 * @author david
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int currentStock;

    public Product(){

    }

    public Product(int id, String name, double price, int currentStock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.currentStock = currentStock;
    }

    public Product(String name, double price, int currentStock){
        this.name = name;
        this.price = price;
        this.currentStock = currentStock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getCurrentStock(){
        return currentStock;
    }

}
