package com.jomapeju.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jomapeju.model.service.TareasService;
import com.jomapeju.orm.entity.Tarea;
import com.jomapeju.orm.repository.TareasRepository;

@Service
@Transactional(readOnly = true)
public class TareasServiceImpl implements TareasService{
	
	@Autowired
	private TareasRepository tareasRepository;

	@Override
	public List<Tarea> getTareas() {		
		return (List<Tarea>) tareasRepository.findAll();
	}

	@Override
	public Tarea getTarea(Long id) throws NotFoundException {
		Optional<Tarea> optionalDev = tareasRepository.findById(id);

		if (optionalDev.isEmpty()) {
			throw new NotFoundException();
		}
		return optionalDev.get();
	}

	@Override
	public Tarea saveTarea(Tarea tarea) {
		return tareasRepository.save(tarea);
	}

}
