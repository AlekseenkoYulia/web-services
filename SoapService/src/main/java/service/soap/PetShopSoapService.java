package service.soap;

import pet.shop.Exceptions.ProductNotFoundException;
import pet.shop.Product;
import pet.shop.Shop;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(endpointInterface = "service.soap.PetShopSoapServiceInterface")
public class PetShopSoapService implements PetShopSoapServiceInterface {
    static Shop petShop = new Shop();

    @Override
    public String welcome() {
        return "Welcome to Pet Shop!";
    }

    @Override
    public String printShop() {
        return petShop.toString();
    }

    @Override
    public String findProductsByDescription(String description) {
        try {
            ArrayList<Product> products = petShop.findProductsByDescription(description);
            return products.toString();
        } catch (ProductNotFoundException e) {
            return "Products not found";
        }
    }

    @Override
    public String findProductById(String id) {
        try {
            Product product = petShop.findProductById(id);
            return product.toString();
        } catch (ProductNotFoundException e) {
            return "Product not found";
        }
    }

    @Override
    public String addProducts(Product[] products) {
        StringBuilder responseMsg = new StringBuilder();
        for (Product p : products) {
            String status = petShop.addProduct(p);
            if (status.equals("Add product: success!")) {
                responseMsg.append("Add product: " + p + " - Success!" + "\n");
            } else {
                responseMsg.append("Can't add product: " + p + " - " + status + "\n");
            }
        }
        return responseMsg.append("Operation complete.").toString();
    }

    @Override
    public String buyProductById(String id) {
        String status = petShop.buyProductById(id);
        return status;
    }

    @Override
    public String buyProductByDescription(String description) {
        String status = petShop.buyProductById(description);
        return status;
    }
}