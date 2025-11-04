package com.antoni.fusteria.repository;

import com.antoni.fusteria.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
