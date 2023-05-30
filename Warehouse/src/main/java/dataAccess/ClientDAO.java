package dataAccess;

import model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Client database access
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * @param clientId id
     * @return find client by id
     */
    public Client findById(int clientId){
        Client toReturn;

        toReturn = super.findById(clientId);

        return toReturn;
    }

    /**
     * @return all the clients from table
     */
    public List<Client> findAll(){
        return super.findAll();
    }

    /**
     * @param client Client
     * @return insert a client in table
     */
    public Client insert(Client client){
        Client insertedClient = client;

        super.insert(client);

        return insertedClient;
    }

    /**
     * @param client Client
     * @param id id
     * @return update a client by id
     */
    public Client update(Client client, int id){
        Client updatedClient = client;

        super.update(client, id);

        return updatedClient;
    }

    /**
     * @return columns and rows of a table
     */
    public ArrayList<ArrayList<String>> getColumnsAndRows(){
        List<Client> clients = super.findAll();
        return super.getTableColumnAndRows(clients);
    }

}
