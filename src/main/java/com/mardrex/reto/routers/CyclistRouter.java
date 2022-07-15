package com.mardrex.reto.routers;


import com.mardrex.reto.models.CyclistDTO;

import com.mardrex.reto.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CyclistRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/cyclist/getAll",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = ListCyclistUseCase.class,
                            beanMethod = "get",
                            operation = @Operation(
                                    operationId = "getAllCyclists",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )

    public RouterFunction<ServerResponse> getAllCyclists(ListCyclistUseCase listCyclistUseCase) {
        return route(GET("/cyclist/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listCyclistUseCase.get(), CyclistDTO.class))
        );
    }


    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/cyclist/create",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = CreateCyclistUseCase.class,
                            beanMethod = "aplly",
                            operation = @Operation(
                                    operationId = "save",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )

    public RouterFunction<ServerResponse> createCyclist(CreateCyclistUseCase createCyclistUseCase) {

        Function<CyclistDTO, Mono<ServerResponse>> executor = cyclistDTO ->
                createCyclistUseCase.apply(cyclistDTO)
                        .flatMap(result ->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result));


        return route(POST("/cyclist/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDTO.class)
                        .flatMap(executor));
    }


    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/cyclist/get/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "getCyclist",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )

    public RouterFunction<ServerResponse> getCyclist(GetCyclistUseCase getCyclistUseCase) {

        return route(
                GET("/cyclist/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistUseCase.apply(request.pathVariable("id")), CyclistDTO.class))
        );
    }

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/cyclist/delete/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.DELETE,
                            beanClass = DeleteCyclistUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "deleteCyclist",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = CyclistDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> deleteCyclist(DeleteCyclistUseCase deleteCyclistUseCase) {

        return route(
                DELETE("/cyclist/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCyclistUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
