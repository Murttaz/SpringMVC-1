package fr.diginamic.springMVC.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.springMVC.enums.Sex;
import fr.diginamic.springMVC.model.Animal;
import fr.diginamic.springMVC.model.Species;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
	List<Animal>findAllBySpecies(Species species);
	List<Animal>findByColor(String color);
	
	@Query("SELECT COUNT(a) FROM Animal a WHERE sex = :sex")
	Integer findBySex(@Param("sex")Sex sex);
}
