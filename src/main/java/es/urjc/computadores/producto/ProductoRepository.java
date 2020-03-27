package es.urjc.computadores.producto;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.computadores.usuario.Usuario;



public interface ProductoRepository extends JpaRepository<Producto, Long>{
	List<Producto> findByPropietario(Usuario u);
	//Producto findById(Long id);
	
}
