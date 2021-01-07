package ua.sazonova.hospital.entity;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.entity.enam.Role;

public class User {
    private static int ENCODING_STRENGTH = 12;

    private int id;
    private String email;
    private String password;
    private Role role;
    private boolean isActive;
    private int idMoreInfo;

    public User() {
    }

    public User(int id, String email, String password, Role role, boolean isActive, int idMoreInfo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.idMoreInfo = idMoreInfo;
    }

    public User(String email, String password, Role role){
        this.email = email;
        this.role = role;
        setPassword(password);
        setActive(false);
    }

    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(ENCODING_STRENGTH));
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
        this.password = hashPassword(password);
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
