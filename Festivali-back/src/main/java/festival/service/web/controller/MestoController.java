package festival.service.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import festival.model.Mesto;
import festival.service.MestoService;
import festival.service.support.MestoToMestoDto;
import festival.service.web.dto.MestoDTO;

@RestController
@RequestMapping("/api/mesta")
public class MestoController {
	
	@Autowired
	private MestoService mestoService;
	
	@Autowired
	private MestoToMestoDto toDto;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<MestoDTO>> getAll(
			@RequestParam(required = false) String grad){
		
		List<Mesto>mesta = mestoService.findAll();
		return new ResponseEntity<>(toDto.convert(mesta), HttpStatus.OK);
	}

}
