package com.matching.repository;

import com.matching.model.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository
        extends JpaRepository<Matching, Long> {

    List<Matching> findByRutUsuario(String rutUsuario);

}