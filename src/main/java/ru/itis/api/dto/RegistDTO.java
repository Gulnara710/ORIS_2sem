package ru.itis.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistDTO {

    @NotBlank(message = "Заполните почту")
    @Email(message = "Неверный формат email")
    private String email;

    @NotBlank(message = "Заполните поле пароль")
    @Size(min = 6, message = "В пароле должно быть от 6 символов")
    private String password;

    @NotBlank(message = "Заполните имя")
    private String name;

    private LocalDate birthDate;
}
