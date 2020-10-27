package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Entrust")
public class EntrustEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entrustID;

    private String status;

    private Date entrustStartDate;

    private Date entrustEndDate;

    private String subTask;

    private String entrustWorker;
}
