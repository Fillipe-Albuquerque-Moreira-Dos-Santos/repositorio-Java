package com.projeto.FillipeAlbuquerque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.projeto.FillipeAlbuquerque.model.Contato;

public interface ContatoRepository extends CrudRepository<Contato, Long> {
    @Query("SELECT c FROM Contato c WHERE c.id = (SELECT MAX(c2.id) FROM Contato c2)")
    public Contato findLastInsertContato();

    @Query("SELECT c FROM Contato c WHERE c.nome = :nome")
    public Contato findByNome(String nome);

    @Query("SELECT c FROM Contato c ORDER BY c.id")
    public List<Contato> findAllOrderedById();
}
