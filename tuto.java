import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Test2 extends JFrame implements ActionListener {
	private JTextField jtf_path;
	private JButton jb_browse;

	public Test2() {
		super();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 60);
		setResizable(false);
		setLocationRelativeTo(null);

		jtf_path = new JTextField(40);
		jb_browse = new JButton("...");
			jb_browse.addActionListener(this);

		add(jtf_path);
		add(jb_browse);
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            jtf_path.setText(file.getPath());
        }

	}

	public static void main(String[] args) {
		new Test2().setVisible(true);
	}
}
