package com.antoni.fusteria.domain.repository;

import com.antoni.fusteria.domain.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("SELECT COUNT(f) FROM Factura f WHERE YEAR(f.dataEmisio) = :any")
    long countByAny(@Param("any") int any);
}
