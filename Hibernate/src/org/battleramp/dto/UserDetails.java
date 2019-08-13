package org.battleramp.dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "USER_DETAILS")
@Table(name = "USER_DETAILS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@NamedQuery(name = "UserDetails.byID", query = "from USER_DETAILS where USER_ID >  : userID ")
@NamedNativeQuery(name = "UserDetails.byName", query = "select * from USER_DETAILS where USER_NAME = ?", resultClass = UserDetails.class)
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

//	@ManyToMany (cascade = CascadeType.PERSIST)
//	private Collection<Vehicle> vehicleList = new ArrayList<>();

//	@OneToOne
//	@JoinColumn (name ="VEHICLE_ID")
//	private Vehicle vehicle;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
//	private Collection<Address> listOfAddresses = new ArrayList<>();

//	@ElementCollection
//	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
//	@GenericGenerator(name="sequence-gen",strategy="increment")
//	@CollectionId(columns = { @Column(name = "ADDRESS_ID") }, generator = "sequence-gen", type = @Type(type = "long"))
//	private Collection<Address> listOfAddresses = new ArrayList<>();

//	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET_NAME")),
//			@AttributeOverride(name = "city", column = @Column(name = "HOME_CITY_NAME")),
//			@AttributeOverride(name = "state", column = @Column(name = "HOME_STATE_NAME")),
//			@AttributeOverride(name = "pincode", column = @Column(name = "HOME_PINCODE_NAME")), })
//	private Address homeaddress;
//	private Address officeaddress;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
