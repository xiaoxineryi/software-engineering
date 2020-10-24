package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@PreAuthorize(value = "hasPermission('project','read')")
public class ProjectController extends BaseController{
    @Autowired
    LogUtil logUtil;

    @GetMapping("/read")
    public void readProject(){
        System.out.println("nice");
        logUtil.INFO("A","hello");
    }
}
