package com.packing.service;

import com.packing.dao.CaseRepository;
import com.packing.model.Case;
import com.packing.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class CaseService implements Service<Case> {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public void add(Case entity) {
        caseRepository.save(entity);
    }

    @Override
    public List<Case> getAll() {
        return caseRepository.findAll();
    }

    @Override
    public Case findById(Integer id) {
        return caseRepository.findById(id).orElseThrow();
    }

    public void remove(Integer id) {
        caseRepository.delete(findById(id));
    }

}
