package br.qa.brunonelli.rest.suite;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.qa.brunonelli.rest.core.BaseTest;
import br.qa.brunonelli.rest.tests.AuthTest;
import br.qa.brunonelli.rest.tests.ContasTest;
import br.qa.brunonelli.rest.tests.MovimentacoesTest;
import br.qa.brunonelli.rest.tests.SaldoTest;
import io.restassured.RestAssured;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ContasTest.class,
	MovimentacoesTest.class,
	SaldoTest.class,
	AuthTest.class
})
public class Suite extends BaseTest {

	@BeforeClass
	public static void login() {
		Map<String, String> login = new HashMap<>();
		login.put("email", "bruno@nelli");
		login.put("senha", "654321");
		
		String TOKEN = given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		
		RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
		
		RestAssured.get("/reset").then().statusCode(200);
	}
}
