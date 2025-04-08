package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import com.hibernate.model.Serie;
import com.hibernate.util.HibernateUtil;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

public class SerieDAO {
	
	//Inserción
	public void insertSerie(Serie s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
	}
		
	//Actualización
	public void updateSerie(Serie s) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
	}
		
	//Borrado
	public void deleteSerie(int id) {
		Transaction transaction = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			s = session.get(Serie.class, id);
			session.remove(s);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
	}
		
	//Selección simple
	public Serie selectSerieById(int id) {
		Transaction transaction = null;
		Serie s = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			s = session.get(Serie.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
		return s;
	}
		
	//Selección múltiple
	public List<Serie> selectAllSeries() {
		Transaction transaction = null;
		List<Serie> series = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			series = session.createQuery("FROM Serie", Serie.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
		return series;
	}
}