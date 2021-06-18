package festival.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.Festival;
import festival.model.Mesto;
import festival.service.FestivalService;
import festival.service.MestoService;
import festival.service.web.dto.FestivalDTO;
@Component
public class FestivalDtoToFestival implements Converter<FestivalDTO, Festival> {

	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private MestoService mestoService;
	
	@Override
	public Festival convert(FestivalDTO dto) {
		
		Mesto mesto = null;
		if(dto.getMestoId() !=null) {
			mesto = mestoService.findOne(dto.getMestoId()).get();
		}
		
		if(mesto !=null) {
			Long id = dto.getId();
			Festival festival = id == null ? new Festival(): festivalService.findOne(id).get();
		
			if (festival !=null) {
				festival.setNaziv(dto.getNaziv());
				festival.setDatumPocetka(dto.getDatumPocetka());
				festival.setDatumZavrsetka(dto.getDatumZavrsetka());
				festival.setCena(dto.getCena());
				festival.setDostupneKarte(dto.getDostupneKarte());
				festival.setMesto(mesto);
			}
			
			return festival;
		
		}else {
			throw new IllegalStateException("Trying to attach to non-existant");
		}
	}
	

}
