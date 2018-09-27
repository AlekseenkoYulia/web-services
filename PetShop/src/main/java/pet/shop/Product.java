package pet.shop;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    String description;
    String id;
    Double priceRUB;
    Double priceUSD;
    Boolean inStock = true;

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Double getPriceRUB() {
        return priceRUB;
    }

    public Double getPriceUSD() {
        return priceUSD;
    }

    public Boolean isInStock() {
        return inStock;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPriceRUB(Double priceRUB) {
        this.priceRUB = priceRUB;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public void setInStock(Boolean inStock) {

        if (inStock == null) {
            this.inStock = true;
        } else {
            this.inStock = inStock;
        }
    }

    public Product() {
    }

    public Product(String description, String id, Double priceRUB, Double priceUSD) {
        this.description = description;
        this.id = id;
        this.priceRUB = priceRUB;
        this.priceUSD = priceUSD;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", RUB=" + priceRUB + ", USD=" + priceUSD + ", In stock=" + inStock + "]";
    }
}

