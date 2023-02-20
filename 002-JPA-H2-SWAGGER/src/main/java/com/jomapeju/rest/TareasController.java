package com.jomapeju.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jomapeju.model.service.TareasService;
import com.jomapeju.orm.entity.Tarea;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping (value="/tareas")
@Lazy(true)
@Api(value = "Gestion de tareas", tags = { "Tareas" })
public class TareasController {
	
	@Autowired
	private TareasService tareasService;
	
//  http://localhost:8080/tareas-app/tareas/listado
	
	@GetMapping(value="/listado")
	@ApiOperation(value = "Listado de tareas", notes = "Notas de Listado de tareas", responseContainer = "List", response = Tarea.class)
	@ApiResponses(value = {})
	public List<Tarea> getTareas() {
		return tareasService.getTareas();
	}
	
	
//  http://localhost:8080/tareas-app/tareas/1/detail
	
	@GetMapping(value="/{id}/detail")
	@ApiOperation(value = "Obtiene el detalle de una tarea", notes = "Notas de Obtiene el detalle de una tarea", responseContainer = "Tarea", response = Tarea.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "Identificador de la tarea", required = true, paramType = "path", example="1"),
	})	
	public Tarea getDetalleTarea(@PathVariable(name = "id") final Long idTarea) {
		try {
			return tareasService.getTarea(idTarea);
		} catch (NotFoundException e) {
			return null;
		}
	}
	
	
//  http://localhost:8080/tareas-app/tareas/save
	
	@PostMapping(value="/save", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Crea una tarea", notes = "Nota de Crea una tarea", response = Tarea.class)
	@ResponseBody
	public Tarea saveTarea(@RequestBody Tarea tarea) {
		return tareasService.saveTarea(tarea);
	}
	
//  http://localhost:8080/tareas-app/tareas/saveEntity
	
	@PostMapping(value="/saveEntity", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Crea una tarea con valor devuelto ResponseEntity", notes = "Nota de Crea una tarea con valor devuelto ResponseEntity", response = ResponseEntity.class)
	public ResponseEntity<Tarea> saveTareaResponseEntity(@RequestBody Tarea tarea) {
		tarea =  tareasService.saveTarea(tarea);
		return new ResponseEntity<>(tarea, HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping(value="/{id}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Elimina una tarea con valor devuelto ResponseEntity", notes = "Nota de Elimina una tarea con valor devuelto ResponseEntity", response = ResponseEntity.class)
	public ResponseEntity<Tarea> deleteTareaResponseEntity(@PathVariable(name = "id") final Long idTarea) {
		try {
			tareasService.deleteTarea(idTarea);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Tarea>(HttpStatus.OK);
	}
	

}
