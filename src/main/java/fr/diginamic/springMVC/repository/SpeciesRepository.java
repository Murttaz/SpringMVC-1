package fr.diginamic.springMVC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.springMVC.model.Species;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {

	List<Species> findFirstByCommonName(String commonName);
	List<Species> findByLatinNameLike (String LatinName);
	
	//		action	quoi		de quelle table/ Class
	@Query("SELECT s FROM Species s ORDER BY s.commonName Asc")
	List<Species>findBySpeciesOrderByCommonNameAsc();
	
	@Query("SELECT s FROM Species s WHERE s.commonName LIKE 'Ch%'")
	List<Species>findByCommonNameLike();
}
