package pet.shop.serviceinterface;

import pet.shop.Product;
import pet.shop.exceptions.CannotAddProductException;
import pet.shop.exceptions.CannotBuyProductException;
import pet.shop.exceptions.ProductNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface PetShopInterface {
    @WebMethod
    String welcome();

    @WebMethod
    ArrayList<Product> findProductsByDescription(String description)throws ProductNotFoundException;

    @WebMethod
    Product findProductById(String id)throws ProductNotFoundException;

    @WebMethod
    void addProducts(Product[] products)throws CannotAddProductException;

    @WebMethod
    void buyProductById(String id) throws CannotBuyProductException, ProductNotFoundException;

    @WebMethod
    void buyProductByDescription(String description) throws CannotBuyProductException, ProductNotFoundException;

    @WebMethod
    public String printShop();
}




