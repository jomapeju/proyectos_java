package com.jomapeju.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jomapeju.model.service.TareasService;
import com.jomapeju.orm.entity.Tarea;

@RestController
@RequestMapping (value="/tareas")
@Lazy(true)
public class TareasController {
	
	@Autowired
	private TareasService tareasService;
	
//  http://localhost:8080/tareas-app/tareas/listado
	
	@GetMapping(value="/listado")
	public List<Tarea> getTareas() {
		return tareasService.getTareas();
	}
	
	
//  http://localhost:8080/tareas-app/tareas/1/detail
	
	@GetMapping(value="/{id}/detail")
	public Tarea getDetalleTarea(@PathVariable(name = "id") final Long idTarea) {
		try {
			return tareasService.getTarea(idTarea);
		} catch (NotFoundException e) {
			return null;
		}
	}
	
	
//  http://localhost:8080/tareas-app/tareas/save
	
	@PostMapping(value="/save", consumes = {"application/xml"}, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Tarea saveTarea(@RequestBody Tarea tarea) {
		return tareasService.saveTarea(tarea);
	}
	
//  http://localhost:8080/tareas-app/tareas/saveEntity
	
	@PostMapping(value="/saveEntity")
	public ResponseEntity<Tarea> saveTareaResponseEntity(@RequestBody Tarea tarea) {
		tarea =  tareasService.saveTarea(tarea);
		return new ResponseEntity<>(tarea, HttpStatus.CREATED);
	}
	

}
