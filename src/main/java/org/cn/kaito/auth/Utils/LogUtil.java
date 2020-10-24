package org.cn.kaito.auth.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@PropertySource(value = "classpath:application-devConfig.yml")
@ConfigurationProperties(prefix = "log-info")
public  class LogUtil {
    @Value("${basePath}")
    private String basePath;

    private File getFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    public void INFO(String path,String projectName,String message) throws IOException {
        String fileName  = path+projectName+".txt";
        System.out.println(fileName);
        File file = getFile(fileName);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String d = formatter.format(date);
            String space = "      ";
            BufferedWriter bf = new BufferedWriter(new FileWriter(file,true));
            bf.write("INFO"+space);
            bf.write(d+space);
            bf.write(message);
            bf.newLine();
            bf.flush();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void INFO(String projectName ,String message) throws IOException {
        INFO(basePath,projectName,message);
    }

    public void WARN(String path,String projectName,String message){
        String fileName  = path+projectName+".txt";
        System.out.println(fileName);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String d = formatter.format(date);
            String space = "      ";
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName,true));
            bf.write("WARN"+space);
            bf.write(d+space);
            bf.write(message);
            bf.newLine();
            bf.flush();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void WARN(String projectName,String message){
        WARN(basePath,projectName,message);
    }

    public void ERROR(String path,String projectName,String message){
        String fileName  = path+projectName+".txt";
        System.out.println(fileName);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String d = formatter.format(date);
            String space = "      ";
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName,true));
            bf.write("ERROR"+space);
            bf.write(d+space);
            bf.write(message);
            bf.newLine();
            bf.flush();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ERROR(String projectName,String message){
        ERROR(basePath,projectName,message);
    }
}
