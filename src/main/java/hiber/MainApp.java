package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car ceed = new Car("polo");
      Car solaris = new Car("solaris");
      Car polo = new Car("polo");
      Car logan = new Car("logan");

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User1", "Lastname1", "user1@mail.ru");
      User user3 = new User("User1", "Lastname1", "user1@mail.ru");
      User user4 = new User("User1", "Lastname1", "user1@mail.ru");

      user1.setCar(ceed);
      user2.setCar(solaris);
      user3.setCar(polo);
      user4.setCar(logan);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Series = "+user.getCar().getSeries());

      }
      User user = userService.userBySeries(2);
      System.out.println(user.toString());

      List<User> usersWithModel = userService.usersByModel("polo");
      System.out.println(usersWithModel.toString());

      context.close();
   }
}
