package api.destinos.destinos.controller;


import api.destinos.destinos.model.Destino;
import api.destinos.destinos.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/destino")
public final class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    private final DestinoDescriptionService service;

    public DestinoController(DestinoRepository destinoRepository, DestinoDescriptionService service) {
        this.destinoRepository = destinoRepository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Destino> create(@RequestBody DestinoRequestPayload destinoRequestPayload) {
        try {
            Destino destino = new Destino(destinoRequestPayload);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createDescriptionDestino(destino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/nome/{id}")
    public ResponseEntity<?> allCity(@PathVariable Integer id, DestinoRequestPayload requestpayload) {
        Optional<Destino> destino = this.destinoRepository.findById(id);
        if (destino.isEmpty()) {
            return ResponseEntity.status(Integer.parseInt("401")).body(("Nenhum destino foi encontrado"));
        }
        return ResponseEntity.ok(destino);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Destino> updateDestino(@PathVariable Integer id, @RequestBody DestinoRequestPayload destinyRequestPayload) {
        Optional<Destino> destino = this.destinoRepository.findById(id);
        if (destino.isPresent()) {
            Destino destinoRaw = destino.get();
            destinoRaw.setName(destinyRequestPayload.name());
            destinoRaw.setImage(destinyRequestPayload.image());
            destinoRaw.setPrice(destinyRequestPayload.price());
            this.destinoRepository.save(destinoRaw);
            return ResponseEntity.ok(destinoRaw);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> updateDestiny(@PathVariable Integer id) {
        Optional<Destino> destinoDelete = this.destinoRepository.findById(id);
        if (destinoDelete.isPresent()) {
            this.destinoRepository.deleteById(id);
            return ResponseEntity.ok("deletado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

}