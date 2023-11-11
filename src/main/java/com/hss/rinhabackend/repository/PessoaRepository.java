package com.hss.rinhabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    @Query("select count(1) from Pessoa")
    Long countPessoas();

    @Query("select case when (count(1) > 0) then true else false end from Pessoa where :reqApelido = apelido")
    boolean pessoaExiste(@Param("reqApelido") String apelido);

    @Query(value = "select * from pessoa " +
            "where lower(stack) like lower(concat('%', :t, '%')) or " +
                "lower(nome) like lower(concat('%', :t, '%')) or " +
                "lower(apelido) like lower(concat('%', :t, '%'))", nativeQuery = true)
    List<Pessoa> findByTermo(@Param("t") String t);
}
