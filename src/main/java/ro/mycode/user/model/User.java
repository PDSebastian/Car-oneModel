package ro.mycode.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.mycode.config.UserPermissions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 3, max = 50, message = "size 3-50")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 3, max = 50, message = "size 3-50")
    private String lastName;

    @Email
    @NotBlank
    @Size(min = 3, max = 50, message = "size 3-50")
    private String email;

    @NotBlank
    private String password;


    @Enumerated(EnumType.STRING)
    private Set<UserPermissions> permissions = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(UserPermissions::getPermission)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
