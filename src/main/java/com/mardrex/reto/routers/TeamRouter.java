package com.mardrex.reto.routers;

import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
        return route(GET("/team/getAll"),
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
                POST("/team/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(executor)
        );
    }

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/get/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "getTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")},
                                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}


                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> getTeam(GetTeamUseCase getTeamUseCase) {
        return route(
                GET("/team/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamUseCase.aplly(request.pathVariable("id")), TeamDTO.class))
        );
    }

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/delete/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.DELETE,
                            beanClass = DeleteTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "deleteTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")},
                                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> deleteTeam(DeleteTeamUseCase deleteTeamUseCase) {
        return route(
                DELETE("/team/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTeamUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/update/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.PUT,
                            beanClass = UpdateTeamUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "updateTeam",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")},
                                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                            )
                    )
            }
    )

    public RouterFunction<ServerResponse> updateTeam(UpdateTeamUseCase updateTeamUseCase) {
        Function<TeamDTO, Mono<ServerResponse>> executor = teamDTO -> updateTeamUseCase.apply(teamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(result), String.class))
                );

        return route(
                PUT("/team/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDTO.class)
                        .flatMap(executor)
        );
    }

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/team/findByCountry/{country}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = GetTeamsByCountryUseCase.class,
                            beanMethod = "apply",
                            operation = @Operation(
                                    operationId = "findByCountry",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = TeamDTO.class))),
                                            @ApiResponse(responseCode = "400", description = "Invalid"),
                                            @ApiResponse(responseCode = "404", description = "Not found")},
                                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}


                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> findTeamsByCountry(GetTeamsByCountryUseCase getTeamsByCountryUseCase) {
        return route(
                GET("/team/findByCountry/{country}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamsByCountryUseCase.apply(request.pathVariable("country")), TeamDTO.class))
        );
    }

}
