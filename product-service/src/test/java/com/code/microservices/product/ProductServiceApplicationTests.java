package com.code.microservices.product;

import com.code.microservices.product.dto.ProductResponse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;

import org.bson.Document;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import org.testcontainers.containers.MongoDBContainer;
import io.restassured.RestAssured;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
class ProductServiceApplicationTests {

	private static MongoDBContainer mongoDBContainer ;
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(ApplicationContext context){
			RestAssured.baseURI = "http://localhost";
		 RestAssured.port = port;
		mongoDBContainer = context.getBean(MongoDBContainer.class);
		mongoDBContainer.start();

		}


	@Test
	void shouldCreateProduct() {
		//create a product
		String requestBody = """				
				{
					"name": "watch",
					"description":"smartwatch 21",
					"price":232
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.post("/api/product")
				.then()
				.statusCode(201)
		        .body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("watch"))
				.body("description", Matchers.equalTo("smartwatch 21"))
				.body("price", Matchers.equalTo(232));
	}
	@Test
	void shoudGetAllProducts(){
		String requestBody = """
            {
                "name": "Product 1",
                "description": "Description for Product 1",
                "price": 10.99
            }
            """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.post("/api/product")
				.then()
				.statusCode(201);

		RestAssured.given()
				.get("/api/product")
				.then()
				.statusCode(200)
				.body("[0].name", Matchers.equalTo("Product 1"))
				.body("[0].description", Matchers.equalTo("Description for Product 1"))
				.body("[0].price", Matchers.equalTo(10.99f));
		System.out.printf("Test passed");

	}


}
