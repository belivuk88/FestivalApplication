package festival.service.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import festival.model.Rezervacija;
import festival.service.RezervacijaService;
import festival.service.support.RezervacijaToRezervacijaDto;
import festival.service.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaToRezervacijaDto toRezervacijaDto;
	
	@GetMapping ResponseEntity<List<RezervacijaDTO>>getAll(){
		List<Rezervacija> rezervacije = rezervacijaService.getAll();
		
		return new ResponseEntity<>(toRezervacijaDto.convert(rezervacije), HttpStatus.OK);
}


}
