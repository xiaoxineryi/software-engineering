package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "operation")
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationID;

    private String operatorID;

    private String operationName;

    private String projectID;

    private Date time;
}
