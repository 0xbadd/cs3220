package cloudDrive;

public class FileEntryBean {
	private String filename;
	private String filepath;
	private int userid;
	private String folderpath;

	public FileEntryBean(String filename, String filepath, int userid, String folderpath) {
		this.filename = filename;
		this.filepath = filepath;
		this.userid = userid;
		this.folderpath = folderpath;
	}

	public String getFilename() {
		return filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public int getUserid() {
		return userid;
	}
	
	public String getFolderpath() {
		return folderpath;
	}
}
