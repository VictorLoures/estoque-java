package com.estoque.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class NativeScriptService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void execute(String sql){
        entityManager.createNativeQuery(sql).executeUpdate();
    }

}
