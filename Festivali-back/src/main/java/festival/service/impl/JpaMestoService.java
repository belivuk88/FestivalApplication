package festival.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festival.model.Mesto;
import festival.repository.MestoRepository;
import festival.service.MestoService;
@Service
public class JpaMestoService implements MestoService {
	
	@Autowired
	MestoRepository mestoRepository;

	@Override
	public List<Mesto> findAll() {
		// TODO Auto-generated method stub
		return mestoRepository.findAll();
	}

	@Override
	public Optional<Mesto> findOne(Long id) {
		// TODO Auto-generated method stub
		return mestoRepository.findById(id);
	}

	@Override
	public Mesto save(Mesto mesto) {
		// TODO Auto-generated method stub
		return mestoRepository.save(mesto);
	}

}
