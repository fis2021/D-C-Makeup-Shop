package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.ProductAlreadyExistsException;
import org.loose.fis.sre.model.Product;
import java.util.List;
import java.util.Objects;

import java.util.UUID;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ProductService {

    private static ObjectRepository<Product> productRepository;

    public static void initDatabase() {

        System.out.println("helloooo");
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("products.db").toFile())
                .openOrCreate("test", "test");

        productRepository = database.getRepository(Product.class);
    }

    public static void addProduct(String description, float price) {
        String uuid = UUID.randomUUID().toString();
        while (checkProductDoesNotAlreadyExist(uuid) == true) {

        }
        productRepository.insert(new Product(uuid, description, price));
    }

    public static List<Product> getProductList() {
        return productRepository.find().toList();
    }

    public static void deleteProduct(Product product) {
        productRepository.remove(product);
    }

    public static void deleteProduct(String id) {
        Product aux = null;
        for (Product product : productRepository.find()) {
            if (id.equals(product.getId())) {
                aux = product;
            }
        }

        productRepository.remove(aux);
    }

    public static void updateProduct(Product product) {
        productRepository.update(product);
    }

    private static boolean checkProductDoesNotAlreadyExist(String id) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(id, product.getId()))
               return true;
        }

        return false;
    }
}
