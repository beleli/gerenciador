package com.gerenciador.dao;

import com.gerenciador.modelo.Produto;
import com.gerenciador.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProdutoDAO {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void salvar(Produto produto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Produto> listar() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Produto", Produto.class).list();
        }
    }

    public void atualizar(Produto produto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Produto p = session.get(Produto.class, id);
            if (p != null) {
                session.delete(p);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Produto buscarPorId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Produto.class, id);
        }
    }
}

