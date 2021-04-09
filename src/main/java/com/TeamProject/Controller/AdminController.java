//package com.TeamProject.Controller;
//
//import com.TeamProject.Dao.AdminDao;
//import com.TeamProject.Person.Admin;
//import com.TeamProject.Person.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class AdminController {
//    @Autowired
//    private AdminDao adminDao;
//    private AdminRepository adminRepository;
//
//    public AdminController(AdminRepository adr){
//        adminRepository = adr;
//    }
//
//    @GetMapping
//    public List<Admin> getAll(){
//        return adminRepository.findAll();
//    }
//
//    @PostMapping
//    public void insert(Admin admin){
//            adminRepository.insert(admin);
//    }
//
//    @PutMapping
//    public void update(Admin admin){
//            adminRepository.save(admin);
//    }
//}
