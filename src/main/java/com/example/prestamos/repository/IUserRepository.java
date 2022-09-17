package com.example.prestamos.repository;


import com.example.prestamos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1 and u.password = ?2")
    ArrayList<User> validaCredenciales(String usuario,String password);

    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1")
    ArrayList<User> existeCorreo(String correoElectronico);


    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1")
    User finByUserName(String correoElectronico);

}
