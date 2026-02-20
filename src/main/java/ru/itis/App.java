package ru.itis;

import ru.itis.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.service.UserService;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.saveUser("Alice");
        userService.updateUser(1L, "Ann");
        userService.getUserById(1L);
        userService.deleteUser(1L);
    }
}
