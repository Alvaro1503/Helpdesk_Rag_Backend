package pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices;

import pe.edu.upc.aaw.helpdesk_rag_backend.dtos.UserDTO;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Users;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    void insert(Users users);

    List<UserDTO> list();   // ← antes devolvía Users

    void delete(Long idusers);

    UserDTO listarId(Long idusers); // ← antes devolvía Users

    void insertarRol(String role, Long userId);

    void update(Users entity);

    void actualizarRol(String role, Long id);

    @Transactional
    void updateUserFields(UserDTO dto);

    void updateBasic(Users u);
}