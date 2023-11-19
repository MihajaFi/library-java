package Repository;

import Configuration.ConnectionConfig;
import Models.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {
    private static Connection connection ;


    public static void getConnection(){
        ConnectionConfig connex = new ConnectionConfig() ;
        connection = connex.createConnection() ;
    }
    private Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String authorName =  resultSet.getString("authorName");
        String sex =  resultSet.getString("sex");

        return new Author(id ,authorName , sex) ;
    }
    @Override
    public List<Author> findAll() {

        List<Author> authors = new ArrayList<>() ;
        String sql = "SELECT * FROM author " ;
        getConnection() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Author author = extractAuthorFromResultSet(resultSet) ;
                authors.add(author) ;
                System.out.println(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("There was a error while fetching books") ;
        }

        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        List<Author> savedAuthors = new ArrayList<>();

        try {
            String query = "INSERT INTO author (id, authorName, sex) VALUES (?,?, ?)";
            getConnection() ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                for (Author author : toSave) {
                    preparedStatement.setInt(1 , author.getId());
                    preparedStatement.setString(2, author.getAuthorName());
                    preparedStatement.setString(3, author.getSex());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        savedAuthors.add(author);
                    }
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }

        return savedAuthors;
    }

    @Override
    public Author save(Author toSave) {
        try {
            String query = "INSERT INTO author (id, authorName, sex) VALUES (?,?, ?)";
            getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1 , toSave.getId());
                preparedStatement.setString(2, toSave.getAuthorName());
                preparedStatement.setString(3, toSave.getSex());
                preparedStatement.executeUpdate();
                System.out.println("succesfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }

    @Override
    public Author delete(Author toDelete) {
        String sql = "DELETE FROM author WHERE id = ?" ;
        getConnection() ;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setInt(1 , toDelete.getId());
            preparedStatement.executeUpdate();
            System.out.println("Author deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException("error");
        }
        return null;
    }
}
