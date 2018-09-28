package service.soap;

import pet.shop.Exceptions.ProductNotFoundException;
import pet.shop.Product;
import pet.shop.Shop;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface PetShopSoapServiceInterface {
    @WebMethod
    String welcome();

    @WebMethod
    ArrayList<Product> findProductsByDescription(String description)throws ProductNotFoundException;

    @WebMethod
    Product findProductById(String id)throws ProductNotFoundException;

    @WebMethod
    String addProducts(Product[] products);

    @WebMethod
    String buyProductById(String id);

    @WebMethod
    String buyProductByDescription(String description);

    @WebMethod
    public String printShop();
}
