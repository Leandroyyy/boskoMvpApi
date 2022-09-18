package br.com.bosko.boskoapi.entity;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.bosko.boskoapi.dto.request.UserRequestDto;
import br.com.bosko.boskoapi.service.parse.ParseDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_clg_user")
public class User implements UserDetails{

    @Id
    @Column(name = "cd_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_nome", nullable = false)
    private String name;

    @Column(name = "nm_sobrenome", nullable = false)
    private String lastName;

    @Column(name = "nr_telefone", unique = true, nullable = false)
    private Long phone;

    @Column(name = "ds_email", unique = true, nullable = false)
    private String email;

    @Column(name = "ds_senha", nullable = false)
    private String password;

    @Column(name = "ds_genero", nullable = false)
    private Gender gender;

    @Column(name = "dt_nascimento", nullable = false)
    private Date birthDate;

    @Column(name="nr_pontos_experiencias")
    private Long experiencePoints;

    @Column(name="level")
    private Integer level;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_progress")
    private List<Progress> progresses = new ArrayList<Progress>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User(){}

    public User(String name, String lastName, Long phone, String email, String password, Gender gender, Date birthDate, Long experiencePoints, Integer level, List<Progress> progresses) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.experiencePoints = experiencePoints;
        this.level = level;
        this.progresses = progresses;
    }

    public static User of(UserRequestDto dto){
        User user = new User();
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setBirthDate(ParseDate.timeStampToDate(dto.birthDate()));
        user.setGender(dto.gender());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
        if (dto.experiencePoints() == null) {
            user.setExperiencePoints(0L);
        } else {
            user.setExperiencePoints(dto.experiencePoints());
        }
        if (dto.level() == null) {
            user.setLevel(0);
        } else {
            user.setLevel(dto.level());
        }
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Long experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Progress> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<Progress> progresses) {
        this.progresses = progresses;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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
