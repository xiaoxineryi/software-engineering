package org.cn.kaito.auth.Service.ServiceImpl;

import org.aspectj.util.FileUtil;
import org.cn.kaito.auth.Service.WorkExecuteService;
import org.cn.kaito.auth.Utils.DateStringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Service
@PropertySource(value = "classpath:application-devConfig.yml")
@ConfigurationProperties(prefix = "project-info")
public class WorkExecuteServiceImpl implements WorkExecuteService {
    @Value("${basePath}")
    private String basePath;

    public String getBasePath(){return basePath;}

    @Override
    public void init(String basePath, String projectName) throws IOException {
        String path = basePath+projectName+"/"+projectName+".txt";
        String backPath = basePath+projectName+"/"+projectName+".backup";
        System.out.println(path);
        File file = getFile(path);
        File fileBack = getFile(backPath);
    }

    @Override
    public void init(String projectName) throws IOException {
        init(basePath,projectName);
    }

    @Override
    public void save(String basePath, String projectName,String userName,String userID,String type) {

    }

    @Override
    public void save(String projectName,String userName,String userID,String type) throws IOException {
        String path = basePath+projectName+"/"+projectName+".txt";
        String backPath = basePath+projectName+"/"+projectName+".backup";

        File file = getFile(path);
        File fileBack = getFile(backPath);
        FileUtil.copyFile(file,fileBack);
        BufferedWriter bf = new BufferedWriter(new FileWriter(fileBack,true));
        bf.write(DateStringUtil.Date2String(new Date())+"     "+"编号为"+userID+"的用户"+userName
        +"执行了"+type+"类任务");
        bf.flush();
        bf.close();

    }

    @Override
    public void commit(String basePath, String projectName) throws IOException {
        String path = basePath+projectName+"/"+projectName+".txt";
        String backPath = basePath+projectName+"/"+projectName+".backup";

        File file = getFile(path);
        File fileBack = getFile(backPath);
        FileUtil.copyFile(fileBack,file);
    }

    @Override
    public void commit(String projectName) throws IOException {
        commit(basePath,projectName);
    }

    @Override
    public void cancel(String basePath, String projectName) {

    }

    @Override
    public void cancel(String projectName) {

    }
    private File getFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }
}
