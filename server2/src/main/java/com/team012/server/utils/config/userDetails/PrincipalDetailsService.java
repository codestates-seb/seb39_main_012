package com.team012.server.utils.config.userDetails;

import com.team012.server.usersPack.users.entity.Users;
import com.team012.server.usersPack.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users usersEntity = usersRepository.findByEmail(email);

        return new PrincipalDetails(usersEntity);
    }
}