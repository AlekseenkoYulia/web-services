package service.rest;

import pet.shop.Exceptions.ProductNotFoundException;
import pet.shop.Product;
import pet.shop.Shop;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/shop")
public class PetShopRestService {
    static Shop petShop = new Shop();

    @GET
    public Response welcome() {
        return Response.status(200).entity("Welcome to Pet Shop!").build();
    }


    @GET
    @Path("/search/{description}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductsByDescription(@PathParam("description") String description) {
        try {
            ArrayList<Product> products = petShop.findProductsByDescription(description);
            return Response.status(200).entity(products).build();
        } catch (ProductNotFoundException e) {
            return Response.status(404).entity("Products not found").build();
        }
    }

    @GET
    @Path("/search/{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findProductById(@PathParam("id") String id) {
        try {
            Product product = petShop.findProductById(id);
            return Response.status(200).entity(product).build();
        } catch (ProductNotFoundException e) {
            return Response.status(404).entity("Product not found").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducts(Product[] products) {
        StringBuilder responseMsg = new StringBuilder();
        for (Product p : products) {
            String status = petShop.addProduct(p);
            if (status.equals("Add product: success!")) {
                responseMsg.append("Add product: " + p + " - Success!" + "\n");
            } else {
                responseMsg.append("Can't add product: " + p + " - " + status + "\n");
            }
        }
        return Response.status(200).entity(responseMsg.append("Operation complete.").toString()).build();
    }

    @GET
    @Path("/buy/{id: \\d+}")
    public Response buyProductById(@PathParam("id") String id) {
        String status = petShop.buyProductById(id);
        return Response.status(200).entity(status).build();
    }

    @GET
    @Path("/buy/{description}")
    public Response buyProductByDescription(@PathParam("description") String description) {
        String status = petShop.buyProductByDescription(description);
        return Response.status(200).entity(status).build();
    }
}