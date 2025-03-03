package com.example.GreetingController.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GreetingController.model.Greeting;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
