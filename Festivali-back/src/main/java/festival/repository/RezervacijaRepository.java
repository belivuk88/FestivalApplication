package festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festival.model.Rezervacija;
@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

}
