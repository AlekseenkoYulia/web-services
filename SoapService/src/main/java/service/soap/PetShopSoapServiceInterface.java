package service.soap;

import pet.shop.Product;
import pet.shop.Shop;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PetShopSoapServiceInterface {
    @WebMethod
    String welcome();

    @WebMethod
    String findProductsByDescription(String description);

    @WebMethod
    String findProductById(String id);

    @WebMethod
    String addProducts(Product[] products);

    @WebMethod
    String buyProductById(String id);

    @WebMethod
    String buyProductByDescription(String description);

    @WebMethod
    public String printShop();
}
