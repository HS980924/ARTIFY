package com.born.artify.domain.user.service;
import com.born.artify.domain.user.dto.CreateUserDTO;
import com.born.artify.domain.user.dto.UserInfoReqDTO;
import com.born.artify.domain.user.dto.UserOpenAIKeyReqDTO;
import com.born.artify.domain.user.entity.User;

import com.born.artify.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(CreateUserDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 암호화

        return userRepository.save(user); // 저장
    }


    @Transactional
    public User updatedUser(UserInfoReqDTO request, int userId){

        User user = getUser(userId);

        if(request.getUsername() != null)
            user.setUserName(request.getUsername());
        if(request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));  // 비밀번호 암호화

        return userRepository.save(user); // 저장

    }

    public User getUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    public User getUser(int userId) {

        User user = userRepository.findById((long)userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    public User setUserOpenAiKey(int userId, UserOpenAIKeyReqDTO dto){


        User user = userRepository.findById((long)userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(dto == null)
        {
            user.setOpen_key(null);
        }else{
            user.setOpen_key(dto.getOpenAiKey());
        }
        userRepository.save(user);

        return user;

    }

}
