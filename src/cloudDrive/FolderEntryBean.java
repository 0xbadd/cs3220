package cloudDrive;

public class FolderEntryBean {
	private int userid;
	private String foldername;
	private String parentpath;
	private int parentid;

	public FolderEntryBean(int userid, String foldername, String parentpath, int parentid) {
		this.userid = userid;
		this.foldername = foldername;
		this.parentid = parentid;
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

	public int getParentid() {
		return parentid;
	}
}
