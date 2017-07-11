package de.fom.wp.view;

/*
 * Hilfsklasse f�r Fehlermeldungen. Wird ben�tigt um Fehler passend zum Formularelement anzuzeigen.
 */
public class Message {

	private String field;
	private String message;

	public Message(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
