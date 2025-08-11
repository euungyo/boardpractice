package com.example.boardpractice.Serviece;

import com.example.boardpractice.DTO.JoinDTO;
import com.example.boardpractice.Entity.UserEntity;
import com.example.boardpractice.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDTO joinDTO) {
        String nickname = joinDTO.getNickname();
        String username = joinDTO.getName();
        String password = joinDTO.getPassword();
        String age = joinDTO.getAge();

        Boolean isExist = userRepository.existsByNickname(nickname);

        if (isExist) {

            return ;
        }

        UserEntity data = UserEntity.builder()
                .nickname(nickname)
                .name(username)
                .password(bCryptPasswordEncoder.encode(password))
                .age(age)
                .role("ROLE_ADMIN")
                .build();

        userRepository.save(data);
    }
}
