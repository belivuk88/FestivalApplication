package festival.service;

import java.util.List;
import java.util.Optional;

import festival.model.Mesto;

public interface MestoService {
	
	List<Mesto>findAll();
	
	Optional<Mesto> findOne (Long id);
	
	Mesto save (Mesto mesto);

}
