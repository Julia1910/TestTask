package com.packing.dao;

import com.packing.model.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlineRepository extends JpaRepository<Orderline, Integer> {
}
