package it.aulab.progetto_blog.dtos;

public class AuthorDto {
 private long id;
 private String name;  
 private String surname;
 private String email;
 private String fullName;
 private Integer NumberOfPosts;
 
 public AuthorDto() {
 }
 public long getId() {
    return id;
 }
 public void setId(long id) {
    this.id = id;
 }
 public String getName() {
    return name;
 }
 public void setName(String name) {
    this.name = name;
 }
 public String getSurname() {
    return surname;
 }
 public void setSurname(String surname) {
    this.surname = surname;
 }
 public String getEmail() {
    return email;
 }
 public void setEmail(String email) {
    this.email = email;
 }
 public String getFullName() {
    return fullName;
 }
 public void setFullName(String fullName) {
    this.fullName = fullName;
 }
 public Integer getNumberOfPosts() {
    return NumberOfPosts;
 }
 public void setNumberOfPosts(Integer numberOfPosts) {
    NumberOfPosts = numberOfPosts;
 }
}
