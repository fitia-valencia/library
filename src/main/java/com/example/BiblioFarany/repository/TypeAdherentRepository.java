// TypeAdherentRepository.java
package com.example.BiblioFarany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BiblioFarany.model.TypeAdherent;

@Repository
public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Integer> {
}
