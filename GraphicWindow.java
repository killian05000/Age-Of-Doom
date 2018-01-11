import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JTextField;

public class GraphicWindow extends JFrame implements ActionListener
{
  private String folder1_path;
  private String folder2_path;
  private JLabel display_folder1_path;
  private JLabel display_folder2_path;
	private JButton load_folder1;
  private JButton load_folder2;

  GraphicWindow(String s)
  {
    super(s);
    setSize(600,400);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JMenuBar menuBar = new JMenuBar();
    JMenuItem item;
    setJMenuBar(menuBar);

    JMenu menu = new JMenu("Folder");
    menuBar.add(menu);

      item = new JMenuItem("Load");
      menu.add(item);
      item.addActionListener(this);

      item = new JMenuItem("Save");
      menu.add(item);
      item.addActionListener(this);
      menu.add(new JSeparator());

      item = new JMenuItem("Quit");
      menu.add(item);
      item.addActionListener(this);

    setContentPane(buildContentPane());
  }

  private JPanel buildContentPane()
  {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
  panel.add(Box.createRigidArea(new Dimension(0,5)));
  panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


  display_folder1_path = new JLabel("First folder's path");
  load_folder1 = new JButton("Load1");
  load_folder1.addActionListener(this);

  display_folder2_path = new JLabel("First folder's path");
  load_folder2 = new JButton("Load2");
  load_folder2.addActionListener(this);

  JButton validation = new JButton("ok");
  validation.addActionListener(this);

  JLabel display_duplicates = new JLabel("here will be the duplicates");


	panel.add(display_folder1_path);
  panel.add(load_folder1);
  panel.add(display_folder2_path);
  panel.add(load_folder2);
  panel.add(validation);
  panel.add(display_duplicates);

	return panel;
  }

  public void actionPerformed(ActionEvent evenement)
  {
              ///////// MENU FILE /////////

    if (evenement.getActionCommand().equals("Load1"))
    {
      JFileChooser selector = new JFileChooser();
      selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        File file = selector.getSelectedFile();
        display_folder1_path.setText(file.getPath());
        System.out.println("Chosen file : " + file);
      }
    }

    if (evenement.getActionCommand().equals("Load2"))
    {
      JFileChooser selector = new JFileChooser();
      selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        File file = selector.getSelectedFile();
        display_folder2_path.setText(file.getPath());
        System.out.println("Chosen file : " + file);
      }
    }

    if (evenement.getActionCommand().equals("ok"))
    {
      folder1_path = display_folder1_path.getText();
      folder2_path = display_folder2_path.getText();
      System.out.println(folder1_path+" # "+folder2_path);
      display_duplicates.setText("les voici : ");
    }

    if (evenement.getActionCommand().equals("Save"))
    {
      System.out.println("MENU SAVE");
    }
    if (evenement.getActionCommand().equals("Quit"))
    {
      System.out.println("MENU QUIT ATTEMPT");
      int result = JOptionPane.showConfirmDialog(null, "Voulez vous quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
      if( result == JOptionPane.YES_OPTION )
        {
          System.out.println("MENU QUIT");
          System.exit(0);
        }
    }
  }
}
