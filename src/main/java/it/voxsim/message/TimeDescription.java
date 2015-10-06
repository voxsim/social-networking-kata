package it.voxsim.message;

public class TimeDescription {
	public static final TimeDescription ZERO_SECOND = new TimeDescription(0, TimeType.SECOND);
	
	private Integer number;
	private TimeType timeType;

	public TimeDescription(Integer number, TimeType timeType) {
		this.number = number;
		this.timeType = timeType;
	}
	
	public String description() {
		String timeDescription = number + " " + timeType.name().toLowerCase();
		
		if(isPlural())
			timeDescription += "s";
		
		return timeDescription;
	}

	private boolean isPlural() {
		return number > 1;
	}

	public boolean isNotZero() {
		return number > 0;
	}
}