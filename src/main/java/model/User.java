package model;
import java.time.LocalDate;
import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
        return "User{" + "id=" + id +  '\'' + ", email='" + email + '\''
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
        return id.equals(user.id) && email.equals(user.email) && firstName
                .equals(user.firstName) && lastName.equals(user.lastName)
                && birthDate.equals(user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName,
                birthDate);
    }
}