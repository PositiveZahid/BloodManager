package business;

/**
 * Created by User on 9/30/2015.
 */
public class DonorTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String bloodGroup;
    private Integer age;
    private Boolean donationStatus;
    private Integer numberOfTimesDonated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNumberOfTimesDonated() {
        return numberOfTimesDonated;
    }

    public void setNumberOfTimesDonated(Integer numberOfTimesDonated) {
        this.numberOfTimesDonated = numberOfTimesDonated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(Boolean donationStatus) {
        this.donationStatus = donationStatus;
    }

}
