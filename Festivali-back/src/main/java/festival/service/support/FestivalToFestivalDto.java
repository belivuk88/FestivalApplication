package festival.service.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.Festival;
import festival.service.web.dto.FestivalDTO;
@Component
public class FestivalToFestivalDto implements Converter<Festival, FestivalDTO> {

	
	@Override
	public FestivalDTO convert(Festival festival) {
		FestivalDTO festivalDTO = new FestivalDTO();
		festivalDTO.setId(festival.getId());
		festivalDTO.setNaziv(festival.getNaziv());
		festivalDTO.setDatumPocetka(festival.getDatumPocetka());
		festivalDTO.setDatumZavrsetka(festival.getDatumZavrsetka());
		festivalDTO.setCena(festival.getCena());
		festivalDTO.setDostupneKarte(festival.getDostupneKarte());
		
		if(festival.getMesto() !=null) {
			festivalDTO.setMestoId(festival.getMesto().getId());
		}
		
		return festivalDTO;
	}
	
	public List<FestivalDTO> convert (List<Festival>festivali){
		List<FestivalDTO> festivaliDTO = new ArrayList<>();
		
		for (Festival festival : festivali) {
			festivaliDTO.add(convert(festival));
		}
		
		return festivaliDTO;
		
	}
}
