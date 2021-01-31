package com.devops.dxc.devops;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest
class DevopsApplicationTests {

	 @Test
	 void contextLoads() {
		 return;
	 }

	 @ParameterizedTest(name = "ahorro = {0} tiene fondos ")
	 @CsvSource({
			 "5000000"
	 })
	 void PoseeFondo(int ahorro) {
		assertTrue("Error en ahorro", ahorro > 0);
		 return;
	 }

	 @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro saldo ")
	 @CsvSource({
			 "900000, 1000000"
	 })
	 public void RetirarSaldo(int ahorro,int sueldo) {
 
		 Dxc res = new Dxc(ahorro, sueldo);
		 int mi10 = res.getDxc();
 
		 assertEquals(ahorro, mi10);
		 return;
	 }

	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro {2} UF ")
	@CsvSource({
			"5000000, 1000000, 150"
	})
	public void Retirar150UF(int ahorro,int sueldo, int retiroUF) {

		Dxc res = new Dxc(ahorro, sueldo);
		int mi10 = res.getDxc();
		int esperado = retiroUF* Util.getUf();

		assertEquals(esperado, mi10);
		return;
	}

	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, paga Impuesto ")
	@CsvSource({
			"6000000, 2500000"
	})
	public void PagaImpuesoCaso1() {

		Dxc res = new Dxc(6000000, 2500000);
		int mi10 = res.getDxc();
		
		
		int imp = res.getImpuesto();

		System.out.println("impuesto=" + imp);
		assertTrue("Error en Impuesto", imp > 0);
		return;
	}

	

}
