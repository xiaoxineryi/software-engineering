package org.cn.kaito.auth.Dao.Repository;

import org.cn.kaito.auth.Dao.Entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,Integer> {

    @Query(value = "select t.type_name from TypeEntity t where t.typeID = ?1")
    String getTypeByID(int id);

    @Query(value = "select count(t) from TypeEntity  t")
    int getCount();


}
