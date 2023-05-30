package model;

/**
 * Client Class
 * Client is the main entity we'll be using to represent a client
 * @author david
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;


    public Client(){

    }

    public Client(int id, String name, String email, String address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Client(String name, String email, String address){
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

}
