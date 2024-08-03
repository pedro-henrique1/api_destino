package api.destinos.destinos.model;


import api.destinos.destinos.controller.DestinoRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String image;

    @Column()
    private Double price;

    @Column(length = 255)
    private String description;

    public Destino(DestinoRequestPayload destinoRequestPayload) {
        this.name = destinoRequestPayload.name();
        this.price = destinoRequestPayload.price();
        this.image = destinoRequestPayload.image();
        this.description = destinoRequestPayload.description();
    }
}
