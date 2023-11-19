package Repository;

import Configuration.ConnectionConfig;
import Models.Subscribers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements  CrudOperations<Subscribers> {
    private static Connection connection ;


    public static void getConnection(){
        ConnectionConfig connex = new ConnectionConfig() ;
        connection = connex.createConnection() ;
    }

    private Subscribers extractSubscribersFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name =  resultSet.getString("name");
        String reference =  resultSet.getString("reference");

        return new Subscribers(id ,name , reference) ;
    }

    @Override
    public List<Subscribers> findAll() {

        List<Subscribers> subscribers = new ArrayList<>() ;
        String sql = "SELECT * FROM subscribers " ;
        getConnection() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Subscribers subscriber = extractSubscribersFromResultSet(resultSet) ;
                subscribers.add(subscriber) ;
                System.out.println(subscriber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("There was a error while fetching books") ;
        }

        return subscribers;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {

        List<Subscribers> savedSubscribers = new ArrayList<>();

        try {
            String query = "INSERT INTO subscribers (id, name, reference) VALUES (?,?, ?)";
            getConnection() ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                for (Subscribers subscriber : toSave) {
                    preparedStatement.setString(1 ,subscriber.getId());
                    preparedStatement.setString(2, subscriber.getName());
                    preparedStatement.setString(3, subscriber.getReference());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        savedSubscribers.add(subscriber);
                    }
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }

        return savedSubscribers;

    }

    @Override
    public Subscribers save(Subscribers toSave) {

        try {
            String query = "INSERT INTO subscribers (id, name, reference) VALUES (?,?, ?)";
            getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1 ,toSave.getId());
                preparedStatement.setString(2, toSave.getName());
                preparedStatement.setString(3, toSave.getReference());

                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Subscribers delete(Subscribers toDelete) {

        String sql = "DELETE FROM subscribers WHERE id = ?" ;
        getConnection() ;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setString(1 , toDelete.getId());
            preparedStatement.executeUpdate();
            System.out.println("Subscriber deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error while deleting");
        }
        return null;
    }
}
