package washo.gmd.app.shared;

import java.io.Serializable;

public class Game implements Serializable  {
	
	public Game() {
		gameID = 0;
		gameOwner = 0;
		title = "";
		date = 0L;
		time = "";
		coordinates = "";
		locationInfo = "";
		fieldType = 0;
	}
	
	private Integer gameID;
	private Integer gameOwner;
	private String title;
	private Long date;
	private String time;
	private String coordinates;
	private String locationInfo;
	private Integer fieldType;
	
	public Integer getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public Integer getGameOwner() {
		return gameOwner;
	}
	public void setGameOwner(int gameOwner) {
		this.gameOwner = gameOwner;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long time2) {
		this.date = time2;
		
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	
	
}