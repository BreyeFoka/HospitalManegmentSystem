package staff;

public class Staff {
    private int id;
    private String name, role, specialization, phone, address;

    public Staff(String name, String role, String specialization, String phone, String address) {
        this.name = name;
        this.role = role;
        this.specialization = specialization;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getSpecialization() { return specialization; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}