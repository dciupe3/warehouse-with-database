package presentation;

import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import presentation.view.ClientView;
import presentation.view.OrderView;
import presentation.view.ProductView;
import presentation.view.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * controller
 * @author david
 */
public class Controller {

    private StartView startView;
    private ClientView clientView;
    private OrderView orderView;
    private ProductView productView;
    ClientBLL clientBLL;
    ProductBLL productBLL;
    OrderBLL orderBLL;

    public Controller(){
        startView = new StartView();
        clientView = new ClientView();
        orderView = new OrderView();
        productView = new ProductView();

        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();

        startView.open();
        startView.addClientListener(new ClientListener());
        startView.addProductListener(new ProductListener());
        startView.addOrderListener(new OrderListener());

        clientView.addShowClientsListener(new ShowClientsListener());
        clientView.addAddClientListener(new AddClientListener());
        clientView.addDeleteClientListener(new DeleteClientListener());
        clientView.addUpdateClientListener(new UpdateClientListener());

        productView.addAddProductListener(new AddProductListener());
        productView.addShowProductsListener(new ShowProductListener());
        productView.addDeleteProductListener(new DeleteProductListener());
        productView.addUpdateProductListener(new UpdateProductListener());

        orderView.addShowClientsListener(new ShowClientsListenerOrder());
        orderView.addShowProductsListener(new ShowProductListenerOrder());
        orderView.addShowOrdersListener(new ShowOrdersListener());
        orderView.addAddOrdersListener(new AddOrderListener());
        orderView.addDeleteOrderListener(new DeleteOrderListener());

    }

    public Controller(StartView startView, ClientView clientView, OrderView orderView, ProductView productView){
        this.clientView = clientView;
        this.orderView = orderView;
        this.productView = productView;
        this.startView = startView;

        startView.addClientListener(new ClientListener());
        startView.addProductListener(new ProductListener());
        startView.addOrderListener(new OrderListener());
    }

    class ClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                clientView.open();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                productView.open();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                orderView.open();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    //clientView

    class ShowClientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<String>> colRow = clientBLL.getColumnAndRows();
            clientView.updateTable(colRow);
        }
    }

    class AddClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> data = clientView.getData();
            clientBLL.insertClient(data);
        }
    }

    class DeleteClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> vector = clientView.getDataFromTable();
            clientBLL.deleteClient(vector);
        }
    }

    class UpdateClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> dataFromText = clientView.getData();
            Vector<String> data = clientView.getDataFromTable();
            int id = Integer.parseInt(data.get(0));
            clientBLL.updateClient(dataFromText, id);
        }
    }

    //Product view

    class ShowProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<String>> colRow = productBLL.getColumnAndRows();
            productView.updateTable(colRow);
        }
    }

    class AddProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> data = productView.getData();
            productBLL.insertProduct(data);
        }
    }

    class DeleteProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> vector = productView.getDataFromTable();
            productBLL.deleteProduct(vector);
        }
    }

    class UpdateProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> dataFromText = productView.getData();
            Vector<String> data = productView.getDataFromTable();
            int id = Integer.parseInt(data.get(0));
            productBLL.updateProduct(dataFromText, id);
        }
    }

    //Orders

    class ShowClientsListenerOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<String>> colRow = clientBLL.getColumnAndRows();
            orderView.updateTable(colRow);
        }
    }

    class ShowProductListenerOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<String>> colRow = productBLL.getColumnAndRows();
            orderView.updateTableProduct(colRow);
        }
    }

    class ShowOrdersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<String>> colRow = orderBLL.getColumnAndRows();
            orderView.updateTableOrder(colRow);
        }
    }

    class AddOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> clientString = orderView.getDataFromTableClient();
            Vector<String> productString = orderView.getDataFromTableProduct();
            String quantity = orderView.getQuantity();
            ArrayList<String> dataProductArrayString = orderView.getDataFromProductArrayList();

            orderBLL.insertOrder(clientString, productString, quantity, dataProductArrayString);
        }
    }

    class DeleteOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> vector = orderView.getDataFromTableOrder();
            orderBLL.deleteOrder(vector);
        }
    }

}
