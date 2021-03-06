package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminDeleteCourseController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/Admin/Courses/Delete/Processing")
    public String deleteCourse(@RequestParam("id") int id,
                               @RequestParam("major") String major,
                               @RequestParam("code") int code,
                               @RequestParam("section") char section,
                               Model model){
        System.out.println("Want to delete: Major: " + major + " Code: " + code + " section: " + section );
        if(adminService.deleteCourse(id, major,code,section)){
                System.out.println("delete success");
                model.addAttribute("msg" ,"success");
        }else{
            System.out.println("delete course fail");
            model.addAttribute("msg", "fail");
        }
        return "dashboardAdm";
    }

}
