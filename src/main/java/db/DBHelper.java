package db;

import models.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void saveOrUpdate(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results){
                session.delete(result);
            }
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria){
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria){
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> T find(int id, Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria criteria = session.createCriteria(classType);
        criteria.add(Restrictions.idEq(id));
        result = getUnique(criteria);
        return result;
    }

    public static <T> List<T> getAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria criteria = session.createCriteria(classType);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        results = getList(criteria);
        return results;
    }

    public static <T> List<T> getAvailableStock(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> availableStock = null;
        Criteria criteria = session.createCriteria(classType);
        criteria.add(Restrictions.gt("quantity", 0));
        availableStock = getList(criteria);
        return availableStock;
    }

    public static List<ClothingType> getClothingType(){
        ArrayList<ClothingType> clothes = new ArrayList<>();
        for (ClothingType clothing : ClothingType.values())
        { clothes.add(clothing);
        } return clothes;
    }

    public static Customer findCustomerByUsername(String username){
        session = HibernateUtil.getSessionFactory().openSession();
        Customer user = null;
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("username", username));
        user = getUnique(criteria);
        return user;
    }

//    public static List<StockItem> findItemsInBasket(Basket basket){
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<StockItem> items = null;
//        Criteria criteria = session.createCriteria(StockItem.class);
//        criteria.add(Restrictions.eq("basket", basket));
//        items = getList(criteria);
//        return items;
//    }


    public static void addToBasket(StockItem item, int ppQuanity, Customer customer, Basket basket){
        basket.addItem(item, ppQuanity);
        customer.setBasket(basket);
        DBHelper.saveOrUpdate(customer);
        DBHelper.save(basket);
    }


    public static List<StockItem> findBasketItems(int basketId){
        session = HibernateUtil.getSessionFactory().openSession();
        List<StockItem> basketItems = null;
        Criteria criteria = session.createCriteria(StockItem.class);
        criteria.add(Restrictions.eq("basket", basketId));
        basketItems = getList(criteria);
        return basketItems;
    }

//    public static long countItemsInBasket(int custId){
//        session = HibernateUtil.getSessionFactory().openSession();
//        long count = 0;
//        Criteria criteria = session.createCriteria(Basket.class);
//        criteria.add(Restrictions.eq("customerId", custId));
//        criteria.setProjection(Projections.count("customerId"));
//        count = getUnique(criteria);
//        return count;
//    }


}