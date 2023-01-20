package fr.diginamic.springMVC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.diginamic.springMVC.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person>findByLastnameLike(String lastname);
	List<Person>findByFirstnameLike (String firstname);
	List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	List<Person> findByAgeGreaterThanEqual(int age);
	
	@Query("SELECT p FROM Person p WHERE p.age BETWEEN 40 AND 60")
	List<Person>findByAgeBetween();
	
	@Query("SELECT p FROM Person p INNER JOIN Animal a WHERE a.name = 'Max'")
	List<Person>findInnerJoin();
}
