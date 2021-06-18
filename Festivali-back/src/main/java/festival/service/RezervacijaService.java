package festival.service;

import java.util.List;
import java.util.Optional;

import festival.model.Rezervacija;

public interface RezervacijaService {
	
	List<Rezervacija>getAll();
	
	Optional<Rezervacija> findOne(Long id);
	
	Rezervacija save (Rezervacija rezervacija);

}
