package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User updateUser(int id, User user){

        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 ID입니다."));
        userEntity.setName(user.getName());

        String rawPassword = userEntity.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }
}
