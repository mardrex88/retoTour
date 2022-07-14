package com.mardrex.reto.routers;

import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.usecases.CreateTeamUseCase;
import com.mardrex.reto.usecases.ListTeamUseCase;
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
public class TeamRouter {
    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/getAll",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = ListTeamUseCase.class,
                            beanMethod = "get",
                            operation = @Operation(
                                    operationId = "get",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )

    public RouterFunction<ServerResponse> getAll(ListTeamUseCase listTeamUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listTeamUseCase.get(), TeamDTO.class))
        );
    }


    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/create",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = CreateTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "save",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> create(CreateTeamUseCase createTeamUseCase) {

        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> createTeamUseCase.apply(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(result), String.class))
                );
        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
        request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(executor)
        );
    }
}
