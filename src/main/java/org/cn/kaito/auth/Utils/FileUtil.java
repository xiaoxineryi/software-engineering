package org.cn.kaito.auth.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


public class FileUtil {
    public static String getFileSrc(String projectName){
        return "localhost:8080/file/download/"+projectName;
    }
}
