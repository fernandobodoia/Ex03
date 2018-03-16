package service;

import DAO.PaisDao;
import model.Pais;

public class PaisService {
	PaisDao dao = new PaisDao();

	public void Pais() {
		dao = new PaisDao();
	}

	public void criar(Pais pais) {
		dao.criar(pais);
	}

	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}

	public void excluir(Pais pais) {
		dao.excluir(pais);

	}

	public Pais carregar(int id) {
		Pais pais = dao.carregar(id);
		return pais;
	}

	public void paisHabitantes(Pais pais) {
		dao.paisHabitantes(pais);
	}

	public void paisMenorArea(Pais pais) {
		dao.paisMenorArea(pais);
	}
	public String[] paisVetPaises() {
		return dao.paisVetPaises();
	}
}
