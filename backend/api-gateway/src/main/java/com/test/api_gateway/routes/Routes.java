package com.test.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;

@Configuration
public class Routes {

    // Product Service Route
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/v1/product"),
                        HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    // Order Service Route
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/v1/order"),
                        HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    // Inventory Service Route
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/v1/inventory/**"),
                        HandlerFunctions.http("http://localhost:8082"))
                .build();
    }

    // Product Service Swagger Route with GET method
    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("product_service_swagger")
                .route(GET("/product-service/v3/api-docs"), // Explicitly enforce GET method
                        HandlerFunctions.http("http://localhost:8080/v3/api-docs"))
                .build();
    }

    // Order Service Swagger Route with GET method
    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(GET("/order-service/v3/api-docs"), // Explicitly enforce GET method
                        HandlerFunctions.http("http://localhost:8081/v3/api-docs"))
                .build();
    }

//    // Inventory Service Swagger Route with GET method
//    @Bean
//    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//        System.out.println("Calling the inventory service ...");
//        return GatewayRouterFunctions.route("inventory_service_swagger")
//                .route(GET("/inventory-service/v3/api-docs"), // Explicitly enforce GET method
//                        HandlerFunctions.http("http://localhost:8082/v3/api-docs"))
//                .build();
//    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route(GET("/inventory-service/v3/api-docs"), // Explicitly enforce GET method
                        HandlerFunctions.http("http://localhost:8082/v3/api-docs")) // Forward request directly
                .build();
    }


    // Test Route
    @Bean
    public RouterFunction<ServerResponse> testRoute() {
        return GatewayRouterFunctions.route("test_route")
                .route(GET("/test"), // Explicitly enforce GET method
                        request -> ServerResponse.ok().body("Test route working"))
                .build();
    }
}
