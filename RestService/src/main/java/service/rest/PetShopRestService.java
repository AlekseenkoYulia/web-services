package service.rest;

import pet.shop.exceptions.CannotAddProductException;
import pet.shop.exceptions.CannotBuyProductException;
import pet.shop.exceptions.ProductNotFoundException;
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
            return Response.status(404).entity(e.getMessage()).build();
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
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducts(Product[] products) {
        try {
            petShop.addProducts(products);
            return Response.status(200).entity("Ok").build();
        } catch (CannotAddProductException ex) {
            return Response.status(400).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("/buy/{id: \\d+}")
    public Response buyProductById(@PathParam("id") String id) {
        try {
            petShop.buyProductById(id);
            return Response.status(200).entity("Ok").build();
        } catch (ProductNotFoundException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        } catch (CannotBuyProductException ex) {
            return Response.status(400).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("/buy/{description}")
    public Response buyProductByDescription(@PathParam("description") String description) {
        try {
            petShop.buyProductByDescription(description);
            return Response.status(200).entity("Ok").build();
        } catch (ProductNotFoundException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        } catch (CannotBuyProductException ex) {
            return Response.status(400).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response showAllProducts() {
        return Response.status(200).entity(petShop).build();
    }
}