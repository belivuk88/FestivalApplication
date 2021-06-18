package festival.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Festival {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private String naziv;
	
	@Column ( nullable = false )
	private String datumPocetka;
	
	@Column ( nullable = false )
	private String datumZavrsetka;
	
	@Column
	private int cena;
	
	@Column
	private int dostupneKarte;
	
	@ManyToOne 
	Mesto mesto;
	
	@OneToMany ( mappedBy = "festival", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rezervacija> rezervacije = new ArrayList<>();

	public Festival() {
		super();
	}

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

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Festival other = (Festival) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Festival [id=" + id + ", naziv=" + naziv + ", datumPocetka=" + datumPocetka + ", datumZavrsetka="
				+ datumZavrsetka + ", cena=" + cena + ", dostupneKarte=" + dostupneKarte + ", mesto=" + mesto + "]";
	}
	
	
	
	
	

}
