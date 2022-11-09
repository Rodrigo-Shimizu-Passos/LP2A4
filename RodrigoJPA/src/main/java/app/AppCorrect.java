package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.*;
public class AppCorrect {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		
		EntityManager em = emf.createEntityManager();
		
		List<Jogo> jogos = em.createQuery("FROM Jogo j join fetch j.estudio", Jogo.class).getResultList();

	}

}
