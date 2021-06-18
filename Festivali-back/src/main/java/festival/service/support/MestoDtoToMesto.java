package festival.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.Mesto;
import festival.service.MestoService;
import festival.service.web.dto.MestoDTO;
@Component
public class MestoDtoToMesto implements Converter<MestoDTO, Mesto> {
	
	@Autowired
	private MestoService mestoService;
	
	@Override
	public Mesto convert (MestoDTO dto) {
		
		Mesto mesto;
		
		if(dto.getId() == null) {
			mesto = new Mesto();
		}else {
			mesto = mestoService.findOne(dto.getId()).get();
		}
		if(mesto !=null) {
			mesto.setGrad(dto.getGrad());
			mesto.setDrzava(dto.getDrzava());
		}
		
		return mesto;
	}

}
