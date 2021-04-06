package com.TeamProject.Controller;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    private AdminRepository adminRepository;

    @Autowired
    private AdminDao adminDao;

    public AdminController(AdminRepository adr){
        adminRepository = adr;
    }

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }
}
