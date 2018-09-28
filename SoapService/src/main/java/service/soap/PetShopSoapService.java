package service.soap;

import pet.shop.*;
import pet.shop.exceptions.ProductNotFoundException;
import pet.shop.serviceinterface.PetShopInterface;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(endpointInterface = "pet.shop.serviceinterface.PetShopInterface")
public class PetShopSoapService implements PetShopInterface {
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
    public ArrayList<Product> findProductsByDescription(String description) throws ProductNotFoundException {
        ArrayList<Product> products = petShop.findProductsByDescription(description);
        return products;
    }

    @Override
    public Product findProductById(String id) throws ProductNotFoundException {
        Product product = petShop.findProductById(id);
        return product;
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