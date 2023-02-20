package com.jomapeju.model.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jomapeju.orm.entity.Tarea;

public interface TareasService {
	
	public List<Tarea> getTareas();
	
	public Tarea getTarea(Long id) throws NotFoundException;
	
	public Tarea saveTarea(Tarea tarea);
	
	public void deleteTarea(Long id) throws NotFoundException;

}
