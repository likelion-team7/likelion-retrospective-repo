package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Visit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBVisitRepositoryTest {

    DBVisitRepository repository = new DBVisitRepository();

    @Test
    void save() {
        repository.save("kim", "010-1234-1234");
        repository.save("park", "010-2345-2345");
        repository.save("lee", "010-3456-3456");
        repository.save("chung", "010-4567-4567");
        repository.save("jo", "010-5678-5678");


    }

    @Test
    void findAll() {
        List<Visit> all = repository.findAll();
        for (Visit visit : all) {
            System.out.println(visit);
        }
    }
}