package web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import web.modelo.Curso;

@Repository
public class CursoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Curso curso) {
		manager.persist(curso);
	}

	public void altera(Curso curso) {
		manager.merge(curso);
	}

	public List<Curso> lista() {
		return manager.createQuery("select c from Curso c order by c.id desc", Curso.class).getResultList();
	}

	public List<Curso> buscaPorNome(String nome) {
		return manager.createQuery("select c from Curso c where c.nome = :nome", Curso.class).setParameter("nome", nome)
				.getResultList();
	}

	public String buscaNomePorId(Long id) {
		return manager.createQuery("select c.nome from Curso c where c.id = :id", String.class).setParameter("id", id)
				.getSingleResult();
	}

	public Curso buscaPorId(Long id) {
		return manager.find(Curso.class, id);
	}

	public void remove(Long id) {
		manager.createQuery("delete from Curso c where c.id = :id").setParameter("id", id).executeUpdate();
	}

}
