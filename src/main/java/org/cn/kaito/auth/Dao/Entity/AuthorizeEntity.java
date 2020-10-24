package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "authorize")
public class AuthorizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorizeID;

    private String projectID;

    private String adminID;

    private Date authorizeDate;

    private String operator;
}
