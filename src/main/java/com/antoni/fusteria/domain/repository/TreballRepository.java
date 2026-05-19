package com.antoni.fusteria.domain.repository;

import com.antoni.fusteria.domain.model.Treball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreballRepository extends JpaRepository<Treball, Long> {

    List<Treball> findByClientId(Long clientId);
}
