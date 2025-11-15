package pe.edu.upc.aaw.helpdesk_rag_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.aaw.helpdesk_rag_backend.dtos.UserDTO;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Role;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Users;
import pe.edu.upc.aaw.helpdesk_rag_backend.repositories.UserRepository;
import pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository uR;

    // --------------------------------------------
    // CONVERTIR ENTIDAD → DTO
    // --------------------------------------------
    private UserDTO convertToDto(Users u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setMail(u.getMail());
        dto.setEnabled(u.getEnabled());
        dto.setPassword(null); // por seguridad

        // EXTRAER ROL
        if (u.getRoles() != null && !u.getRoles().isEmpty()) {
            dto.setRole(u.getRoles().get(0).getRol());
        } else {
            dto.setRole("User");
        }

        return dto;
    }

    // --------------------------------------------
    //  CONVERTIR DTO → ENTIDAD
    // --------------------------------------------
    private Users convertToEntity(UserDTO dto) {
        Users u = new Users();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setMail(dto.getMail());
        u.setPassword(dto.getPassword());
        u.setEnabled(dto.getEnabled());

        // CREAR ROL SI LLEGA
        if (dto.getRole() != null) {
            Role r = new Role();
            r.setRol(dto.getRole());
            u.setRoles(List.of(r));
        }

        return u;
    }
    @Override
    public void insert(Users users) {
        uR.save(users);
    }
    @Override
    public List<UserDTO> list() {
        return uR.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long idusers) {
        uR.deleteById(idusers);
    }

    @Override
    public UserDTO listarId(Long idusers) {
        Users u = uR.findById(idusers).orElse(new Users());
        return convertToDto(u);
    }

    @Override
    public void insertarRol(String rol, Long userId) {
        uR.insRol(rol, userId);
    }

    @Override
    @Transactional
    public void update(Users u) {
        uR.save(u);
    }

    @Override
    public void actualizarRol(String rol, Long userId) {
        uR.actualizarRol(rol, userId);
    }

    @Override
    @Transactional
    public void updateUserFields(UserDTO dto) {
        uR.updateUserFields(
                dto.getId(),
                dto.getName(),
                dto.getMail(),
                dto.getEnabled(),
                dto.getPassword() == null || dto.getPassword().isEmpty() ? null : dto.getPassword()
        );
    }
    public void updateBasic(Users u) {
        uR.updateBasic(
                u.getName(),
                u.getMail(),
                u.getPassword(),
                u.getEnabled(),
                u.getId()
        );
    }
}