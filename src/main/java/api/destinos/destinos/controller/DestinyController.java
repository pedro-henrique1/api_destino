package api.destinos.destinos.controller;


import api.destinos.destinos.model.Testimory;
import api.destinos.destinos.repository.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/depoimentos")
public class DestinyController {

    @Autowired
    private DestinyRepository repository;


    @PostMapping
    public ResponseEntity<Testimory> createDestiny(@RequestBody DestinyRequestPayload destinyRequestPayload) {
        Testimory newTestimory = new Testimory(destinyRequestPayload);
        this.repository.save(newTestimory);
        return ResponseEntity.status(Integer.parseInt("201")).body((newTestimory));

    }


    @GetMapping("/all")
    public ResponseEntity<List<Testimory>> allDestiny(DestinyRequestPayload destinyRequestPayload) {
        List<Testimory> destinyAll = repository.findAll();
        Collections.shuffle(destinyAll);
        List<Testimory> limitedList = destinyAll.subList(0, Math.min(3, destinyAll.size()));
        return ResponseEntity.ok(limitedList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Testimory> updateDestiny(@PathVariable UUID id, @RequestBody DestinyRequestPayload destinyRequestPayload) {
        Optional<Testimory> testimory = this.repository.findById(id);
        if (testimory.isPresent()) {
            Testimory rawTestimory = testimory.get();
            rawTestimory.setDeponent(destinyRequestPayload.deponent());
            this.repository.save(rawTestimory);
            return ResponseEntity.ok(rawTestimory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> updateDestiny(@PathVariable UUID id) {
        Optional<Testimory> testimory = this.repository.findById(id);
        if (testimory.isPresent()) {
            this.repository.deleteById(id);
            return ResponseEntity.ok("deletado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

}
