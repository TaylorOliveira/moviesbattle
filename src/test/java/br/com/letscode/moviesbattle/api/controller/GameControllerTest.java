package br.com.letscode.moviesbattle.api.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GameControllerTest {

    @LocalServerPort
    private static int port;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/game";
    }

    @Test
    void success_InitializeGame() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/initialize")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void success_NextQuiz() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/next-quiz")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void success_FinalizeGame() {
        given()
                .pathParam("gameId", 1)
                .get("/finalize/{gameId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void success_ValidateRound() {
        given()
                .pathParam("roundId", 1)
                .queryParam("choice", ChoiceMovieEnum.LEFT)
                .get("/quiz/validate/{roundId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}