package db;

import models.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        {
            clothes.add(clothing);
        }
        return clothes;
    }

    public static Customer findCustomerByUsername(String username){
        session = HibernateUtil.getSessionFactory().openSession();
        Customer user = null;
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("username", username));
        user = getUnique(criteria);
        return user;
    }

    public static void addSaleToShopCash(Double saleTotal, Shop shop) {
        Double newCash = (shop.getTotalCash() + saleTotal);
        shop.setTotalCash(newCash);
        DBHelper.update(shop);
    }

// /  public static Set<StockItem> findBasketItems(Basket basket){
//  session = HibernateUtil.getSessionFactory().openSession();
//  session.refresh(basket);
//   Hibernate.initialize(basket.getStockItems());
//  session.close();
//  return basket.getStockItems();
//

//    public static void addToBasket(StockItem item, int ppQuantity, Customer customer){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Basket basket = find(customer.getBasket().getId(), Basket.class);
//        basket.addItem(item, ppQuantity);
//        DBHelper.update(basket);
//    }
//
//    public static void deleteFromBasket(StockItem item, Customer customer) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        Basket basket = find(customer.getBasket().getId(), Basket.class);
//        basket.deleteItem(item);
//        DBHelper.update(basket);
//    }
//
//    public static Double calculateTotalBasketPrice(Set<StockItem> basketItems) {
//        Double totalPrice = 0.00;
//        for (StockItem basketItem : basketItems){
//            totalPrice += (basketItem.getPrice() * basketItem.getPendingPurchaseQuantity());
//        }
//        return totalPrice;
//    }

}