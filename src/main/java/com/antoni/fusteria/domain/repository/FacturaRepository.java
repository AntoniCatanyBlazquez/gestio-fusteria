package com.antoni.fusteria.domain.repository;

import com.antoni.fusteria.domain.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
