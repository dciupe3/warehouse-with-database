package dataAccess;

import connection.ConnectionFactory;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T> A universal type of class
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * the type of the current Class
     */
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @param field the field that we want to replace in the query
     * @return a select query String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @param t a T object
     * @return insert query String
     */
    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");
        int nr = 1;
        for (Field f : type.getDeclaredFields()){
            sb.append(f.getName());
            if(type.getDeclaredFields().length > nr){
                sb.append(",");
            }
            nr++;
        }
        sb.append(")");
        sb.append(" VALUES (");
        nr = 1;
        try {
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                if(f.getType().toString().equals("class java.lang.String")){
                    sb.append("'" + f.get(t) + "'");
                }
                else{
                    sb.append(f.get(t));
                }
                if(type.getDeclaredFields().length > nr) {
                    sb.append(",");
                }
                nr++;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append(")");

        System.out.println(sb.toString() + "\n\n");
        return sb.toString();
    }

    /**
     * @param t a T object
     * @param ID id
     * @return update query String
     */
    private String createUpdateQuery(T t, int ID){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName() + " SET ");

        int id = 0;
        int nr = 2;
        try {
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                if(f.getName().equals("id")){
                    id = (int) f.get(t);
                }else {
                    sb.append(f.getName() + " = ");
                    if (f.getType().toString().equals("class java.lang.String")) {
                        sb.append("'" + f.get(t) + "'");
                    } else {
                        sb.append(f.get(t));
                    }
/*                    if (f.getName().equals("id")) {
                        id = (int) f.get(t);
                    }*/
                    if (type.getDeclaredFields().length > nr) {
                        sb.append(",");
                    }
                    nr++;
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sb.append(" WHERE id = ");

        sb.append(ID);

        System.out.println(sb.toString() + "\n");
        return sb.toString();

    }

    /**
     * @param t a T object
     * @return delete query String
     */
    private String createDeleteQuery(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName() + " WHERE ");

        int id = 0;
        try {
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                if(f.getName().equals("id")){
                    id = (int) f.get(t);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sb.append(" id = " + id);

        System.out.println(sb.toString() + "\n");
        return sb.toString();
    }

    /**
     * @param t T object
     * @return the columns and rows of a table in an ArrayList of Strings
     */
    public ArrayList<ArrayList<String>> getTableColumnAndRows(List <T> t) {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<String> header = new ArrayList<String>();
        for(Field field : t.get(0).getClass().getDeclaredFields()){
            String s = new String(field.getName());
            header.add(s);
        }

        list.add(header);

        for(T obj : t){
            ArrayList<String> row = new ArrayList<String>();
            for(Field field : obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    row.add(value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(row);
        }

        return list;
    }

    /**
     * @return all the objects from a table
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * @param id id of an Object
     * @return the object with that id
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @param resultSet resultSet from a query
     * @return list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param t T object
     * @return insert an object in the table
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * @param t T object
     * @param id id
     * @return update an object in the table by id
     */
    public T update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t, id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    /**
     * @param t T object
     * @return delete t object from table
     */
    public T delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Stergeti prima data comanda in care este implicat");
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }
}
