package pe.edu.upc.aaw.helpdesk_rag_backend.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.helpdesk_rag_backend.dtos.UserDTO;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Users;
import pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void insertar(@RequestBody UserDTO userDTO) {

        ModelMapper m = new ModelMapper();
        Users u = m.map(userDTO, Users.class);

        // 1. Encriptar password
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            u.setPassword(encoder.encode(userDTO.getPassword()));
        }

        // 2. Guardar usuario (ahora sí tiene ID real)
        uS.insert(u);

        // 3. Insertar rol usando EL ID del usuario recien creado
        if (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) {
            uS.actualizarRol(userDTO.getRole(), u.getId());
        }
    }
    //v
    @GetMapping
    public List<UserDTO> listar(){
        return uS.list().stream().map(y->{
            ModelMapper m=new ModelMapper();
            return m.map(y,UserDTO.class);
        }).collect(Collectors.toList());
    }
    //eli
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        uS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody UserDTO dto) {

        // 1. Mapear dto → entidad temporal
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto, Users.class);

        // 2. Encriptar password si llega una nueva
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            u.setPassword(encoder.encode(dto.getPassword()));
        } else {
            u.setPassword(null); // no cambia la password
        }

        // 3. Actualizar usuario
        uS.update(u);

        // 4. Actualizar rol SI LLEGA
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            uS.actualizarRol(dto.getRole(), dto.getId());
        }
    }

    @GetMapping("/{id}")
    public UserDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        UserDTO dto = m.map(uS.listarId(id), UserDTO.class);
        return dto;
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<String> asignarRol(@PathVariable Long id, @RequestParam String rol) {
        uS.insertarRol(rol, id);
        return ResponseEntity.ok("Rol asignado correctamente");
    }
}
