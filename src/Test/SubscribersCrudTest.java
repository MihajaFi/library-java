package Test;

import Models.Subscribers;
import Repository.SubscribersCrudOperations;

import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudTest {
    public static void SubcribersTest(){
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();
        Subscribers subscriber1 =  new Subscribers("4", "John Doe", "123456");
        Subscribers subscriber2 =  new Subscribers("5", "John Doe", "123456");
        Subscribers subscriber3 =  new Subscribers("6", "John Doe", "123456");
        // Test de findAll
        System.out.println("All Subscribers:");
        subscribersCrudOperations.findAll();



        subscribersCrudOperations.save(subscriber1);
        System.out.println("Saving Subscriber: " + subscriber1);

        // Test de saveAll
        List<Subscribers> subscribersToSave = new ArrayList<>();
        subscribersToSave.add(subscriber2);
        subscribersToSave.add(subscriber3);
        System.out.println("Saving Subscribers:");
        for (Subscribers subscriber : subscribersToSave) {
            System.out.println(subscriber);
        }
        List<Subscribers> savedSubscribers = subscribersCrudOperations.saveAll(subscribersToSave);
        System.out.println("Saved Subscribers:");
        for (Subscribers savedSubscriber : savedSubscribers) {
            System.out.println(savedSubscriber);
        }

        // Test de delete
        Subscribers subscriberToDelete = subscriber1;
        System.out.println("Deleting Subscriber: " + subscriberToDelete);
        Subscribers deletedSubscriber = subscribersCrudOperations.delete(subscriberToDelete);
        System.out.println("Deleted Subscriber: " + deletedSubscriber);
    }
}
