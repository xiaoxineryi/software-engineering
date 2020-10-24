package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.time.DateTimeException;

@Data
@Entity
@Table(name = "Project")
public class ProjectEntity {
    @Id
    private String projectID;

    private String projectName;

    private String creator;

    private String status;

    private Date createDate;

    private Date finishDate;

    private String path;
}
