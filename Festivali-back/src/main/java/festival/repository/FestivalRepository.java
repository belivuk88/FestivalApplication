package festival.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import festival.model.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long>  {
	
	@Query("SELECT f FROM Festival f WHERE " +
			"(:mestoId = NULL OR f.mesto.id = :mestoId) AND " + 
			"(:naziv = NULL OR f.naziv like :naziv)")
	Page<Festival> pretraga(@Param("mestoId") Long mestoId, @Param("naziv") String naziv, Pageable pageable);
	

}
