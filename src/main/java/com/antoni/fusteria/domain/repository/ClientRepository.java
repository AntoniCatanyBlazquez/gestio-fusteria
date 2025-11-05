package com.antoni.fusteria.domain.repository;

import com.antoni.fusteria.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
