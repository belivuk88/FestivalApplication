package festival.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.Rezervacija;
import festival.service.web.dto.RezervacijaDTO;
@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
		rezervacijaDTO.setId(rezervacija.getId());
		rezervacijaDTO.setKupljeneKarte(rezervacija.getKupljeneKarte());
		rezervacijaDTO.setUkupnaCena(rezervacija.getUkupnaCena());
		
		if(rezervacija.getFestival() !=null) {
			rezervacijaDTO.setFestivalId(rezervacija.getFestival().getId());
		}
		return rezervacijaDTO;
	}
	
	public List<RezervacijaDTO> convert (List<Rezervacija>rezervacije){
		List<RezervacijaDTO> rezervacijeDTO = new ArrayList<>();
		
		for (Rezervacija rezervacija : rezervacije) {
			rezervacijeDTO.add(convert(rezervacija));
		}
		
		return rezervacijeDTO;
	}
}
