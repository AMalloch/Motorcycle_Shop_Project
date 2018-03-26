package db;

//import models.Accessory;
//import models.Bike;
import org.hibernate.Criteria;
import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerDBHelper {

    //    private static Transaction transaction;
    private static Session session;

    public static <T> List<T> getAvailableStock(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> availableStock = null;
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.gt("quantity", 0));
        availableStock = DBHelper.getList(cr);
        return availableStock;
    }

}