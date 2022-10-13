package web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import web.modelo.Turma;

@Repository
public class TurmaDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Turma turma) {
		manager.persist(turma);
	}

	public void altera(Turma turma) {
		manager.merge(turma);
	}

	public List<Turma> listaTurmas() {
		return manager.createQuery("select t from Turma t", Turma.class).getResultList();
	}

	public List<Turma> buscaPorNome(String nome) {
		return manager.createQuery("select t from Turma t where t.nome = :nome", Turma.class).setParameter("nome", nome)
				.getResultList();
	}

	public Turma buscaPorId(Long id) {
		return manager.find(Turma.class, id);
	}

	public void remove(Long id) {
		manager.createQuery("delete from Turma t where t.id = :id").setParameter("id", id).executeUpdate();
	}

}
