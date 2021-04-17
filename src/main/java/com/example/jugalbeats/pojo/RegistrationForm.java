package com.example.jugalbeats.pojo;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    String gender;
    
    @NotNull
    String username;
    
    @NotNull
    String mobile;
    
    @NotNull
    String profession;

    @NotNull
    String userType;
    
    @NotNull
    String artType;

}
