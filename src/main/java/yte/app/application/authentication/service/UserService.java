package yte.app.application.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yte.app.application.authentication.entity.Authority;
import yte.app.application.authentication.entity.User;
import yte.app.application.authentication.repository.UserRepository;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MessageResponse addUsers(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return new MessageResponse(ResponseType.WARNING, "Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthorities().add(new Authority(user.getRole()));
        userRepository.save(user);

        return new MessageResponse(ResponseType.SUCCESS, "User has been added successfully");
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public MessageResponse deleteUserById(Long id) {
        String name = userRepository.findById(id).orElseThrow().getUsername();
        System.out.println(name);
        if ((name.equals("admin"))) {
            return new MessageResponse(ResponseType.ERROR, "user could not be deleted");
        }
        userRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "User has been deleted successfully");
    }

    @Transactional
    public MessageResponse updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.update(updatedUser);
        userRepository.save(user);

        return new MessageResponse(ResponseType.SUCCESS, "User has been updated successfully");
    }
}
