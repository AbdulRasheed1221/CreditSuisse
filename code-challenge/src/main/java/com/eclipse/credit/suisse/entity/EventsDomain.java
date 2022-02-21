package com.eclipse.credit.suisse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS_LOG")
public class EventsDomain {
	
	@Id
	@Column(name = "EVENT_ID")
	private String eventID;
	
	@Column(name = "DURATION")
	private long duration;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "HOST")
	private String host;
	
	@Column(name = "ALERT")
	private boolean alert;
	
	
	public String getEventID() {
		return eventID;
	}




	public void setEventID(String eventID) {
		this.eventID = eventID;
	}




	public long getDuration() {
		return duration;
	}




	public void setDuration(long duration) {
		this.duration = duration;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getHost() {
		return host;
	}




	public void setHost(String host) {
		this.host = host;
	}




	public boolean isAlert() {
		return alert;
	}




	public void setAlert(boolean alert) {
		this.alert = alert;
	}




	@Override
	public String toString() {
		return "EventsDomain [eventID=" + eventID + ", duration=" + duration + ", type=" + type + ", host=" + host
				+ ", alert=" + alert + "]";
	}

}
