package service.soap;

import pet.shop.*;
import pet.shop.exceptions.CannotAddProductException;
import pet.shop.exceptions.CannotBuyProductException;
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
    public void addProducts(Product[] products) throws CannotAddProductException{
        petShop.addProducts(products);
    }

    @Override
    public void buyProductById(String id) throws CannotBuyProductException, ProductNotFoundException {
        petShop.buyProductById(id);
    }

    @Override
    public void buyProductByDescription(String description) throws CannotBuyProductException, ProductNotFoundException {
        petShop.buyProductByDescription(description);
    }
}