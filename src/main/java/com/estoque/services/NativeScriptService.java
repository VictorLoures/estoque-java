package com.estoque.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NativeScriptService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void execute(String sql){
        entityManager.createNativeQuery(sql).executeUpdate();
    }

}
