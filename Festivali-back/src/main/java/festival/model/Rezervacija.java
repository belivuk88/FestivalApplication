package festival.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	private int kupljeneKarte;
	
	@Column
	private int ukupnaCena;
	
	@ManyToOne
	Festival festival;

	public Rezervacija() {
		super();
	}

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

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
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
		Rezervacija other = (Rezervacija) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", kupljeneKarte=" + kupljeneKarte + ", ukupnaCena=" + ukupnaCena
				+ ", festival=" + festival + "]";
	}
	
	
	

}
