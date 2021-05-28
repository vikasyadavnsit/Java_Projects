package org.api.wrapper.patterns;

public class PhoneWrapper {

	private String buildVersion;
	private String model;
	private String os;
	private String processor;
	private int ram;

	public PhoneWrapper() {

	}

	private PhoneWrapper(PhoneWrapperBuilder builder) {
		this.buildVersion = builder.buildVersion;
		this.model = builder.model;
		this.os = builder.os;
		this.processor = builder.processor;
		this.ram = builder.ram;
	}

	public static PhoneWrapperBuilder builder() {
		return new PhoneWrapperBuilder();
	}

	public static class PhoneWrapperBuilder {
		private String buildVersion;
		private String model;
		private String os;
		private String processor;
		private int ram;

		public PhoneWrapperBuilder buildVersion(String buildVersion) {
			this.buildVersion = buildVersion;
			return this;
		}

		public PhoneWrapper build() {
			PhoneWrapper wrapper = new PhoneWrapper(this);
			return wrapper;
		}

		public PhoneWrapperBuilder model(String model) {
			this.model = model;
			return this;
		}

		public PhoneWrapperBuilder os(String os) {
			this.os = os;
			return this;
		}

		public PhoneWrapperBuilder processor(String processor) {
			this.processor = processor;
			return this;
		}

		public PhoneWrapperBuilder ram(int ram) {
			this.ram = ram;
			return this;
		}

	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	@Override
	public String toString() {
		return "PhoneWrapper [buildVersion=" + buildVersion + ", model=" + model + ", os=" + os + ", processor="
				+ processor + ", ram=" + ram + "]";
	}

}
