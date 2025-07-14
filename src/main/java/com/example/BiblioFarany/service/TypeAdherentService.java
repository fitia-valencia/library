// TypeAdherentService.java
package com.example.BiblioFarany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.TypeAdherent;
import com.example.BiblioFarany.repository.TypeAdherentRepository;

@Service
public class TypeAdherentService {
    
    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    public List<TypeAdherent> getAllTypes() {
        return typeAdherentRepository.findAll();
    }
}
