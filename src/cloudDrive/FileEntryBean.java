package cloudDrive;

public class FileEntryBean {
	private String filename;
	private String filepath;
	private int userid;

	public FileEntryBean(String filename, String filepath, int userid) {
		this.filename = filename;
		this.filepath = filepath;
		this.userid = userid;
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
}
