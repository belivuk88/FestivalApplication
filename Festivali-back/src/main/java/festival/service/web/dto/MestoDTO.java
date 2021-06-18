package festival.service.web.dto;

import org.hibernate.validator.constraints.Length;

public class MestoDTO {
	
	private Long id;
	
	private String grad;
	
	@Length(max = 3)
	private String drzava;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	
	

}
