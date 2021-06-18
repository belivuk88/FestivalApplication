package festival.service.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import festival.model.Festival;
import festival.model.Rezervacija;
import festival.service.FestivalService;
import festival.service.support.FestivalDtoToFestival;
import festival.service.support.FestivalToFestivalDto;
import festival.service.support.RezervacijaDtoToRezervacija;
import festival.service.web.dto.FestivalDTO;
import festival.service.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/festivali")
public class FestivalController {
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private FestivalDtoToFestival toFestival;
	
	@Autowired
	private FestivalToFestivalDto toDto;
	
	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<FestivalDTO>> get(@RequestParam(value = "mestoId", required = false) Long mestoId,
		@RequestParam(value = "naziv", required = false) String naziv,
		@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		
		Page<Festival> page = null;
		
		if(mestoId !=null || naziv !=null) {
			page = festivalService.pretraga(mestoId, naziv, pageNo);
			
		}else
			page = festivalService.findAll(pageNo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<FestivalDTO> getOne(@PathVariable Long id){
		Optional<Festival>festivali = festivalService.findOne(id);
		if(!festivali.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(festivali.get()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FestivalDTO> add(@Validated @RequestBody FestivalDTO newDto){
		
		Festival festival = toFestival.convert(newDto);
		Festival saved = festivalService.save(festival);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FestivalDTO> edit(@Validated @RequestBody FestivalDTO festivalDTO, 
			@PathVariable Long id){
		
		if(!id.equals(festivalDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Festival festival = toFestival.convert(festivalDTO);
		Festival saved = festivalService.update(festival);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Festival obrisanFestival = festivalService.delete(id);
		
		if(obrisanFestival !=null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('KORISNIK')")
	@PostMapping (value = "/{id}/rezervisi")
	public ResponseEntity<Festival> rezervacija (@PathVariable Long id, @Validated @RequestBody RezervacijaDTO rezervacijaDTO){
	Rezervacija rezervacija = toRezervacija.convert(rezervacijaDTO);
	
	Festival festival = festivalService.rezervacija(id, rezervacija);
	
	if(festival !=null) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
