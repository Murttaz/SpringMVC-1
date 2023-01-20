package fr.diginamic.springMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.springMVC.model.Animal;
import fr.diginamic.springMVC.model.Person;
import fr.diginamic.springMVC.repository.AnimalRepository;
import fr.diginamic.springMVC.repository.PersonRepository;

@Controller
public class PersonController {

	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;
	
	 @GetMapping("person")
	 public String getPersonListView(Model model) {
		 List<Person> person=personRepository.findAll();
		 model.addAttribute("persons",person);
		 return "list_person";
	 }
	 @GetMapping("person/{id}")
		public String getPersonByView(@PathVariable("id") Integer id, Model model) {
			Optional<Person> person = personRepository.findById(id);
			if (person.isPresent()) {
				model.addAttribute("person", person.get());
				model.addAttribute( "animal" ,animalRepository.findAll());
				return "update_person";
			}
			return "error";
		}

		@GetMapping("person/create")
		public String getPersonCreateView(Model model) {
			model.addAttribute("person", new Person());
			return "create_person";
		}

		@PostMapping("/person")
		public String createOrUpdate(Person person) {
			this.personRepository.save(person);
			return "redirect:/person";
	 }
		@GetMapping("person/delete/{id}")
		public String delete(@PathVariable("id") Integer personDel) {
			Optional<Person> personToDelete = this.personRepository.findById(personDel);
			personToDelete.ifPresent(person->this.personRepository.delete(person));
			return "redirect:/person";
		}
}