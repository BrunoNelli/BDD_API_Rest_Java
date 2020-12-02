package br.qa.brunonelli.rest.utils;

import io.restassured.RestAssured;

public class Utils {

	public static Integer getIdDaContaPeloNome(String nome) {
		return RestAssured.get("/contas?nome="+nome).then().extract().path("id[0]");
	}
	
	public static Integer getIdDaMovimentacaoPelaDescricao(String desc) {
		return RestAssured.get("/transacoes?descricao="+desc).then().extract().path("id[0]");
	}
}
