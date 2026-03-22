package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
