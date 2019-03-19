package app.services;

import app.domain.dtos.PartSeedDto;

public interface PartService {
    void seedParts(PartSeedDto[] partSeedDtos);
}
