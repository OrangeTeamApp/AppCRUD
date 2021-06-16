package model;
import java.time.LocalDate;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void asDto(User user) {
        this.setEmail(user.getEmail());
        this.setFirstName(user.getEmail());
        this.setLastName(user.getLastName());
        this.setBirthDate(user.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }



    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login='" + login + '\''
                + ", password='" + password + '\'' + ", email='" + email + '\''
                + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + ", birthDate=" + birthDate  + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && password
                .equals(user.password) && email.equals(user.email) && firstName
                .equals(user.firstName) && lastName.equals(user.lastName)
                && birthDate.equals(user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, firstName, lastName,
                birthDate);
    }
}