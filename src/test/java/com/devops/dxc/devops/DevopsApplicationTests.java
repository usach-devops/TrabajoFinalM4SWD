package com.devops.dxc.devops;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest
class DevopsApplicationTests {
	private static int valUF;

@BeforeAll
	 static void contextLoads() {
		 valUF = Util.getUf();
		 System.out.println("Uf Obtenida=" + valUF);
		 return;
	 }

	 private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

	@ParameterizedTest(name = "ahorro = {0} --> valor fondo invalido ")
	@CsvSource({
			"''",
			"0"
	})
	void ValorFondoInvalido(String ahorro) {

	   int ahorroaux =0;

	   if (isNumeric(ahorro))
		  ahorroaux = Integer.parseInt(ahorro);
		  
	   Dxc res = new Dxc(ahorroaux, 0);
	   ahorroaux = res.getAhorro();

	   assertTrue("Error en ahorro " + ahorroaux , ahorroaux == 0);
		return;
	}

	 @ParameterizedTest(name = "ahorro = {0} tiene fondos ")
	 @CsvSource({
			 "999999",
			 "1100000",
			 "5000000",
			 "44000000",
			 "50000000"
	 })
	 void PoseeFondo(int ahorro) {
	   
		Dxc res = new Dxc(ahorro, 0);
		ahorro = res.getAhorro();

		assertTrue("Error en ahorro " + ahorro , ahorro > 0);
		 return;
	 }

	 @ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro saldo ")
	 @CsvSource({
	/*	" , ",	
		"0, 0",*/
		"900000, 1000000"/*,
		"1100000, 1000000",
		"5000000, 1500000",
		"44000000, 1500000",
		"50000000, 2500000"*/
	 })
	 public void RetirarSaldo(int ahorro,int sueldo) {
 
		 Dxc res = new Dxc(ahorro, sueldo);
		 int mi10 = res.getDxc();
 
		 assertEquals(ahorro, mi10);
		 return;
	 }


	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro {2} UF ")
	@CsvSource({
		/*" , , ",
		"0, 0, 0",*/
		"900000, 1000000, 150",
		"1100000, 1000000, 150",
		"5000000, 1500000, 150",
		"5000000, 1000000, 150"
	})
	public void NoPuedeRetirar150UF(int ahorro,int sueldo, int retiroUF) {

		Dxc res = new Dxc(ahorro, sueldo);
		int mi10 = res.getDxc();
		int esperado = retiroUF* valUF;

		assertNotEquals(esperado, mi10);
		return;
	}

	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, retiro {2} UF ")
	@CsvSource({
		/*" , , ",
		"0, 0, 0",*/
		"44000000, 1500000, 150",
		"50000000, 2500000, 150"
	})
	public void PuedeRetirar150UF(int ahorro,int sueldo, int retiroUF) {

		Dxc res = new Dxc(ahorro, sueldo);
		int mi10 = res.getDxc();
		int esperado = retiroUF* valUF;

		assertEquals(esperado, mi10);
		return;
	}

	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, saldo cero ")
	@CsvSource({
		/*" , ",
		"0, 0",	*/
		"900000, 1000000"
	})
	public void SaldoCero(int ahorro,int sueldo) {

		Dxc res = new Dxc(ahorro, sueldo);
		res.getDxc();

		int saldo = res.getSaldo();

		assertEquals(0, saldo);
		return;
	}

	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, saldo cero ")
	@CsvSource({
		"1100000, 1000000",
		"5000000, 1500000",
		"44000000, 1500000",
		"50000000, 2500000"
	})
	public void SaldoMayorACero(int ahorro,int sueldo) {

		Dxc res = new Dxc(ahorro, sueldo);
		res.getDxc();

		int saldo = res.getSaldo();

		assertTrue("Error en Saldo " + saldo , saldo > 0);
		return;
	}


	@ParameterizedTest(name = "ahorro = {0} , sueldo {1}, paga Impuesto ")
	@CsvSource({
	/*	" , ",*/
		"0, 0",	
		"900000, 1000000",
		"1100000, 1000000",
		"6000000, 2500000",
		"44000000, 1500000",
		"50000000, 2500000",
		"50000000, 1499999",
		"50000000, 1500000",
		"50000000, 1529999",
		"50000000, 1530000",
		"50000000, 1530001",
		"50000000, 2549999",
		"50000000, 2550000",
		"50000000, 2550001",
		"50000000, 3569999",
		"50000000, 3570000",
		"50000000, 3570001",
		"50000000, 4589999",
		"50000000, 4590000",
		"50000000, 4590001",
		"50000000, 6119999",
		"50000000, 6120000",
		"50000000, 6120001",
		"50000000, 15817999",
		"50000000, 15818000",
		"50000000, 15818001"
	})
	public void PagaImpuesoCaso1(int ahorro, int sueldo) {

		Dxc res = new Dxc(ahorro, sueldo);
		res.getDxc();

		int imp = res.getImpuesto();

		System.out.println("impuesto=" + imp);
		assertTrue("Error en Impuesto", imp >=0);
		return;

	}
}

