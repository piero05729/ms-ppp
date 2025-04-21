package upeu.edu.pe.msppp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.msppp.domain.Ppp;
import upeu.edu.pe.msppp.service.IPppService;
import upeu.edu.pe.msppp.service.impl.IPppServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ppp")
public class PppController {

    @Autowired
    private IPppServiceImpl pppService;

    @GetMapping
    public ResponseEntity<List<Ppp>> listar() {
        List<Ppp> lista = pppService.readAll();
        if (lista.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ppp> guardar(@RequestBody Ppp ppp) {
        return new ResponseEntity<>(pppService.create(ppp), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ppp> buscar(@PathVariable Long id) {
        Optional<Ppp> ppp = pppService.read(id);
        return ppp.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ppp> actualizar(@PathVariable Long id, @RequestBody Ppp ppp) {
        Optional<Ppp> actual = pppService.read(id);
        if (actual.isPresent()) {
            ppp.setIdppp(id);
            return new ResponseEntity<>(pppService.update(ppp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pppService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

