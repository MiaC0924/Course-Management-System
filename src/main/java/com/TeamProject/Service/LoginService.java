package com.TeamProject.Service;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.ProfessorDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LoginService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;


    public boolean adminLogin(String username,String password){
        if(adminDao.findAdminByEmail(username) == null){
            return false;
        }
        if(adminDao.findAdminByEmail(username).getUsername() != null && adminDao.findAdminByEmail(username).checkPassword(password)){
            return true;
        }
        return false;
    }

    public boolean studentLogin(String username,String password){
        if(studentDao.findStudentByEmail(username) == null){
            return false;
        }
        if(studentDao.findStudentByEmail(username).getUsername() != null && studentDao.findStudentByEmail(username).checkPassword(password)){
            return true;
        }
        return false;
    }

    public boolean professorLogin(String username,String password){
        if(professorDao.findProfByEmail(username) == null){
            return false;
        }
        System.out.println("here");
//        if(professorDao.findProfByEmail(username).getUsername() != null && professorDao.findProfByEmail(username).checkPassword(password)){
//            return true;
//        }
        return false;
    }


}
