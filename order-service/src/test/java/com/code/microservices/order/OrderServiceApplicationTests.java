package com.code.microservices.order;
import com.code.microservices.order.stubs.InventoryClientStub;
import io.restassured.RestAssured;

import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
@AutoConfigureWireMock(port=0)
class OrderServiceApplicationTests {
	private static MySQLContainer mySQLContainer;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp(ApplicationContext context) {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		mySQLContainer = context.getBean(MySQLContainer.class);
		mySQLContainer.start();

	}


	@Test
	void shouldCreateOrder() {
		String responseBody = """
				{
				 "skuCode":"iphone_15",
				            "price":1234,
				            "quantity":1
				}""";
		InventoryClientStub.stubInventoryCall("iphone_15",1);
				var responseString = RestAssured.given()
				.contentType("application/json")
				.body(responseBody)
						.when()
						.post("/api/order")
						.then()
						.log().all()
						.statusCode(201)
						.extract()
						.body()
						.asString();
assertThat(responseString,Matchers.is("Order Placed Successfully"));
	}
}