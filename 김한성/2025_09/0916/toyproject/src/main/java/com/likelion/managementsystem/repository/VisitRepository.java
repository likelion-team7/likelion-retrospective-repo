package com.likelion.managementsystem.repository;


import com.likelion.managementsystem.domain.Visit;

import java.util.List;

public interface VisitRepository {

    void save(String name, String phoneNumber);

    List<Visit> findAll();
}
