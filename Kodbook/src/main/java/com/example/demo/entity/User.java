package com.example.demo.entity;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Username cannot be empty")
	@Size(min = 3,max=20,message = "Insert the username size between 3-20 character")
	private String username;
	@Email(regexp ="^[A-Za-z0-9+_.-]+@(.+)$",message = "Invalid email")
	private String email;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Enter strong pasword")
	private String password;
	@NotBlank(message = "This field cannot be empty")
	private String dob;
	@NotBlank(message = "This field cannot be empty")
	private String gender;
	
	private String city;
	
	private String bio;
	
	private String college;
	
	private String linkedin;
	
	private String github;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilephoto;

	public String getPhotoBase64() {
        if (profilephoto == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(profilephoto);
    }
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, String email, String password, String dob, String gender, String city,
			String bio, String college, String linkedin, String github, List<Post> posts, byte[] profilephoto) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.city = city;
		this.bio = bio;
		this.college = college;
		this.linkedin = linkedin;
		this.github = github;
		this.posts = posts;
		this.profilephoto = profilephoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public byte[] getProfilephoto() {
		return profilephoto;
	}

	public void setProfilephoto(byte[] profilephoto) {
		this.profilephoto = profilephoto;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", dob="
				+ dob + ", gender=" + gender + ", city=" + city + ", bio=" + bio + ", college=" + college
				+ ", linkedin=" + linkedin + ", github=" + github + ", posts=" + posts + ", profilephoto="
				+ Arrays.toString(profilephoto) + "]";
	}

	
	
}
