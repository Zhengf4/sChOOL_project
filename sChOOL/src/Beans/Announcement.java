package Beans;

import org.joda.time.DateTime;

public class Announcement {
	private int announcementId;
	private String announcement;
	private DateTime dateIssued;
	
	public Announcement() {}
	
	public Announcement(int announcementId, String announcement, DateTime dateIssued) {
		super();
		this.announcementId = announcementId;
		this.announcement = announcement;
		this.dateIssued = dateIssued;
	}
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public DateTime getDateIssued() {
		return dateIssued;
	}
	public void setDateIssued(DateTime dateIssued) {
		this.dateIssued = dateIssued;
	}
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	
}
