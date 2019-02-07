package app.repositories;

import app.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
    Tube findByName(String name);
}
