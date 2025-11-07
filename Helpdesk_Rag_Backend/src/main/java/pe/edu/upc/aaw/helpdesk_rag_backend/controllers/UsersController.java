package pe.edu.upc.aaw.helpdesk_rag_backend.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public void insertar(@RequestBody UserDTO userDTO){
        ModelMapper m=new ModelMapper();
        Users te =m.map(userDTO, Users.class);
        uS.insert(te);
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
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto, Users.class);
        uS.insert(u);
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
