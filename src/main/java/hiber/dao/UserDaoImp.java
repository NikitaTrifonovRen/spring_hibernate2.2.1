package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   @Autowired

   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User userBySeries(int series){
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where carSeries like :series");
      int s = series;
      query.setParameter("series",s);
      return query.getSingleResult();

   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> usersByModel(String model) {
      TypedQuery<Integer> query=sessionFactory.getCurrentSession().createQuery("select series from Car where model like :model");
      String m = model;
      List<Integer> seriesList = query.setParameter("model",m).getResultList();
      List<User> usersModel = new ArrayList<>();
      for (Integer integer: seriesList){
         usersModel.add((User) sessionFactory.getCurrentSession().createQuery("from User where carSeries like :series").setParameter("series",integer).getSingleResult());

      }
      return usersModel;
   }

}
