package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("BMV", 345);
      Car car2 = new Car("Volvo", 745);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      User user5 = new User("User5", "Lastname5", "user5@mail.ru", car1);
      User user6 = new User("User6", "Lastname6", "user6@mail.ru", car2);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5, user5.getCar());
      userService.add(user6, user6.getCar());



      System.out.println(userService.getUserByCar("Volvo", 745));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar());
         System.out.println();
      }

      context.close();
   }
}
