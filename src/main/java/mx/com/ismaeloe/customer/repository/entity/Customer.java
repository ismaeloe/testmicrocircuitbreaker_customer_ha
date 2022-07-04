package mx.com.ismaeloe.customer.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

//import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
//@Entity
//@Table(name="tbl_customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1567471097587462313L;

//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El número de documento no puede ser vacío")
    @Size( min = 8 , max = 8, message = "El tamaño del número de documento es 8")
//    @Column(name = "number_id" , unique = true ,length = 8, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede ser vacío")
//    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede ser vacío")
//    @Column(name="last_name", nullable=false)
    private String lastName;

    @NotEmpty(message = "el correo no puede estar vacío")
    @Email(message = "no es un dirección de correo bien formada")
//    @Column(unique=true, nullable=false)
    private String email;

//    @Column(name="photo_url")
    private String photoUrl;

    @NotNull(message = "la región no puede ser vacia")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberID() {
		return numberID;
	}

	public void setNumberID(String numberID) {
		this.numberID = numberID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
