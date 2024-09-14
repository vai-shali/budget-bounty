package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN 1 ELSE 0 END FROM User u WHERE u.email = :email")
    int existsByEmail(@Param("email") String email);
	
	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN 1 ELSE 0 END FROM User u WHERE u.username = :username")
    int existsByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

