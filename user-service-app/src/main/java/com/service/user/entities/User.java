package com.service.user.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer userId;

    private  String userName;
    private  String userEmail;
    private  String userPassword;
    private  String userAbout;
    //other user information

}
