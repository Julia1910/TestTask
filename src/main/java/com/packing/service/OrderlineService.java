package com.packing.service;

import com.packing.dao.OrderlineRepository;
import com.packing.model.Orderline;
import com.packing.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class OrderlineService implements Service<Orderline> {

    @Autowired
    private OrderlineRepository orderlineRepository;

    @Override
    public void add(Orderline entity) {
        orderlineRepository.save(entity);
    }

    @Override
    public List<Orderline> getAll() {
        return orderlineRepository.findAll();
    }

    @Override
    public Orderline findById(Integer id) {
        return orderlineRepository.findById(id).orElseThrow();
    }

    @Override
    public void remove(Integer id) {
        orderlineRepository.delete(findById(id));
    }
}
