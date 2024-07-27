package orm.hibernate.annotation;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;

/**
 * By specify entity annotation with name , hibernate will create table (if it not exists) by
 * the provided name i.e student_address_data
 */
@Entity(name="student_address_data")
public class Address {

	/**
	 * Made this object as primary and
	 * kept auto incremental value for it
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int unique_identifier;
	
	/**
	 * Hibernate will create a column with name first_line_address instead of address1
	 * with max length of 50
	 */
	@Column(name="first_line_address",length = 50)
	private String address1;
	
	@Column(name="second_line_address",length = 50)
	private String address2;
	
	/**
	 * Stores complete timestamp details
	 * in the column name details_creation_date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="details_creation_date")
	private Date stores_timestamp;
	
	/**
	 * Since we are storing timestamp, we don't required 
	 * to store the date again, these parameter may be used
	 * for any other computation purpose
	 */
	@Transient
	private Date stores_date;
	
	/**
	 * Stores student photo
	 */
	@Column(name="student_photo",length = 100000)
	@Lob
	private byte[] photo;
	
	/**
	 * To store Enum types of variables
	 */
    @Enumerated(EnumType.STRING)
    @Column(name="form_status")
	private formStatus form;
    
    public enum formStatus{
    	PENDING,
    	SUBMITED;
    }

	public int getUnique_identifier() {
		return unique_identifier;
	}

	public void setUnique_identifier(int unique_identifier) {
		this.unique_identifier = unique_identifier;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getStores_timestamp() {
		return stores_timestamp;
	}

	public void setStores_timestamp(Date stores_timestamp) {
		this.stores_timestamp = stores_timestamp;
	}

	public Date getStores_date() {
		return stores_date;
	}

	public void setStores_date(Date stores_date) {
		this.stores_date = stores_date;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public formStatus getForm() {
		return form;
		
	}

	public void setForm(formStatus form) {
		this.form = form;
	}

	@Override
	public String toString() {
		return "Address [unique_identifier=" + unique_identifier + ", address1=" + address1 + ", address2=" + address2
				+ ", stores_timestamp=" + stores_timestamp + ", stores_date=" + stores_date + ", photo="
				+ Arrays.toString(photo) + ", form=" + form + "]";
	}	
}
