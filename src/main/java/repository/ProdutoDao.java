package repository;


import model.Produto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import Control.HibernateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements Modelo <Produto>, Serializable {

    @Override
    public void incluir(Produto obj) {
        Session session = null;
        try{
            session = Control.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        }catch(HibernateException e){
            if(session != null && session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null) session.close();
        }
    }

    @Override
    public void alterar(Produto obj) {
        Session session = null;
        try{
            session = Control.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        }catch(HibernateException e){
            if(session != null && session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null) session.close();
        }
    }

    @Override
    public void exclui(Produto obj) {

        Session session = null;
        try{
            session = Control.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        }catch(HibernateException e){
            if(session != null && session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null) session.close();
        }
    }

    @Override
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();

        Session session = null;
        try {
            session = Control.HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery("from Produto");
            //query.setMaxResults(11);
            lista = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session instanceof Session) {
                session.close();
            }
        }

        return lista;
    }

    public static void main(String[] args) {

        Long iduser = 1L;

        ProdutoDao empresaDao = new ProdutoDao();

        List<Produto> Produto = empresaDao.listar();

        for (Produto produto : Produto) {
            System.out.println("Produto: " + produto.getDescricao());
        }

    }

}
