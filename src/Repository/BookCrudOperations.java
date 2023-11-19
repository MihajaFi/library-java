package Repository;

import Configuration.ConnectionConfig;
import Models.Book;
import Models.topic_enum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements  CrudOperations<Book> {
    private static Connection connection ;


    public static void getConnection(){
        ConnectionConfig connex = new ConnectionConfig() ;
        connection = connex.createConnection() ;
    }

    private Book extractBookFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String type =  resultSet.getString("type");
        String bookName =  resultSet.getString("bookName");
        int pageNumbers = resultSet.getInt("pageNumbers");
        topic_enum topic = topic_enum.valueOf(resultSet.getString("topic"));
        Date releaseDate =  resultSet.getDate("releaseDate");
        int authorId = resultSet.getInt("authorId") ;
        String status =  resultSet.getString("status");
        return new Book(id ,type ,bookName ,pageNumbers ,topic ,releaseDate ,authorId,status) ;
    }
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>() ;
        String sql = "SELECT * FROM \"book\" " ;
        getConnection() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Book book = extractBookFromResultSet(resultSet) ;
                books.add(book) ;
                System.out.println(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("There was a error while fetching books") ;
        }

        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {

        List<Book> savedBooks = new ArrayList<>();

        try {
            String query = "INSERT INTO \"book\" (id, type, bookName, pageNumbers, topic, releaseDate, authorId,  status) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
            getConnection() ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                for (Book book : toSave) {
                    preparedStatement.setInt(1 , book.getId());
                    preparedStatement.setString(2, book.getType());
                    preparedStatement.setString(3, book.getBookName());
                    preparedStatement.setInt(4, book.getPageNumbers());
                    preparedStatement.setObject(5, book.getTopic(), Types.OTHER);
                    preparedStatement.setDate(6, new java.sql.Date(book.getReleaseDate().getTime()));
                    preparedStatement.setInt(7 , book.getAuthorId());
                    preparedStatement.setString(8, book.getStatus());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        savedBooks.add(book);
                    }
                }
               }
        } catch (SQLException e) {
            throw  new RuntimeException(e) ;
        }

        return savedBooks;
    }

    @Override
    public Book save(Book toSave) {
        try {
            // Établir la connexion (assurez-vous que la connexion est initialisée correctement)

            String query = "INSERT INTO book (type, bookName, pageNumbers, topic, releaseDate, status) VALUES (?, ?, ?, ?, ?, ?) ";
            getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, toSave.getType());
                preparedStatement.setString(2, toSave.getBookName());
                preparedStatement.setInt(3, toSave.getPageNumbers());
                preparedStatement.setObject(4, toSave.getTopic(), Types.OTHER);
                preparedStatement.setDate(5, new java.sql.Date(toSave.getReleaseDate().getTime()));
                preparedStatement.setString(6, toSave.getStatus());
                 preparedStatement.executeUpdate();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
    }

    @Override
    public Book delete(Book toDelete) {

        String sql = "DELETE FROM book WHERE id = ?" ;
        getConnection() ;
        try(PreparedStatement preparedStatement =connection.prepareStatement(sql)){
            preparedStatement.setInt(1 , toDelete.getId());
            preparedStatement.executeUpdate();
            System.out.println("Book deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException("error");
        }
        return null;
    }
}
