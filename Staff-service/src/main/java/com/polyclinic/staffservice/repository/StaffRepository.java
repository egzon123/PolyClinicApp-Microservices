package com.polyclinic.staffservice.repository;

import com.polyclinic.staffservice.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff,Integer > {

    Staff findStaffById(int id);
}
