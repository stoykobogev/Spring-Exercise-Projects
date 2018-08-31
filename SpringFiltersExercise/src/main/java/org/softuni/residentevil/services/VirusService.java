package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.binding.VirusBindingModel;

import java.util.List;

public interface VirusService {
    List<Virus> getAll();

    void persistVirus(VirusBindingModel virusBindingModel);

    VirusBindingModel getVirusModelById(Long id);

    void updateVirus(Long id, VirusBindingModel virusBindingModel);

    void deleteVirus(Long id);
}
