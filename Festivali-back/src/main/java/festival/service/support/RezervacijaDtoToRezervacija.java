package festival.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.Festival;
import festival.model.Rezervacija;
import festival.service.FestivalService;
import festival.service.RezervacijaService;
import festival.service.web.dto.RezervacijaDTO;
@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private FestivalService festivalService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		
		Festival festival = null;
		if(dto.getFestivalId() !=null) {
			festival = festivalService.findOne(dto.getFestivalId()).get();
		}
		
		if(festival !=null) {
			Long id = dto.getId();
			Rezervacija rezervacija = id == null ? new Rezervacija(): rezervacijaService.findOne(id).get();
			
			if(rezervacija !=null) {
				rezervacija.setKupljeneKarte(dto.getKupljeneKarte());
				rezervacija.setUkupnaCena(rezervacija.getUkupnaCena());
				rezervacija.setFestival(festival);
			}
			return rezervacija;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant");
		}
	}
}
