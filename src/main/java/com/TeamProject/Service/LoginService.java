package com.TeamProject.Service;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.ProfessorDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminDao adminDao;
    private StudentDao studentDao;
    private ProfessorDao professorDao;


    public boolean adminLogin(String username,String password){
        if(username == adminDao.findAdminByEmail(username).getUsername() && adminDao.findAdminByEmail(username).checkPassword(password)){
            return true;
        }
        return false;
    }

    public boolean studentLogin(String username,String password){
        if(username == studentDao.findStudentByEmail(username).getUsername() && studentDao.findStudentByEmail(username).checkPassword(password)){
            return true;
        }
        return false;
    }

    public boolean professorLogin(String username,String password){
        if(username == professorDao.findProfByEmail(username).getUsername() && professorDao.findProfByEmail(username).checkPassword(password)){
            return true;
        }
        return false;
    }


}
