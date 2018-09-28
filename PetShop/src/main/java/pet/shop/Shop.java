package pet.shop;

import pet.shop.exceptions.CannotAddProductException;
import pet.shop.exceptions.CannotBuyProductException;
import pet.shop.exceptions.ProductNotFoundException;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop {
    ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Shop() {
        products = new ArrayList<Product>();
        initProducts();
    }

    public ArrayList<Product> findProductsByDescription(String description) throws ProductNotFoundException {
        ArrayList<Product> foundProducts = new ArrayList<Product>();

        for (Product p : products) {
            if (p.description.toLowerCase().contains(description.toLowerCase())) {
                foundProducts.add(p);
            }
        }
        if (foundProducts.size() > 0) {
            return foundProducts;
        }
        throw new ProductNotFoundException("Product not found");
    }

    public Product findProductById(String id) throws ProductNotFoundException {
        for (Product p : products) {
            if (p.id.equals(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    public void addProduct(String description, String id, Double rub, Double usd) throws CannotAddProductException {
        if (rub == null && usd == null) {
            throw new CannotAddProductException("You should refine price");
        }
        if (rub == null) {
            rub = usd * 30;
        }

        if (usd == null) {
            usd = rub / 30;
        }

        if (usd * 30 != rub) {
            throw new CannotAddProductException("Cannot add product. RUB price is not equals to USD price");
        }
        if (id.length() < 8 || id.length() > 10) {
            throw new CannotAddProductException("Cannot add product. Product ID must be 8-10 digits");
        }

        for (Product p : products) {
            if (p.id.equals(id) || p.description.toLowerCase().equals(description.toLowerCase())) {
                throw new CannotAddProductException("Product is already in PetShop");
            }
        }

        Product product = new Product(description, id, rub, usd);
        products.add(product);
    }

    public void addProduct(Product p) throws CannotAddProductException{
        addProduct(p.getDescription(), p.getId(), p.getPriceRUB(), p.getPriceUSD());
    }
    public void addProducts(Product[] products) throws CannotAddProductException{
        ArrayList<Product> addedProducts = new ArrayList<>();
        try {
            for (Product p : products) {
                addProduct(p);
                addedProducts.add(p);
            }
        } catch (CannotAddProductException ex) {
            for (Product p : addedProducts) {
                deleteProduct(p);
            }
            throw ex;
        }
    }

    public void buyProductById(String id) throws CannotBuyProductException, ProductNotFoundException{
        try {
            Product fountProduct = findProductById(id);
            if (fountProduct.isInStock()) {
                fountProduct.setInStock(false);
            } else {
                throw new CannotBuyProductException("Product out of stock");
            }
        } catch (ProductNotFoundException e) {
            throw e;
        }
    }

    public void buyProductByDescription(String description) throws CannotBuyProductException, ProductNotFoundException{
        try {
            ArrayList<Product> foundProducts = findProductsByDescription(description);

            if (foundProducts.size() > 1) {
                throw new CannotBuyProductException("We find several products for your request. Please refine request");
            }

            if (foundProducts.get(0).isInStock()) {
                foundProducts.get(0).setInStock(false);
            } else {
                throw new CannotBuyProductException("Product out of stock");
            }
        } catch (ProductNotFoundException e) {
            throw e;
        }
    }

    public void deleteProduct(Product product){
        for (Product p: products){
            if(p.equals(product)){
                products.remove(p);
            }
        }
    }

    @Override
    public String toString() {
        return products.toString();
    }


    public void initProducts(){
        try {
            addProduct("JustFoodForDogs Fresh-on-the-Go Beef and Russet Potato Dog Food", "00000000", new Double("165"), new Double("5.5"));
            addProduct("JustFoodForDogs Fresh-on-the-Go Chicken and White Rice Dog Food", "00000001", new Double("150"), new Double("5"));
            addProduct("Kiwi Kitchens Super Food Booster Fish Recipe for Cats & Dogs", "00000002", new Double("390"), new Double("13"));
            addProduct("Kiwi Kitchens Lamb Liver Freeze Dried Dog Treats", "00000003", new Double("600"), new Double("20"));
        } catch (CannotAddProductException ex) {
            ex.printStackTrace();
        }
    }
}
