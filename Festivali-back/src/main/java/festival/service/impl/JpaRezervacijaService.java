package festival.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festival.model.Rezervacija;
import festival.repository.RezervacijaRepository;
import festival.service.RezervacijaService;
@Service
public class JpaRezervacijaService implements RezervacijaService {
	
	@Autowired
	RezervacijaRepository rezervacijaRepository;

	@Override
	public List<Rezervacija> getAll() {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findAll();
	}

	@Override
	public Optional<Rezervacija> findOne(Long id) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findById(id);
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.save(rezervacija);
	}

}
