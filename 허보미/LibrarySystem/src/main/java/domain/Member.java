package domain;

import java.time.LocalDateTime;

public class Member {
    private long id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;

    //생성자
    public Member() {}
    public Member(long id, String username, String password, String role, LocalDateTime createdAt) {
        this.id = id; this.username = username; this.password = password;
        this.role = role; this.createdAt = createdAt;
    }
    public Member(String username, String password, String role) {
        this(0, username, password, role, null);
    }

    //getter,setter
    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override public String toString() {
        return "Member{id=" + id + ", username='" + username + "', role='" + role + "', createdAt=" + createdAt + "}";
    }
}
