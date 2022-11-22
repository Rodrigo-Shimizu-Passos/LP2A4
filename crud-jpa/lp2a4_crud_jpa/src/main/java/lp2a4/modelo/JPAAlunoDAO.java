package lp2a4.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAAlunoDAO implements AlunoDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	private EntityManager em = emf.createEntityManager();

	@Override
	public boolean create(AlunoPOJO aluno) {
		try {
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public AlunoPOJO retrieve(String matricula) {
		AlunoPOJO aluno = em.find(AlunoPOJO.class, matricula);
		return aluno;
	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		try {
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(String matricula) {
		AlunoPOJO aluno = new AlunoPOJO();
		aluno.setMatricula(matricula);
		
		try {
		em.getTransaction().begin();
		em.remove(em.merge(aluno));
		em.getTransaction().commit();
		return true;
		} catch(Exception e) {
			return false;
		}
	}	
}
