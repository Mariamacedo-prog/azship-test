package com.avaliacao.azship.infraestrutura.adaptadores.respositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avaliacao.azship.infraestrutura.adaptadores.entidades.ClienteEntity;


@Repository
public interface SpringClienteRepository extends JpaRepository<ClienteEntity, Long>{
	@Query(value = "SELECT * FROM cliente c WHERE " +
            "c.nome LIKE %?1% ", nativeQuery = true)
	Page<ClienteEntity> findAllBySearch(Pageable pageable, String search);
	
	@Modifying
	@Query(value = "DELETE FROM frete WHERE cliente_id = ?1", nativeQuery = true)
	void deleteFreteByClienteId(Long clienteId);

	@Modifying
	@Query(value = "DELETE FROM atributo_cliente WHERE cliente_id = ?1", nativeQuery = true)
	void deleteAtributoClienteByClienteId(Long clienteId);
}
