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
        //deleteAllProducts();
    }

    public static void deleteAllProducts() {
        for (Product product : productRepository.find()) {
           productRepository.remove(product);

        }
    }

    public static void generateRandomProducts() {
        for (int i = 0; i < 5; i++) {
            addProduct("description " + i, (float) i);
        }
    }

    public static void addProduct(String description, float price) {
        String uuid = UUID.randomUUID().toString();
        System.out.println("id nou: " + uuid);
        productRepository.insert(new Product(uuid, description, price));
        checkProductDoesNotAlreadyExist(uuid);
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

    public static void updateProduct(Product updatedProduct) {
        Product aux = null;
        for (Product  product: productRepository.find()) {
            if (updatedProduct.getId().equals(product.getId())) {
                aux = product;
            }
        }

        productRepository.remove(aux);

        productRepository.insert(updatedProduct);
    }

    private static boolean checkProductDoesNotAlreadyExist(String id) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(id, product.getId()))
                System.out.println("id exists: " + id);
            System.out.println(product);

        }

        return false;
    }
}
