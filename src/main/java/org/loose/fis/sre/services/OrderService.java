package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.model.User;

import org.loose.fis.sre.model.Order;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class OrderService {

    private static ObjectRepository<Order> orderRepository;

    public static void initDatabase() {

       // System.out.println("helloooo");
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("orders.db").toFile())
                .openOrCreate("test", "test");

        orderRepository = database.getRepository(Order.class);
        generateRandomOrders();
        //deleteAllProducts();
    }

    public static List<Order> getOrderList() {
        return orderRepository.find().toList();
    }



    public static void addOrder(String userId, String orderedProducts, float totalPrice) {
        String uuid = UUID.randomUUID().toString();
       // System.out.println("id nou: " + uuid);
        orderRepository.insert(new Order(uuid, userId, orderedProducts, totalPrice));
       // checkProductDoesNotAlreadyExist(uuid);
    }

    public static void generateRandomOrders() {
        List<User> users = UserService.getUsersList();
        for (int i = 0; i < users.size(); i++) {
            addOrder(users.get(i).getUsername(), "1 x ruj, 2 x crema Nivea" , (float) 20);
        }
    }
}
