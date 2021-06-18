package festival.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import festival.model.Festival;
import festival.model.Rezervacija;

public interface FestivalService {
	
	Page<Festival>pretraga (Long mestoId, String naziv, int pageNum);
	
	Page<Festival> findAll(int page);
	
	Optional<Festival> findOne(Long id);
	
	Festival save (Festival festival);
	
	Festival update (Festival festival);
	
	Festival delete (Long id);
	
	Festival rezervacija (Long id, Rezervacija rezervisi);
	
	

}
