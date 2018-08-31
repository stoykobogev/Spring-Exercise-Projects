package org.softuni.residentevil.services;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.bindingmodels.VirusBindingModel;
import org.softuni.residentevil.repositories.CapitalRepository;
import org.softuni.residentevil.repositories.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final VirusRepository virusRepository;
    private final CapitalRepository capitalRepository;
    private final ModelMapper mapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper mapper) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Virus> getAll() {
        return this.virusRepository.findAll();
    }

    @Override
    public void persistVirus(VirusBindingModel virusBindingModel) {
        Virus virus = this.mapper.map(virusBindingModel, Virus.class);
        List<Capital> capitals = this.capitalRepository.findCapitalsByNameIn(virusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        virus.setReleasedOn(LocalDate.parse(virusBindingModel.getReleasedOn(), DATE_TIME_FORMATTER));
        this.virusRepository.saveAndFlush(virus);
    }

    @Override
    public VirusBindingModel getVirusModelById(Long id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        VirusBindingModel virusBindingModel = this.mapper.map(virus, VirusBindingModel.class);
        List<String> capitalNames = virus.getCapitals().stream().map(Capital::getName).collect(Collectors.toList());
        virusBindingModel.setCapitals(capitalNames);
        virusBindingModel.setReleasedOn(virus.getReleasedOn().format(DATE_TIME_FORMATTER));
        return virusBindingModel;
    }

    @Override
    public void updateVirus(Long id, VirusBindingModel virusBindingModel) {
        Virus virusWithId = this.virusRepository.findById(id).orElse(null);
        Virus virus = this.mapper.map(virusBindingModel, Virus.class);
        List<Capital> capitals = this.capitalRepository.findCapitalsByNameIn(virusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        virus.setReleasedOn(LocalDate.parse(virusBindingModel.getReleasedOn(), DATE_TIME_FORMATTER));
        if (virusWithId != null) {
            virus.setId(virusWithId.getId());
            this.virusRepository.saveAndFlush(virus);
        }
    }

    @Override
    public void deleteVirus(Long id) {
        this.virusRepository.deleteById(id);
    }
}
