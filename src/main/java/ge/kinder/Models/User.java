package ge.kinder.Models;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {
    public static final String USER_TABLE = "kinder_base.user";
    public static final String USER_USER_ID = "user_id" ;
    public static final String USER_MAIL = "Mail";
    public static final String USER_FIRST_NAME = "First_Name";
    public static final String USER_BIRTH_DATE = "Birth_Date";
    public static final String USER_CITY = "City";
    public static final String USER_GENDER = "Gender";
    public static final String USER_SHOW_GENDER = "Show_Gender";
    public static final String USER_PREFERENCE = "Preference";
    public static final String USER_ORIENTATION = "Orientation";
    public static final String USER_BIO = "Bio";
    public static final String USER_HOROSCOPE = "Horoscope";
    public static final String USER_COMPANY = "Company";
    public static final String USER_JOB = "Job";
    public static final String USER_SCHOOL = "School";
    public static final String USER_MIN_AGE = "Min_Age";
    public static final String USER_MAX_AGE = "Max_Age";
    public static final String USER_REGISTRATION_DATE = "Registration_Date";
    public static final String USER_SHOW_ACTIVE = "Show_Active";
    public static final String USER_LAST_Session = "Last_Session";
    public static final String USER_HIDED = "Hided";
    public static final String USER_ROLE= "Role";
    public static final String USER_BALANCE = "Balance";
    public static final String SHOT_TO_LIKED = "Show_To_Liked";


    private int user_id;

    private Role role;

    // during registration
    private String mail;
    private String first_name;
    private  java.sql.Date birth_date;
    private String city;
    private String gender;
    private String genderPref;

    private int genderIsShown;

    private  List<String> images;

    //optional during registration
    private List<Hobby> hobbies;
    private String orientation;

    //optional
    private String bio;
    private String horoscope;

    private String company;
    private String job;
    private String school;


    private int min_age;
    private  int max_age;


    private int showActiveStatus; // anu chandes tu ara, rom bolo 24 saatshi viyavi shemosuli an axla var shemosuli
    private java.sql.Date last_session;


    private  int show_to_liked;

    private int is_hided;



    private java.sql.Date registration_date; // es avtomaturad enicheba bazashi
    private int balance; // sawyisi defaultad hkondes 100 lari, rom sheedzlos premmiumis yidva


    public User(String mail, String first_name, java.sql.Date birth_date, String city, String gender, String genderPref, int genderIsShown, List<String> images, List<Hobby> hobbies, String orientation) {
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

    public User(String mail, String first_name, java.sql.Date birth_date, String city, String gender, String genderPref, int genderIsShown, List<String> images) {
        this.mail = mail;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.city = city;
        this.gender = gender;
        this.genderPref = genderPref;
        this.genderIsShown = genderIsShown;
        this.images = images;
    }


    public User(int user_id, String role, String mail, String first_name, Date birth_date, String city, String gender, String genderPref, int genderIsShown, List<String> images, List<Hobby> hobbies, String orientation, String bio, String horoscope, String company, String job, String school, int min_age, int max_age, int showActiveStatus, Date last_session, int show_to_liked, int is_hided, Date registration_date, int balance) {
        this.user_id = user_id;
        this.role = Role.valueOf(role);
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
        this.bio = bio;
        this.horoscope = horoscope;
        this.company = company;
        this.job = job;
        this.school = school;
        this.min_age = min_age;
        this.max_age = max_age;
        this.showActiveStatus = showActiveStatus;
        this.last_session = last_session;
        this.show_to_liked = show_to_liked;
        this.is_hided = is_hided;
        this.registration_date = registration_date;
        this.balance = balance;
    }

    public User(){

    }

    public String getRole() {

        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
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

    public java.sql.Date getBirth_date() {
        return (java.sql.Date) birth_date;
    }

    public void setBirth_date(java.sql.Date birth_date) {
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

    public int isGenderIsShown() {
        return genderIsShown;
    }

    public void setGenderIsShown(int genderIsShown) {
        this.genderIsShown = genderIsShown;
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

    public void setHobbies(ArrayList<Hobby> hobbies) {
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

    public int isShowActiveStatus() {
        return showActiveStatus;
    }

    public void setShowActiveStatus(int showActiveStatus) {
        this.showActiveStatus = showActiveStatus;
    }

    public java.sql.Date getLast_session() {

        return last_session;
    }

    public void setLast_session(java.sql.Date last_session) {
        this.last_session = last_session;
    }



    public int isShow_to_liked() {
        return show_to_liked;
    }

    public void setShow_to_liked(int show_to_liked) {
        this.show_to_liked = show_to_liked;
    }

    public int isIs_hided() {
        return is_hided;
    }

    public void setIs_hided(int is_hided) {
        this.is_hided = is_hided;
    }



    public java.sql.Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(java.sql.Date registration_date) {
        this.registration_date = registration_date;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


}
