package es.urjc.computadores.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.computadores.usuario.Usuario;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByComprador(Usuario comprador);
	List<Chat> findByVendedor(Usuario vendedor);
}