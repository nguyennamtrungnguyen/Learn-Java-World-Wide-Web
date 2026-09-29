package iuh.fit.shoppingcart.controller;

import iuh.fit.shoppingcart.dto.AddCartResponseDTO;
import iuh.fit.shoppingcart.dto.CheckoutSummaryDTO;
import iuh.fit.shoppingcart.dto.CustomerBillDTO;
import iuh.fit.shoppingcart.model.ShoppingCart;
import iuh.fit.shoppingcart.service.ShoppingCartService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@RequestScoped
@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShoppingCartController {

    @Inject
    private ShoppingCartService cartService;

    @GET
    public Response getAll() {
        return Response.ok(cartService.getAllCarts()).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getById(@PathParam("id") int id) {
        ShoppingCart cart = cartService.getCartById(id);
        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "Cart item not found")).build();
        }
        return Response.ok(cart).build();
    }

    @POST
    public Response create(ShoppingCart cart) {
        if (cartService.createCart(cart)) {
            return Response.status(Response.Status.CREATED)
                    .entity(Map.of("message", "Cart item created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "Failed to create cart item")).build();
    }

    @PUT
    @Path("/{id: \\d+}")
    public Response update(@PathParam("id") int id, ShoppingCart cart) {
        cart.setId(id);
        if (cartService.updateCart(cart)) {
            return Response.ok(Map.of("message", "Cart item updated")).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Cart item not found or not modified")).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    public Response delete(@PathParam("id") int id) {
        if (cartService.deleteCart(id)) {
            return Response.ok(Map.of("message", "Cart item deleted")).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Cart item not found")).build();
    }

    // ---------- Yeu cau 1 ----------
    @GET
    @Path("/bill/{customerName}")
    public Response getBillByCustomer(@PathParam("customerName") String customerName) {
        CustomerBillDTO bill = cartService.calculateCustomerBill(customerName);
        return Response.ok(bill).build();
    }

    // ---------- Yeu cau 2 ----------
    @POST
    @Path("/add-with-cap")
    public Response addWithCap(ShoppingCart cart) {
        try {
            AddCartResponseDTO result = cartService.addItemToCart(cart);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage())).build();
        }
    }

    // ---------- Yeu cau 4 ----------
    @POST
    @Path("/checkout/{customerName}")
    public Response checkout(@PathParam("customerName") String customerName) {
        try {
            CheckoutSummaryDTO summary = cartService.checkout(customerName);
            return Response.ok(summary).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", e.getMessage())).build();
        }
    }
}
