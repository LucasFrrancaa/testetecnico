package repository;


import model.Pagamentos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import Control.HibernateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDao implements Modelo <Pagamentos>, Serializable {

    @Override
    public void incluir(Pagamentos obj) {

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
    public void alterar(Pagamentos obj) {

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
    public void exclui(Pagamentos obj) {

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
    public List<Pagamentos> listar() {
        List<Pagamentos> lista = new ArrayList<>();

        Session session = null;
        try {
            session = Control.HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery("from Pagamentos p JOIN FETCH p.usuario JOIN FETCH p.produto");
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

        PagamentoDao empresaDao = new PagamentoDao();

        List<Pagamentos> Pagamentos = empresaDao.listar();

        for (Pagamentos pagamentos : Pagamentos) {
            System.out.println("Pagamentos: " + pagamentos.getQuantidade());
        }

    }

}
