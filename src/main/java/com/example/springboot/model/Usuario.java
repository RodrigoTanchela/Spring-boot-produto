package com.example.springboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class Usuario implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", unique = true, nullable = true, length = 255)
	private String userName;
	
	@Column (name = "full_name")
	private String fullName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;
	
	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;
	
	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	public Usuario() {
		super();
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissoes", joinColumns= {@JoinColumn (name = "id_usuario")},
			inverseJoinColumns = {@JoinColumn (name = "id_permissao")})
	private List<Permissao> permissions;
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		for(Permissao permissao : permissions) {
			roles.add(permissao.getDescription());
		}
		return roles;
	}
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}



	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}



	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}



	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}



	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}



	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, fullName, id, password,
				userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialsNonExpired, other.credentialsNonExpired)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName);
	}
	
	

}
