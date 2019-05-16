package cloudDrive;

public class FileEntryBean {
	private int id;
	private String filename;
	private String filepath;
	private int userid;

	public FileEntryBean(int id, String filename, String filepath, int userid) {
		this.id = id;
		this.filename = filename;
		this.filepath = filepath;
		this.userid = userid;
	}

	public int getId() {
		return id;
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
