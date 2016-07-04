package dauphine.ihm;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class JFileChooserInputXml {

	private String path;

	public JFileChooserInputXml() {
		// TODO Auto-generated constructor stub
		JFileChooser fileChooser = new JFileChooser(".");
		FileFilter xmlFilter = new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "XML format only";
			}

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if (f.isDirectory())
					return true;
				if (extension(f).equalsIgnoreCase("xml"))
					return true;
				return false;
			}

			private String extension(File f) {
				int indexFile = f.getName().lastIndexOf('.');
				if (indexFile > 0 && indexFile < f.getName().length() - 1)
					return f.getName().substring(indexFile + 1);
				return "";
			}
		};
		fileChooser.setFileFilter(xmlFilter);
		fileChooser.setDialogTitle("Select your input file");
		int retour = fileChooser.showOpenDialog(null);
		if (retour == JFileChooser.APPROVE_OPTION)
			setPath(fileChooser.getSelectedFile().getAbsolutePath());
		System.out.println(path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * public static void main(String[] args) { new JFileChooserInputXml();
	 * System.out.println(System.getProperty("user.dir") + File.separator); }
	 */

}
