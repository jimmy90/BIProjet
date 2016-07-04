package dauphine.ihm;

import javax.swing.JFileChooser;

public class JDirectoryChooserOutputXml {

	private String path;

	public JDirectoryChooserOutputXml() {
		// TODO Auto-generated constructor stub
		JFileChooser chooser = new JFileChooser(".");
		chooser.setDialogTitle("Select output directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			setPath(chooser.getSelectedFile().getAbsolutePath());
		}
		System.out.println(path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * public static void main(String[] args) { new
	 * JDirectoryChooserOutputXml(); }
	 */
}
