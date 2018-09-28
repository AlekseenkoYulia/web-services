package pet.shop;

import pet.shop.exceptions.ProductNotFoundException;

import java.util.ArrayList;

public class Shop {
    ArrayList<Product> products;

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
        throw new ProductNotFoundException();
    }

    public Product findProductById(String id) throws ProductNotFoundException {
        for (Product p : products) {
            if (p.id.equals(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException();
    }

    public String addProduct(String description, String id, Double rub, Double usd) {
        if (rub == null && usd == null) {
            return "You should refine price";
        }
        if (rub == null) {
            rub = usd * 30;
        }

        if (usd == null) {
            usd = rub / 30;
        }

        if (usd * 30 != rub) {
            return "Cannot add product. RUB price is not equals to USD price";
        }
        if (id.length() < 8 || id.length() > 10) {
            return "Cannot add product. Product ID must be 8-10 digits";
        }

        for (Product p : products) {
            if (p.id.equals(id) || p.description.toLowerCase().equals(description.toLowerCase())) {
                return "Product is already in PetShop";
            }
        }

        Product product = new Product(description, id, rub, usd);
        products.add(product);
        return "Add product: success!";
    }

    public String addProduct(Product p) {
        return addProduct(p.getDescription(), p.getId(), p.getPriceRUB(), p.getPriceUSD());
    }

    public String buyProductById(String id) {
        try {
            Product fountProduct = findProductById(id);
            if (fountProduct.isInStock()) {
                fountProduct.setInStock(false);
                return "Buy product: success!";
            } else {
                return "Product out of stock";
            }
        } catch (ProductNotFoundException e) {
            return "Product not found";
        }
    }

    public String buyProductByDescription(String description) {
        try {
            ArrayList<Product> foundProducts = findProductsByDescription(description);

            if (foundProducts.size() > 1) {
                return "We find several products for your request. Please refine request";
            }

            if (foundProducts.get(0).isInStock()) {
                foundProducts.get(0).setInStock(false);
                return "Buy product: success!";
            } else {
                return "Product out of stock";
            }
        } catch (ProductNotFoundException e) {
            return "Product not found";
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (Product p : products)
            result += p + "\n";
        return result;
    }


    public void initProducts() {
        addProduct("JustFoodForDogs Fresh-on-the-Go Beef and Russet Potato Dog Food", "00000000", new Double("165"), new Double("5.5"));
        addProduct("JustFoodForDogs Fresh-on-the-Go Chicken and White Rice Dog Food", "00000001", new Double("150"), new Double("5"));
        addProduct("Kiwi Kitchens Super Food Booster Fish Recipe for Cats & Dogs", "00000002", new Double("390"), new Double("13"));
        addProduct("Kiwi Kitchens Lamb Liver Freeze Dried Dog Treats", "00000003", new Double("600"), new Double("20"));
    }

    public static void main(String[] args) throws Exception{
        Shop s = new Shop();

        System.out.println(s.findProductsByDescription("kiwi"));
        Product[] products1 = {new Product("test2", "00000008", null,(double)1),
                new Product("test3", "0000010000", (double)135, null)};
        for (Product p: products1){
            s.addProduct(p);
        }
        System.out.println(s.toString());
        System.out.println(s.findProductsByDescription("test"));
        System.out.println(s.buyProductById("00000008"));
        System.out.println(s.toString());
        System.out.println(s.buyProductByDescription("test3"));
    }
}
