package com.botilleria.despachos.repository;

import com.botilleria.despachos.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DespachoRepository extends JpaRepository<Despacho, String> {
    Optional<Despacho> findByOrdenId(String ordenId);
}
