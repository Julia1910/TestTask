package com.packing.dao;

import com.packing.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
    Optional<Case> findById(Integer id);
}
