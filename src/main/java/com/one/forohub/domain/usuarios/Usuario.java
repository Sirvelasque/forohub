package com.one.forohub.domain.usuarios;

import com.one.forohub.domain.perfiles.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<Perfil> profiles = new HashSet<>();

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.name = datosRegistroUsuario.name();
        this.email = datosRegistroUsuario.email();
        this.password = datosRegistroUsuario.password();
    }

    public void updateUser(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario.name() != null) {
            this.name = datosActualizarUsuario.name();
        }
        if (datosActualizarUsuario.email() != null) {
            this.email = datosActualizarUsuario.email();
        }
        if (datosActualizarUsuario.password() != null) {
            this.password = datosActualizarUsuario.password();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles.stream().map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

