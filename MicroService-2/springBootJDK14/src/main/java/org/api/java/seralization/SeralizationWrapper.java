package org.api.java.seralization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class SeralizationWrapper implements Serializable, Externalizable {

	private static final long serialVersionUID = 8512409228885604020L;
	static int minAge;

	static {
		System.out.println("Static Block in Initalized...");
		setMinAge(18);
	}

	{
		bool = Boolean.TRUE;
		count = 10000;
	}

	int age;
	private String fname;
	protected String mname;
	public String lname;
	transient int maxAge;
	final int count;

	transient final Boolean bool;

	public SeralizationWrapper() {
	}

	public SeralizationWrapper(int age, String fname, String mname, String lname, int maxAge) {
		super();
		this.age = age;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.maxAge = maxAge;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(fname);
		out.writeObject(lname);
		out.writeBoolean(bool);
		out.writeLong(serialVersionUID);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		fname = in.readObject().toString();
		lname = in.readObject().toString();
	}

	public static int getMinAge() {
		return minAge;
	}

	public static void setMinAge(int minAge) {
		SeralizationWrapper.minAge = minAge;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeralizationWrapper [age=");
		builder.append(age);
		builder.append(", minAge=");
		builder.append(minAge);
		builder.append(", serialVersionUID=");
		builder.append(serialVersionUID);
		builder.append(", fname=");
		builder.append(fname);
		builder.append(", mname=");
		builder.append(mname);
		builder.append(", lname=");
		builder.append(lname);
		builder.append(", count=");
		builder.append(count);
		builder.append(", bool=");
		builder.append(bool);
		builder.append("]");
		return builder.toString();
	}

}
