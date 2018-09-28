package service.soap;

import pet.shop.Product;
import pet.shop.exceptions.CannotAddProductException;
import pet.shop.exceptions.ProductNotFoundException;
import pet.shop.serviceinterface.PetShopInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:6666/soap?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.service/", "PetShopSoapServiceService");

        Service service = Service.create(url, qname);

        PetShopInterface s = service.getPort(PetShopInterface.class);

        System.out.println(s.welcome());
        System.out.println(s.printShop());
        try{
            System.out.println(s.findProductsByDescription("kiwi"));
        } catch (ProductNotFoundException e){
            System.out.println(e.getMessage());
        }
        Product[] products1 = {new Product("test1", "1000000000", null,(double)1),
                new Product("test2", "2000000000", (double)135, null),
                new Product("test3", "3000000000", (double)90, (double)3)};

        Product product2 = new Product("test3", "5000000000", null,(double)1);
        Product product3 = new Product("test6", "6000000000", null,null);
        Product product4 = new Product("test7", "6000000000", (double)90,(double)2);
        Product[] testProducts = {product2};
        try{
            s.addProducts(products1);
            System.out.println(s.printShop());
        } catch (CannotAddProductException ex){
            System.out.println(ex.getMessage());
        }

        try{
            s.addProducts(testProducts);
            System.out.println(s.printShop());
        } catch (CannotAddProductException ex){
            System.out.println(ex.getMessage());
        }

    }
}
