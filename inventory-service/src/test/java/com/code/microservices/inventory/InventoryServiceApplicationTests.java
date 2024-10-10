package com.code.microservices.inventory;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import io.restassured.RestAssured;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
class InventoryServiceApplicationTests {

	private static MySQLContainer mySQLContainer;
	@LocalServerPort
	private int port;
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@BeforeAll
	static void beforeAll(ApplicationContext context) {
		mySQLContainer = context.getBean(MySQLContainer.class);
		mySQLContainer.start();
	}

	@Test
	void isInStock() {
//			Boolean  response = RestAssured.given()
//					.contentType("application/json")
//					.queryParam("skuCode","iphone_15")
//					.queryParam("quantity", 100)
//					.when()
//					.get("/api/inventory")
//					.then()
//					.log().all()
//					.statusCode(200)
//					.extract()
//					.body()
//					.asString().equals("true");
		var response = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone_15&quantity=1")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);
		assertTrue(response);

		var negativeResponse = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone_15&quantity=1000")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);
		assertFalse(negativeResponse);
	}

}
