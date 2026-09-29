package iuh.fit.shoppingcart.controller;

import iuh.fit.shoppingcart.dto.RepriceReportDTO;
import iuh.fit.shoppingcart.dto.RevenueShareDTO;
import iuh.fit.shoppingcart.model.Product;
import iuh.fit.shoppingcart.service.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@RequestScoped
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public Response getAll() {
        return Response.ok(productService.getAllProducts()).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getById(@PathParam("id") int id) {
        Product p = productService.getProductById(id);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "Product not found")).build();
        }
        return Response.ok(p).build();
    }

    @POST
    public Response create(Product product) {
        if (productService.createProduct(product)) {
            return Response.status(Response.Status.CREATED)
                    .entity(Map.of("message", "Product created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", "Failed to create product")).build();
    }

    @PUT
    @Path("/{id: \\d+}")
    public Response update(@PathParam("id") int id, Product product) {
        product.setId(id);
        if (productService.updateProduct(product)) {
            return Response.ok(Map.of("message", "Product updated")).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Product not found or not modified")).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    public Response delete(@PathParam("id") int id) {
        if (productService.deleteProduct(id)) {
            return Response.ok(Map.of("message", "Product deleted")).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("message", "Product not found")).build();
    }

    // ---------- Yeu cau 3 ----------
    @PUT
    @Path("/dynamic-pricing")
    public Response dynamicPricing() {
        List<RepriceReportDTO> report = productService.applyDynamicRepricing();
        return Response.ok(report).build();
    }

    // ---------- Yeu cau 5 ----------
    @GET
    @Path("/analytics/revenue-share")
    public Response getRevenueShare() {
        List<RevenueShareDTO> stats = productService.getRevenueShareReport();
        return Response.ok(stats).build();
    }
}
