package com.TeamProject.DBSeeder;

import com.TeamProject.Person.Admin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class AdminSeeder implements CommandLineRunner {

    private AdminRepository adminRepository;

    public AdminSeeder(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Admin adm = new Admin(
                "Admin",
                "Female",
                "admin@fack.com",
                LocalDate.now(),
                "Admin"
        );

        Admin john = new Admin(
                "John",
                "Male",
                "alexx@fack.com",
                LocalDate.now(),
                "Admin"
        );

        adminRepository.deleteAll();

        ArrayList<Admin> adminList = new ArrayList<>();
        adminList.add(adm);
        adminList.add(john);
        adminRepository.saveAll(adminList);
    }
}
