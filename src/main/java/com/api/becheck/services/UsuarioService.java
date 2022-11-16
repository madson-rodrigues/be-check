package com.api.becheck.services;

import com.api.becheck.models.UsuarioModel;
import com.api.becheck.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    final
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> optional = usuarioRepository.findUsuarioByUsername(username);

        if(optional.isPresent()) {
            return new org.springframework.security.core.userdetails.User(optional.get().getUsername(), encoder().encode(optional.get().getPassword()), Collections.singletonList(new SimpleGrantedAuthority(optional.get().getRole())));
        }

        throw new UsernameNotFoundException("User not found");
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public Page<UsuarioModel> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<UsuarioModel> findById(UUID id_user) {
        return usuarioRepository.findById(id_user);
    }

    public void delete(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
    }

    public void getById(UUID id_usuario) {
    }
}
