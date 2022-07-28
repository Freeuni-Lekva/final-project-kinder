package Models;

import java.util.Date;
import java.util.List;

public class User {

    int user_id;


    // during registration
    String mail;
    String first_name;
    Date birth_date;
    String city;
    String gender;

    String genderPref;

    boolean genderIsShown;

    List<String> images;

    //optional during registration
    List<String> hobbies;
    String orientation;

    //optional
    String bio;
    String horoscope;

    String company;
    String job;
    String school;
    boolean show_age;
    boolean show_active;
    int min_age;
    int max_age;


    boolean showActiveStatus; // anu chandes tu ara, rom bolo 24 saatshi viyavi shemosuli an axla var shemosuli
    Date last_session;

    boolean is_premium;
    boolean show_to_liked;

    boolean is_hided;


    boolean is_admin;  // admins tu unda hkodes useri, mashin egec movnishnot
    Date registration_date; // es avtomaturad enicheba bazashi
    int balance; // sawyisi defaultad hkondes 100 lari, rom sheedzlos premmiumis yidva
    String password;  // es gvinda tu yoveljerze meilze ertjeradi kodi gavagzavnot?

    public User(String mail, String first_name, Date birth_date, String city, String gender, String genderPref, boolean genderIsShown, List<String> images, List<String> hobbies, String orientation) {
        this.mail = mail;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.city = city;
        this.gender = gender;
        this.genderPref = genderPref;
        this.genderIsShown = genderIsShown;
        this.images = images;
        this.hobbies = hobbies;
        this.orientation = orientation;
    }

    public User(String mail, String first_name, Date birth_date, String city, String gender, String genderPref, boolean genderIsShown, List<String> images) {
        this.mail = mail;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.city = city;
        this.gender = gender;
        this.genderPref = genderPref;
        this.genderIsShown = genderIsShown;
        this.images = images;
    }

    // აქ ზოგი სეტერე და გეტერი წასაშლელია

    public void addImage(String path){
       //
    }

    public void deleteImage(String path){
        //
    }
    public void addHobbie(){
        //
    }

    public void deleteHobbie(){
        //
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getGenderPref() {
        return genderPref;
    }

    public void setGenderPref(String genderPref) {
        this.genderPref = genderPref;
    }

    public boolean isGenderIsShown() {
        return genderIsShown;
    }

    public void setGenderIsShown(boolean genderIsShown) {
        this.genderIsShown = genderIsShown;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
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

    public boolean isShow_age() {
        return show_age;
    }

    public void setShow_age(boolean show_age) {
        this.show_age = show_age;
    }

    public boolean isShow_active() {
        return show_active;
    }

    public void setShow_active(boolean show_active) {
        this.show_active = show_active;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public boolean isShowActiveStatus() {
        return showActiveStatus;
    }

    public void setShowActiveStatus(boolean showActiveStatus) {
        this.showActiveStatus = showActiveStatus;
    }

    public Date getLast_session() {
        return last_session;
    }

    public void setLast_session(Date last_session) {
        this.last_session = last_session;
    }

    public boolean isIs_premium() {
        return is_premium;
    }

    public void setIs_premium(boolean is_premium) {
        this.is_premium = is_premium;
    }

    public boolean isShow_to_liked() {
        return show_to_liked;
    }

    public void setShow_to_liked(boolean show_to_liked) {
        this.show_to_liked = show_to_liked;
    }

    public boolean isIs_hided() {
        return is_hided;
    }

    public void setIs_hided(boolean is_hided) {
        this.is_hided = is_hided;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
