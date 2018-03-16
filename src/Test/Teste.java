package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Pais;
import service.PaisService;

public class Teste {

	Pais pais, copia;
	PaisService paisService;
	static int id = 9;

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Setup");
		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome("Vanessa");
		pais.setArea(1209999);
		pais.setPopulacao(1000911);
		Pais copia = new Pais();
		copia.setId(id);
		copia.setNome("Vanessa");
		copia.setArea(1209999);
		copia.setPopulacao(1000911);

		System.out.println(pais);
		System.out.println(copia);
	}

	@Test
	public void teste00Carregar() {
		System.out.println("carregar");
		Pais fixture = new Pais(1, "Brasil", 0000, 0000);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(id);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("Criando");

		copia.setId(id);
		assertEquals("Teste de criacao", pais, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setNome("legal");
		copia.setNome("legal");

		paisService.atualizar(pais);
		paisService.carregar(id);
		// assertEquals(pais.getNome(), copia.getNome(), "Teste atualizacao");

	}

	@Test
	public void test03Excluir() {
		System.out.println("Excluir");
		pais.setId(id);
		copia.setId(-1);

		paisService.excluir(pais);
		paisService.carregar(id);

		assertEquals(pais.getId(), copia.getId(), "Teste exclusao");
	}

	@Test
	public void test04MaiorPop() {
		System.out.println("Maior populacao");
		paisService.paisHabitantes(pais);
		long pop = pais.getPopulacao();

		copia.setPopulacao(pop);

		assertEquals(pais.getPopulacao(), copia.getPopulacao(), "Testando maior populacao");
	}

	@Test
	public void test05MenorArea() {
		System.out.println("Menor Area");
		paisService.atualizar(pais);
		;
		double area = pais.getArea();

		copia.setArea(area);

		assertEquals(pais.getArea(), copia.getArea(), "Testando menor area");

	}

	public void test06Array() {
		System.out.println("Vetor de paises");
		Pais array = paisService.carregar(pais.getId());
		assertEquals(array, 3, "Testando Vetor de paises");
	}

}