package festival.service.web.dto;

public class RezervacijaDTO {
	
	private Long id;
	
	private int kupljeneKarte;
	
	private int ukupnaCena;
	
	private Long festivalId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKupljeneKarte() {
		return kupljeneKarte;
	}

	public void setKupljeneKarte(int kupljeneKarte) {
		this.kupljeneKarte = kupljeneKarte;
	}

	public int getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Long getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}
	
	
	
	

}
