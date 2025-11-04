package com.antoni.fusteria.repository;

import com.antoni.fusteria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
