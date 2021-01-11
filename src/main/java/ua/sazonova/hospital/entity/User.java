package ua.sazonova.hospital.entity;

import ua.sazonova.hospital.entity.enam.Role;

public class User {

    private int id;
    private String email;
    private String password;
    private Role role;
    private boolean isActive;
    private int idMoreInfo;

    public User() {}

    /**
     * All fields constructor
     *
     * @param id         - user id
     * @param email      - user email
     * @param password   - user password
     * @param role       - role of user (could be admin, doctor or patient)
     * @param isActive   - display whether user has received approval from the administrator for registration
     * @param idMoreInfo - id from another table (doctors or patients) by it you can get more information about user
     */
    public User(int id, String email, String password, Role role, boolean isActive, int idMoreInfo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.idMoreInfo = idMoreInfo;
    }

    /**
     * Constructor for registration
     *
     * @param email    - user email
     * @param password - user password
     * @param role     - role of user (could be admin, doctor or patient)
     */
    public User(String email, String password, Role role) {
        this.email = email;
        this.role = role;
        this.password = password;
        setActive(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getIdMoreInfo() {
        return idMoreInfo;
    }

    public void setIdMoreInfo(int idMoreInfo) {
        this.idMoreInfo = idMoreInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", idMoreInfo=" + idMoreInfo +
                '}';
    }
}
