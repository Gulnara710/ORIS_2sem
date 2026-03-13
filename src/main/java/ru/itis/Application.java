package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.persistence.entity.UserEntity;
import ru.itis.persistence.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        UserService userService = context.getBean(UserService.class);

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setName("Alice");
        user.setBirthDate(LocalDate.now().minusYears(20));

        userService.saveUser(user);
        System.out.println("Создан: " + user.getId());

        userService.findById(user.getId())
                .ifPresent(u -> System.out.println("Получен: " + u.getName()));

        user.setName("Ann");
        userService.update(user);

        userService.deleteById(user.getId());
        System.out.println("Удалён");
    }
}
