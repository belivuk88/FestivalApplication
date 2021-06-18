package festival.service.web.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

public class FestivalDTO {
	
	private Long id;
	
	@Length(max = 50)
	private String naziv;
	
	private String datumPocetka;
	
	private String datumZavrsetka;
	
	@Min(value = 0)
	private int cena;
	
	private int dostupneKarte;
	
	private Long mestoId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(String datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(String datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getDostupneKarte() {
		return dostupneKarte;
	}

	public void setDostupneKarte(int dostupneKarte) {
		this.dostupneKarte = dostupneKarte;
	}

	public Long getMestoId() {
		return mestoId;
	}

	public void setMestoId(Long mestoId) {
		this.mestoId = mestoId;
	}
	
	
	
	
	
	

}
