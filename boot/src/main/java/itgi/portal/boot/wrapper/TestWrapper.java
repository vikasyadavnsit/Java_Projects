package itgi.portal.boot.wrapper;

public class TestWrapper {

	private int id;
	private String fname;

	public int getId() {
		return id;
	}

	public TestWrapper(int id, String fname) {
		super();
		this.id = id;
		this.fname = fname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
