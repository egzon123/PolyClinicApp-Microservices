package com.polyclinic.pacientservice.repositories;

import com.polyclinic.pacientservice.models.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacientRepository extends CrudRepository<Pacient , Integer> {

        Pacient findPacientById(int id);
}
