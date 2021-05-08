package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.ProductAlreadyExistsException;
import org.loose.fis.sre.model.Product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ProductService {

    private static ObjectRepository<Product> productRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("products.db").toFile())
                .openOrCreate("test", "test");

        productRepository = database.getRepository(Product.class);
    }

    public static void addProduct(String description, float price) throws ProductAlreadyExistsException {
        checkProductDoesNotAlreadyExist(description);
        productRepository.insert(new Product(description, price));
    }

    public static List<Product> getProductList() {
        return productRepository.find().toList();
    }

    public static void deleteProduct(Product product) {
        productRepository.remove(product);
    }

    public static void updateProduct(Product product) {
        productRepository.update(product);
    }

    private static void checkProductDoesNotAlreadyExist(String description) throws ProductAlreadyExistsException {
        for (Product product : productRepository.find()) {
            if (Objects.equals(description, product.getDescription()))
                throw new ProductAlreadyExistsException(description);
        }
    }
}
