//package br.com.letscode.moviesbattle.api.controller;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.junit.jupiter.api.BeforeAll;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.Test;
//import io.restassured.RestAssured;
//import org.junit.runner.RunWith;
//
//import static io.restassured.RestAssured.given;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class RankingControllerTest {
//
//    @LocalServerPort
//    private static int port;
//
//    @BeforeAll
//    static void setUp() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.port = port;
//        RestAssured.basePath = "/ranking";
//    }
//
//    @Test
//    void success_Ranking() {
//        given()
//                .accept(ContentType.JSON)
//                .when()
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }
//}