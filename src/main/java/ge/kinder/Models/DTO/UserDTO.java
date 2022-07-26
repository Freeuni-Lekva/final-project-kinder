package ge.kinder.Models.DTO;

import ge.kinder.Models.Hobby;

import java.util.Date;
import java.util.List;

public class UserDTO {

    // ამ კლასის ინფორმაცია ყველასთან შეიძლება გამოჩნდეს

    private int user_id;
    private String first_name;
    private Date birth_date;
    private String city;
    private String gender;
    private List<String> images;

    private  List<Hobby> hobbies;
    private String bio;
    private String horoscope;

    private String company;
    private String job;
    private String school;
    private String mail;

    public UserDTO(int user_id, String first_name, Date birth_date, String city, String gender, List<String> images, List<Hobby> hobbies, String bio, String horoscope, String company, String job, String school,String mail) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.city = city;
        this.gender = gender;
        this.images = images;
        this.hobbies = hobbies;
        this.bio = bio;
        this.horoscope = horoscope;
        this.company = company;
        this.job = job;
        this.school = school;
        this.mail = mail;
    }

    public UserDTO(){

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", birth_date=" + birth_date +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", images=" + images +
                ", hobbies=" + hobbies +
                ", bio='" + bio + '\'' +
                ", horoscope='" + horoscope + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}

