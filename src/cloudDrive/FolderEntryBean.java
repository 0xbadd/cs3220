package cloudDrive;

public class FolderEntryBean {
	private int userid;
	private String foldername;
	private String parentpath;

	public FolderEntryBean(int userid, String foldername, String parentpath) {
		this.userid = userid;
		this.foldername = foldername;
		this.parentpath = parentpath;
	}

	public int getUserid() {
		return userid;
	}

	public String getFoldername() {
		return foldername;
	}

	public String getParentpath() {
		return parentpath;
	}

}
