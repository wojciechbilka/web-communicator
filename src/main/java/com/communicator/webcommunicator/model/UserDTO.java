package com.communicator.webcommunicator.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Size(min = 3, max = 10, message = "Username length must contain in range 3-10 letters.")
    private String userName;

    private String password;
    private String confirmPassword;

    @Email(message = "Wrong email format.")
    @NotEmpty(message = "Field cannot be empty.")
    private String email;

    @Email(message = "Wrong email format.")
    @NotEmpty(message = "Field cannot be empty.")
    private String confirmEmail;

    public User createUser() {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setEmail(email);
        user.setActive(true);
        user.setRoles("ROLE_USER");
        return user;
    }
}
