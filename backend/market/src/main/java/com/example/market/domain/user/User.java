package com.example.market.domain.user;

import com.example.market.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Getter
@RequiredArgsConstructor
@Entity
public class User implements UserDetails {

    @Builder
    public User(String email, String password, String name, String role, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.address = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        this.getRoleList().forEach(r -> authorities.add(() -> r));
        System.out.println("user.getRoleList() = " + this.getRoleList());
        return authorities;
    }

    private List<String> getRoleList() {
        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));

        }
        return new ArrayList<>();

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    private String email;
    private String password;
    private String name;
    private String profileImgUrl;
    private String role;
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Product> productList;

}




