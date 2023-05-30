package businessLogic;

import dataAccess.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * ClientBLL is the main entity we'll be using to do operations with a Client
 * @author david
 */
public class ClientBLL {

    /**
     * ClientDAO is used to access the database
     */
    private ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id){
        Client client = clientDAO.findById(id);

        if(client == null) {
            throw new NoSuchElementException("The client with id = " + id + " was not found!");
        }

        return client;
    }

    public List<Client> findAllClients(){
        List<Client> clients = clientDAO.findAll();

        if(clients.isEmpty()){
            throw new NoSuchElementException("The client table is empty");
        }

        return clients;
    }

    public Client insertClient(Client client){
        return clientDAO.insert(client);
    }


    /**
     * @param data is an ArrayList that contains all the attributes of a client
     * @return return a Client
     */
    public Client insertClient(ArrayList<String> data){

        Client client = new Client(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3));
        return clientDAO.insert(client);
    }


    public Client updateClient(Client client, int id){
        return clientDAO.update(client, id);
    }

    /**
     * @param v is an ArrayList that contains all the attributes of a client
     * @param id is the id of a client
     * @return return a Client
     */
    public Client updateClient(ArrayList<String> v, int id) {
        Client client = new Client(Integer.parseInt(v.get(0)), v.get(1), v.get(2), v.get(3));
        return clientDAO.update(client, id);
    }

    public Client deleteClient(Client client){
        return clientDAO.delete(client);
    }

    /**
     * @param v is a Vector that contains all the attributes of a client
     * @return a Client
     */
    public Client deleteClient(Vector<String> v){
        Client client = new Client(Integer.parseInt(v.get(0)), v.get(1), v.get(2), v.get(3));
        return clientDAO.delete(client);
    }

    /**
     * @return the column and the rows of a table in an ArrayList of Strings
     */
    public ArrayList<ArrayList<String>> getColumnAndRows(){
        return clientDAO.getColumnsAndRows();
    }

}
