package dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import entity.Group;
import entity.User;
import exceptions.FieldNotPresentException;

public class UserDTO {
    
    @NotEmpty(message = "Email obbligatoria")
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Formato e-mail non valido")
    private String mail;
    @NotEmpty(message = "Password obbligatoria")
    private String password;
    @NotEmpty(message = "Nome obbligatorio")
    private String firstName;
    @NotEmpty(message = "Cognome obbligatorio")
    private String lastName;
    
    private String group;

    public UserDTO() {
    }

    public UserDTO(User user) throws FieldNotPresentException {
	this.mail = user.getMail();
	this.password = null;
	this.firstName = user.getFirstName();
	this.lastName = user.getLastName();

	List<Group> groups = user.getGroups();
	if (groups != null && groups.size() >= 1) {
	    this.group = groups.get(0).getId();
	} else {
	    System.err.println("groups not found");
	    //throw new FieldNotPresentException();
	    this.group = "TDF";
	}
    }

    public UserDTO(String mail, String password, String firstName, String lastName, String group) {
	super();
	this.mail = mail;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.group = group;
    }

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getGroup() {
	return group;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    @Override
    public String toString() {
	return "UserDTO [mail=" + mail + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", group=" + group + "]";
    }
}