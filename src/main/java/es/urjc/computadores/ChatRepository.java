package es.urjc.computadores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByComprador(Usuario comprador);
}
