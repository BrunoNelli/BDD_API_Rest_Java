package br.qa.brunonelli.rest.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.qa.brunonelli.rest.core.BaseTest;
import br.qa.brunonelli.rest.utils.Utils;

public class SaldoTest extends BaseTest {
	
	@Test
	public void deveCalcularSaldoContas() {		
		Integer CONTA_ID = Utils.getIdDaContaPeloNome("Conta para saldo");
		 
		given()
		.when()
			.get("/saldo")
		.then()
			.statusCode(200)
			.body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"))
		;
	}
}
