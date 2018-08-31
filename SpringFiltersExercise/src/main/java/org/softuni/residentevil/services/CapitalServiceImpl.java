package org.softuni.residentevil.services;

import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.repositories.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }


    @Override
    public List<Capital> getAll() {
        return this.capitalRepository.findAll();
    }
}
