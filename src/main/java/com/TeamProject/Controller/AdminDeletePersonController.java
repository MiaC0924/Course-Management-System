package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeletePersonController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/Admin/Person/Delete/Processing")
    public String deleteCourse(@RequestParam("btnradio") String btnradio,
                               @RequestParam("id") int id,
                               @RequestParam("email") String email,
                               Model model){
        boolean flag1 = false;
        System.out.println("Want to delete: " + btnradio + " id: " + id + " email: " + email);
        if(btnradio.equals("Professor")){
            //bug !!!   adminService.deleteProfessor(id,email)  !!! //
            if(adminService.deleteProfessor(id)){
                flag1 = true;
            }else{
                flag1 = false;
            }

        }else if (btnradio.equals("Student")){
            //bug !!!   adminService.deleteStudent(id)  !!! //
            if(adminService.deleteStudent(id)){
                flag1 = true;
            }else{
                flag1 = false;
            }
        }
        if(flag1){
            System.out.println("delete success");
            model.addAttribute("msg" ,"success");
        }else{
            System.out.println("delete course fail");
            model.addAttribute("msg", "fail");
        }
        return "dashboardAdm";
    }

}
