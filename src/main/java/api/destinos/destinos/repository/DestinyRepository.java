package api.destinos.destinos.repository;

import api.destinos.destinos.model.Testimory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DestinyRepository extends JpaRepository<Testimory, UUID> {
//    List<Testimory> finByDestinyId(UUID DestinyId);
}
