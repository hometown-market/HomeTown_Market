package com.example.market.domain.user;

import com.example.market.domain.product.Product;
import com.example.market.domain.wish.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Getter
@RequiredArgsConstructor
@Entity

public class User implements UserDetails {

    @Builder
    public User(String email, String password, String name, String profileImgUrl, String role, String address, String provider, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.role = role;
        this.address = address;
        this.provider = provider;
        this.phone = phone;
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
        return getEmail();
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
    private String provider;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Product> productList;

    @OneToMany(mappedBy = "user")
    private Set<Wish> wishList;

    public void updateData(String newName) {
        this.name = newName;
    }
}




