package com.user_manager_v1.repository;

import com.user_manager_v1.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email FROM utenti WHERE email = :email", nativeQuery = true)
    List<String> checkUserEmail(@Param("email") String email);

    @Query(value = "SELECT password FROM utenti WHERE email = :email", nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM utenti WHERE email = :email", nativeQuery = true)
    User GetUserDetailsByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO utenti(nome, cognome, email, password) VALUES (:nome, :cognome, :email, :password)", nativeQuery = true)

    int registerNewUser(@Param("nome") String nome,
                        @Param("cognome") String cognome,
                        @Param("email") String email,
                        @Param("password") String password);
}