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
import fr.diginamic.springMVC.model.Species;
import fr.diginamic.springMVC.repository.SpeciesRepository;

@Controller
public class SpeciesController {
	
	@Autowired
	private SpeciesRepository speciesRepository;

	
	 @GetMapping("species")
	 public String getSpeciesListView(Model model) {
		 List<Species> species=speciesRepository.findAll();
		 model.addAttribute("specie",species);
		 return "list_species";
	 }
	 
	 @GetMapping("species/{id}")
		public String getSpeciesByView(@PathVariable("id") Integer id, Model model) {
			Optional<Species> species = speciesRepository.findById(id);
			if (species.isPresent()) {
				model.addAttribute("species", species.get());
				return "update_species";
			}
			return "error";
		}

		@GetMapping("species/create")
		public String getSpeciesCreateView(Model model) {
			model.addAttribute("species", new Species());
			return "create_species";
		}
		@PostMapping("/species")
		public String createOrUpdate(Species species) {
			this.speciesRepository.save(species);
			return "redirect:/species";
	 }
		@GetMapping("species/delete/{id}")
		public String delete(@PathVariable("id") Integer speciesDel) {
			Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesDel);
			speciesToDelete.ifPresent(species->this.speciesRepository.delete(species));
			return "redirect:/species";
		}

}
