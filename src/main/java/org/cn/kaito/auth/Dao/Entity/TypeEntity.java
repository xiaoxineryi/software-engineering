package org.cn.kaito.auth.Dao.Entity;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TaskType")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeID;

    private String type_name;
}
