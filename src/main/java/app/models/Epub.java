package app.models;

public class Epub
{
    private boolean isAvailable;
    private String acsTokenLink;
    private String downloadLink;
	
    public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getAcsTokenLink() {
		return acsTokenLink;
	}
	public void setAcsTokenLink(String acsTokenLink) {
		this.acsTokenLink = acsTokenLink;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
    
    
}
