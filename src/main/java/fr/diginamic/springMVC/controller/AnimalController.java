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
import fr.diginamic.springMVC.model.Species;
import fr.diginamic.springMVC.repository.AnimalRepository;
import fr.diginamic.springMVC.repository.SpeciesRepository;

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private SpeciesRepository speciesRepository;
	
	
	@GetMapping("animal")
	public String getAnimalListView(Model model) {
		List<Animal> animal = animalRepository.findAll();
		model.addAttribute("animals", animal);
		return "list_animal";
	}

	@GetMapping("animal/{id}")
	public String getAnimalByView(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animal = animalRepository.findById(id); 
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
			model.addAttribute( "species" ,speciesRepository.findAll());
			return "update_animal";
		}
		return "error";
	}

	@GetMapping("animal/create")
	public String getAnimalCreateView(Model model) {
		model.addAttribute("animal", new Animal());
		model.addAttribute( "species" ,speciesRepository.findAll());
		return "create_animal";
	}

	@PostMapping("/animal")
	public String createOrUpdate(Animal animal) {
		this.animalRepository.save(animal);
		return "redirect:/animal";
	}
	
	@GetMapping("animal/delete/{id}")
	public String delete(@PathVariable("id") Integer animalDel) {
		Optional<Animal> animalToDelete = this.animalRepository.findById(animalDel);
		animalToDelete.ifPresent(animal->this.animalRepository.delete(animal));
		return "redirect:/animal";
	}

}
