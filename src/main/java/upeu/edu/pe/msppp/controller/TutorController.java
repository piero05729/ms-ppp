package upeu.edu.pe.msppp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.msppp.domain.Tutor;
import upeu.edu.pe.msppp.service.ITutorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    @Autowired
    private ITutorService tutorService;

    @GetMapping
    public ResponseEntity<List<Tutor>> listar() {
        List<Tutor> lista = tutorService.readAll();
        if (lista.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tutor> guardar(@RequestBody Tutor tutor) {
        return new ResponseEntity<>(tutorService.create(tutor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> buscar(@PathVariable Long id) {
        Optional<Tutor> tutor = tutorService.read(id);
        return tutor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutor> actualizar(@PathVariable Long id, @RequestBody Tutor tutor) {
        Optional<Tutor> actual = tutorService.read(id);
        if (actual.isPresent()) {
            tutor.setIdtutor(id);
            return new ResponseEntity<>(tutorService.update(tutor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tutorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
