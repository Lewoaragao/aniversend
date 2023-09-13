package com.aniversend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aniversend.models.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    List<People> findByBirthday(Date birthday);
    People findByEmail(String email);
}

