import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class MainWindow extends JFrame implements ActionListener
{
private String single_folder_path;
private String folder1_path;
private String folder2_path;
private JLabel display_single_folder_path;
private JLabel display_folder1_path;
private JLabel display_folder2_path;
private JTextArea display_duplicates;
private JButton load_single_folder;
private JButton load_folder1;
private JButton load_folder2;
private JTextField regexFileName;
private SpinnerNumberModel minFileLength;

private JTextArea textPane = new JTextArea();
private JScrollPane scroll = new JScrollPane(textPane);

MainWindow(String s)
{
        super(s);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
}

private JPanel buildContentPane()
{
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel regex_label = new JLabel("Regex file name :");
        regexFileName = new JTextField(".*");
        regexFileName.setMaximumSize( new Dimension(Integer.MAX_VALUE, regexFileName.getPreferredSize().height) );

        JLabel min_label = new JLabel("Minimal file size (byte) :");
        minFileLength = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner min_spinner = new JSpinner(minFileLength);
        min_spinner.setMaximumSize( new Dimension(Integer.MAX_VALUE, min_spinner.getPreferredSize().height) );

        JLabel single_folder = new JLabel("Compare 1 folder :");
        display_single_folder_path = new JLabel("The folder's path");
        load_single_folder = new JButton("Load");
        load_single_folder.addActionListener(this);

        JButton validation1 = new JButton("Compare");
        validation1.addActionListener(this);

        JLabel folders2 = new JLabel("Compare 2 folders : ");
        display_folder1_path = new JLabel("First folder's path");
        load_folder1 = new JButton("Load1");
        load_folder1.addActionListener(this);

        display_folder2_path = new JLabel("Second folder's path");
        load_folder2 = new JButton("Load2");
        load_folder2.addActionListener(this);

        JButton validation2 = new JButton("Compare");
        validation2.addActionListener(this);

        display_duplicates = new JTextArea("here will be the duplicates");

        scroll = new JScrollPane(display_duplicates);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scroll, BorderLayout.CENTER);

        panel.add(min_label);
        panel.add(min_spinner);
        panel.add(Box.createVerticalStrut(15));
        panel.add(regex_label);
        panel.add(regexFileName);
        panel.add(Box.createVerticalStrut(20));
        panel.add(single_folder);
        panel.add(Box.createVerticalStrut(15));
        panel.add(display_single_folder_path);
        panel.add(load_single_folder);
        panel.add(Box.createVerticalStrut(2));
        panel.add(validation1);
        panel.add(Box.createVerticalStrut(15));
        panel.add(folders2);
        panel.add(Box.createVerticalStrut(15));
        panel.add(display_folder1_path);
        panel.add(load_folder1);
        panel.add(display_folder2_path);
        panel.add(load_folder2);
        panel.add(Box.createVerticalStrut(2));
        panel.add(validation2);
        panel.add(Box.createVerticalStrut(15));
        panel.add(scroll);

        return panel;
}

public void actionPerformed(ActionEvent evenement)
{
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
                display_duplicates.setText("Duplicates in "+single_folder_path+"\n");

                Path d1 = Paths.get(single_folder_path);
                FindDuplicateFiles fdf = new FindDuplicateFiles();
                fdf.minFileLength = minFileLength.getNumber().intValue();
                fdf.regexFileName = regexFileName.getText();
                try
                {
                        fdf.find(d1);
                        for (Vector<File> files : fdf.duplicateFiles) {
                                for (File f : files) {
                                        display_duplicates.append(f.toString() + " ; ");
                                }
                                display_duplicates.append("\n");
                        }
                } catch (IOException e)
                {
                        display_duplicates.setText("error : compare 1");
                }
        }

        if (evenement.getActionCommand().equals("Compare (2)")) // Avec 2 dossiers
        {
                folder1_path = display_folder1_path.getText();
                folder2_path = display_folder2_path.getText();
                display_duplicates.setText("Compare "+folder1_path+" and "+folder2_path+"\n");

                Path d1 = Paths.get(folder1_path);
                Path d2 = Paths.get(folder2_path);
                FindDuplicateFiles fdf = new FindDuplicateFiles();
                fdf.minFileLength = minFileLength.getNumber().intValue();
                fdf.regexFileName = regexFileName.getText();
                try
                {
                        fdf.find(d1,d2);
                        for (Vector<File> files : fdf.duplicateFiles) {
                                for (File f : files) {
                                        display_duplicates.append(f.toString() + " ; ");
                                }
                                display_duplicates.append("\n");
                        }
                } catch (IOException e)
                {
                        display_duplicates.setText("error : compare 2");
                }
        }
}
}
