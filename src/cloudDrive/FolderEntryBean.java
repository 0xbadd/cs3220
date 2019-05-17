package cloudDrive;

public class FolderEntryBean {
	private int userid;
	private String foldername;
	private String parentname;

	public FolderEntryBean(int userid, String foldername, String parentname) {
		this.userid = userid;
		this.foldername = foldername;
		this.parentname = parentname;
	}

	public int getUserid() {
		return userid;
	}

	public String getFoldername() {
		return foldername;
	}

	public String getParentname() {
		return parentname;
	}
}
