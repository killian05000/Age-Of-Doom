import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class GraphicWindow extends JFrame implements ActionListener
{
  private String single_folder_path;
  private String folder1_path;
  private String folder2_path;
  private JLabel display_single_folder_path;
  private JLabel display_folder1_path;
  private JLabel display_folder2_path;
  private JLabel display_duplicates;
  private JButton load_single_folder;
	private JButton load_folder1;
  private JButton load_folder2;

  private JTextArea textPane = new JTextArea();
  private JScrollPane scroll = new JScrollPane(textPane);

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

  JLabel space_line = new JLabel(" ");
  JLabel single_folder = new JLabel("Compare 1 folder :");
  display_single_folder_path = new JLabel("The folder's path");
  load_single_folder = new JButton("Load");
  load_single_folder.addActionListener(this);

  JButton validation1 = new JButton("Compare (1)");
  validation1.addActionListener(this);

  JLabel folders2 = new JLabel("Compare 2 folders : ");
  display_folder1_path = new JLabel("First folder's path");
  load_folder1 = new JButton("Load1");
  load_folder1.addActionListener(this);

  display_folder2_path = new JLabel("First folder's path");
  load_folder2 = new JButton("Load2");
  load_folder2.addActionListener(this);

  JButton validation2 = new JButton("Compare (2)");
  validation2.addActionListener(this);

  display_duplicates = new JLabel("here will be the duplicates");

  this.getContentPane().add(scroll, BorderLayout.CENTER);
  //this.getContentPane().add(BorderLayout.SOUTH);

  panel.add(single_folder);
  panel.add(space_line);
  panel.add(display_single_folder_path);
  panel.add(load_single_folder);
  panel.add(validation1);
  
  panel.add(space_line);
  panel.add(space_line);

  panel.add(folders2);
  panel.add(space_line);
	panel.add(display_folder1_path);
  panel.add(load_folder1);
  panel.add(display_folder2_path);
  panel.add(load_folder2);
  panel.add(validation2);

  panel.add(space_line);
  panel.add(space_line);

  panel.add(display_duplicates);

	return panel;
  }

  public void actionPerformed(ActionEvent evenement)
  {
              ///////// MENU FILE /////////

    if (evenement.getActionCommand().equals("Load"))
    {
      JFileChooser selector = new JFileChooser();
      selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        File file = selector.getSelectedFile();
        display_single_folder_path.setText(file.getPath());
        System.out.println("Chosen file : " + file);
      }
    }

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

    if (evenement.getActionCommand().equals("Compare (1)")) // Avec 1 dossier
    {
      single_folder_path = display_single_folder_path.getText();
      System.out.println("Compare 1 : "+single_folder_path);
      display_duplicates.setText("les voici : ");

      //Path d1 = Paths.get(single_folder_path);
      //FindDuplicateFiles fdf = new FindDuplicateFiles();
      // try
      // {
      //   fdf.find(d1);
      // } catch (IOException e)
      // {
      //    System.out.println("WOOOOOOO error : compare 1");
      // }
    }

    if (evenement.getActionCommand().equals("Compare (2)")) // Avec 2 dossiers
    {
      folder1_path = display_folder1_path.getText();
      folder2_path = display_folder2_path.getText();
      System.out.println("Compare 2 : "+folder1_path+" # "+folder2_path);
      display_duplicates.setText("les voici : ");

      //Path d1 = Paths.get(folder1_path);
      //Path d2 = Paths.get(folder2_path);
      //FindDuplicateFiles fdf = new FindDuplicateFiles();
      // try
      // {
      //   fdf.find(d1,d2);
      // } catch (IOException e)
      // {
      //    System.out.println("WOOOOOOO error : compare 2");
      // }
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
