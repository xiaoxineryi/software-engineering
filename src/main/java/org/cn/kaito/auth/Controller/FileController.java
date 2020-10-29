package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.FileService;
import org.cn.kaito.auth.Service.ProjectService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/download/{fileName}")
    public void download(HttpServletResponse response,@PathVariable(name = "fileName")String fileName ) throws Exception {
        if (!projectService.validate(getUid(),fileName)){
            throw new CustomerException(StatusEnum.USER_CANT_WORK);
        }
        // 文件地址，真实环境是存放在数据库中的
        File file = new File("./src/main/resources/project/"+fileName+"/"+fileName+".txt");
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + fileName+".txt");
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

}
