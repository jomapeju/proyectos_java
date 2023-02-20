package com.jomapeju.orm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jomapeju.orm.entity.Tarea;

@Repository
public interface TareasRepository extends CrudRepository<Tarea, Long>{

}
