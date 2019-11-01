package com.polyclinic.staffservice.service;

import com.polyclinic.staffservice.model.Staff;
import com.polyclinic.staffservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;


    public List<Staff> getAllStaff(){
        return (List<Staff>) staffRepository.findAll();
    }

}
