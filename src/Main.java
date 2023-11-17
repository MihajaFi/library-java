import Models.Author;
import Models.Book;
import Models.topic_enum;
import Repository.AuthorCrudOperations;
import Repository.BookCrudOperations;
import Test.AuthorCrudTest;
import Test.BookCrudtTest;
import Test.SubscribersCrudTest;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        AuthorCrudTest.AuthorTest();
        SubscribersCrudTest.SubcribersTest();
        BookCrudtTest.BookTest();
    }


}