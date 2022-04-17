package br.com.pereira.LojaDeDoces.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pereira.LojaDeDoces.model.Adm;
import br.com.pereira.LojaDeDoces.repository.AdmRepository;

@RestController
@RequestMapping("/api")
public class AdmController {

	@Autowired
    private AdmRepository aRep;
	
	@GetMapping("/adm/{idAdm}")
    public ResponseEntity<Adm> getAdm(@PathVariable(value = "idAdm") int idAdm) throws ResourceNotFoundException {
		Adm adm = aRep.findById(idAdm).orElseThrow(
                () -> new ResourceNotFoundException(idAdm + " inválido")
        );
        return ResponseEntity.ok().body(adm);
    }
	
	@PutMapping("/adm")
	public ResponseEntity<String> putAdm(@Valid @RequestBody Adm a) {
        aRep.save(a);
        return ResponseEntity.ok().body("Adm atualizado com sucesso");
    }
}