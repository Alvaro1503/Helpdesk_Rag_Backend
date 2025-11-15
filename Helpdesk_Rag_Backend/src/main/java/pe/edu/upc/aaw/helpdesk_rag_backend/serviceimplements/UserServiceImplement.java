package pe.edu.upc.aaw.helpdesk_rag_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Users;
import pe.edu.upc.aaw.helpdesk_rag_backend.repositories.UserRepository;
import pe.edu.upc.aaw.helpdesk_rag_backend.serviceinterfices.UserService;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
@Autowired
private UserRepository uR;

    @Override
    public void insert(Users users) {
uR.save(users);
    }

    @Override
    public List<Users> list() {
        return uR.findAll();
    }

    @Override
    public void delete(Long idusers) {
uR.deleteById(idusers);
    }

    @Override
    public Users listarId(Long idusers) {
        return uR.findById(idusers).orElse(new Users());
    }
    public void insertarRol(String rol, Long userId) {
        uR.insRol(rol, userId);
    }

}
