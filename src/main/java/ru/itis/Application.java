package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.service.UserService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        UserService userService = context.getBean(UserService.class);

        userService.saveUser("Alice");
        userService.updateUser(1L, "Ann");
        userService.getUserById(1L);
        userService.deleteUser(1L);

    }
}
