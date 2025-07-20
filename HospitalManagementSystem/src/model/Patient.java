package model;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private boolean emergency;

    public Patient(String name, int age, String gender, String address, String phone, boolean emergency) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.emergency = emergency;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public boolean isEmergency() { return emergency; }
}