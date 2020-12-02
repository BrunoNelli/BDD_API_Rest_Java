package br.qa.brunonelli.rest.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.qa.brunonelli.rest.core.BaseTest;
import br.qa.brunonelli.rest.utils.Utils;

public class ContasTest extends BaseTest {
	
	@Test
	public void deveIncluirContaSucesso() {		
		given()
			.body("{ \"nome\": \"Conta inserida\" }")
		.when()
			.post("/contas/")
		.then()
			.statusCode(201)
		;
	}
	
	@Test
	public void deveAlterarContaComSucesso() {
		Integer CONTA_ID = Utils.getIdDaContaPeloNome("Conta para alterar");
		given()
			.body("{ \"nome\": \"Conta alterada\" }")
			.pathParam("id", CONTA_ID)
		.when()
			.put("/contas/{id}")
		.then()
			.statusCode(200)
			.body("nome", is("Conta alterada"))
		;
	}
	
	@Test
	public void naoDeveInserirContaComMesmoNome() {		
		given()
			.body("{ \"nome\": \"Conta mesmo nome\" }")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("Já existe uma conta com esse nome!"))
		;
	}
}
