package com.polyclinic.staffservice.controller;

import com.polyclinic.staffservice.model.Staff;
import com.polyclinic.staffservice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/staff")
    public List<Staff> getAllStaff(){
        return staffService.getAllStaff();
    }
}
