package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "Entrust")
public class EntrustEntity {
    @Id
    private int entrustID;

    private String status;

    private Date entrustStartDate;

    private Date entrustEndDate;

    private String subTask;

    private String entrustWorker;
}
