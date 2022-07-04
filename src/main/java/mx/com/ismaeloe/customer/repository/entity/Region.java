package mx.com.ismaeloe.customer.repository.entity;

import lombok.Data;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
//@Entity
//@Table(name = "tbl_regions") //TBL_REGIONS
public class Region implements Serializable {

	private static final long serialVersionUID = 256118028932700658L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		Region other = (Region) obj;
		return Objects.equals(id, other.id);
	}

	
}
