package entity;

public class NotificationDto {
	private String message;
	private long id ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public NotificationDto() {
		// TODO Auto-generated constructor stub
	}
	public NotificationDto(String m , long id ) {
		this.message=m;
		this.id=id;
		// TODO Auto-generated constructor stub
	}


}