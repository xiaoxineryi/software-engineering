package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SubTask")
public class SubTaskEntity {
    @Id
    private String taskID;

    private int taskPosition;

    private Integer typeID;

    private String status;

    private String executor;

    private String projectID;
}
