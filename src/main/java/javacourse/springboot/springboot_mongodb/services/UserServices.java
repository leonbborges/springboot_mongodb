package javacourse.springboot.springboot_mongodb.services;

import javacourse.springboot.springboot_mongodb.domain.User;
import javacourse.springboot.springboot_mongodb.dto.UserDTO;
import javacourse.springboot.springboot_mongodb.repository.UserRepository;
import javacourse.springboot.springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
      return repository.findAll();
    }

    public User findById(String id){
        Optional <User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public User fromDTO (UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
    public User update(String id, User obj){
        User newobj = findById(id);
        updateData(newobj, obj);
        return repository.save(newobj);
    }

    private void updateData(User newobj, User obj) {
        newobj.setName(obj.getName());
        newobj.setEmail(obj.getEmail());
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }
}
