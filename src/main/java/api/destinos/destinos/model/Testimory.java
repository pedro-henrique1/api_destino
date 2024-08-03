package api.destinos.destinos.model;


import api.destinos.destinos.controller.DestinyRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
//@Table(name = "testimony")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Testimory implements Comparable<Testimory> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column()
    private String name;


    @Column()
    private String deponent;

    @Column()
    private String image;


    public Testimory(DestinyRequestPayload destinyRequestPayload) {

        this.name = destinyRequestPayload.name();
        this.deponent = destinyRequestPayload.deponent();
        this.image = destinyRequestPayload.image();
    }

    @Override
    public int compareTo(Testimory o) {
        return 0;
    }
}
