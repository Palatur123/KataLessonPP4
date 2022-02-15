package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Gendalf", "Seriy", "Gendalf@mag.ru");
      User user2 = new User("Saruman", "Velikiy", "Saruman@mag.ru");
      User user3 = new User("Senya", "Ganji", "ganja@hobbit.ru");
      User user4 = new User("Fedya", "Sumkin", "Fedya@hobbit.ru");

      Car car1 = new Car("Hz1", 1);
      Car car2 = new Car("Hz2", 21);
      Car car3 = new Car("Hz3", 31);
      Car car4 = new Car("Hz4", 41);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1.");
      }

      System.out.println(userService.getUserByCar("Hz2", 21));
      System.out.println("2.");

      try {
         User notFoundUser = userService.getUserByCar("Hz4", 41);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3.");
      }

      context.close();
   }
}
