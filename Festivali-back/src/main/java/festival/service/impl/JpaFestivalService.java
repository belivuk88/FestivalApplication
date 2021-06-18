package festival.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import festival.model.Festival;
import festival.model.Rezervacija;
import festival.repository.FestivalRepository;
import festival.service.FestivalService;
import festival.service.RezervacijaService;
@Service
public class JpaFestivalService implements FestivalService {
	
	@Autowired
	FestivalRepository festivalRepository;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	FestivalService festivalService;

	@Override
	public Page<Festival> pretraga(Long mestoId, String naziv, int page) {
		// TODO Auto-generated method stub
		if(naziv !=null) {
			naziv = "%" + naziv + "%";
		}
		return festivalRepository.pretraga(mestoId, naziv, PageRequest.of(page, 2));
	}

	@Override
	public Page<Festival> findAll(int page) {
		// TODO Auto-generated method stub
		return festivalRepository.findAll(PageRequest.of(page, 2));
	}

	@Override
	public Optional<Festival> findOne(Long id) {
		// TODO Auto-generated method stub
		return festivalRepository.findById(id);
	}

	@Override
	public Festival save(Festival festival) {
		// TODO Auto-generated method stub
		return festivalRepository.save(festival);
	}

	@Override
	public Festival update(Festival festival) {
		// TODO Auto-generated method stub
		return festivalRepository.save(festival);
	}

	@Override
	public Festival delete(Long id) {
		Festival festival = findOne(id).get();
		if(festival !=null) {
			festival.getMesto().getFestivali().remove(festival);
			festival.setMesto(null);
			festivalRepository.delete(festival);
			return festival;
		}
		
		return null;
	}

	@Override
	public Festival rezervacija(Long id, Rezervacija rezervisi) {
		
		Festival festival = festivalRepository.getOne(id);
		
		if(festival.getDostupneKarte() > 0) {
			rezervisi.setUkupnaCena(festival.getCena() * rezervisi.getKupljeneKarte());
			rezervisi.setFestival(festival);
			rezervacijaService.save(rezervisi);
			
			festival.setDostupneKarte(festival.getDostupneKarte() - rezervisi.getKupljeneKarte());
			festivalService.update(festival);
			
			return festivalRepository.save(festival);
			
		}
		
		return null;
	}

}
