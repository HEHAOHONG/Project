import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class GUI extends JFrame
{
    private JPanel editorPanel; //for text area
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel searchPanel;
    private JScrollPane scroller1; //for text area
    private JScrollPane selectPanel;

    private JTextArea editor; //for text area
    private JTextField searchline = new JTextField();

    private JButton loadButton;
    private JButton saveButton;
    private JButton selectButton;
    private JButton searchButton;
    private JButton statisticsButton;

    private Model model = new Model();
    private DefaultListModel<String> listmodel;
    private JList<String> list;

    private JLabel lable = new JLabel("Button:                  " + "Name List:         " + "Display Area:") ;

    private boolean hasfile = false;
    private boolean haspatient = false;


    public GUI() {
        createGUI();
        setModel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void createGUI() {
        setTitle("File loader");
        createEditorPanel();
        createButtonPanel();
        createSelectPanel();
        createSearchPanel();
        createTopPanel();
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 100));
        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        topPanel.add(editorPanel, BorderLayout.EAST);
        topPanel.add(selectPanel, BorderLayout.CENTER);
        topPanel.add(lable,BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(10, 1, 10, 3));
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        createLoadButton();
        createSaveButton();
        createSelectButton();
        createStatisButton();
        createSearchButton();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(selectButton);
        buttonPanel.add(statisticsButton);
        buttonPanel.add(searchButton);
    }

    private void createEditorPanel() {
        editor = new JTextArea();
        editor.setColumns(45);
        editor.setText("load document");
        scroller1 = new JScrollPane(editor);
        editorPanel = new JPanel(new BorderLayout());
        editorPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 3, 3));
        editorPanel.add(scroller1, BorderLayout.CENTER);
    }

    private void createSelectPanel(){
        list = new JList<String>();
        list.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try {
                            listClicked();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
        selectPanel = new JScrollPane(list);
    }

    private void createSearchPanel(){
        searchPanel = new JPanel(new GridLayout(1, 20, 3, 3));
        searchPanel.add(new JLabel("Search bar : "));
        searchPanel.add(searchline);
    }


    private void createLoadButton() {
        loadButton = new JButton("Load...");
        loadButton.addActionListener((ActionEvent e) -> {
            try {
                loadButtonClick();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void createSaveButton() {
        saveButton = new JButton("Save as...");
        saveButton.addActionListener((ActionEvent e) -> saveButtonClick());
    }

    private void createSelectButton(){
        selectButton = new JButton("Names...");
        selectButton.addActionListener((ActionEvent e) -> selectButtonClick());
    }

    private void createSearchButton(){
        searchButton = new JButton("Search...");
        searchButton.addActionListener((ActionEvent e) -> {
            try {
                searchButtonClick();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void createStatisButton(){
        statisticsButton = new JButton("Statistics..");
        statisticsButton.addActionListener((ActionEvent e) -> {
            try {
                statisticsButtonClick();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }


    private void loadButtonClick() throws IOException {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try
            {
                model.read(file.getAbsolutePath());
                editor.read(new FileReader(model.getpatients()), null);
                hasfile = true;
            }
            catch (IOException exp)
            {
                JOptionPane.showMessageDialog(this, "Unable to load the file", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveButtonClick() {
        if (hasfile){
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try
                {
                    editor.write(new FileWriter(file));
                }
                catch (IOException exp)
                {
                    JOptionPane.showMessageDialog(this, "Unable to save the file", "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please load a file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectButtonClick(){
        if (hasfile){
            listmodel.clear();
            haspatient = true;
            for(int i=1; i<=model.getsize(); i++){
                listmodel.addElement(model.getname(i));
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please load a file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listClicked() throws IOException {
        if (hasfile){
            int n = list.getSelectedIndex();
            if (!(n < 0) || (n > listmodel.size()))
            {
                selectButton.setEnabled(true);
            }
            if (haspatient){
                editor.read(new FileReader(model.getpatient(list.getSelectedIndex()+1)), null);
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a name", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please first load a file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchButtonClick() throws IOException {
        if (hasfile){
            editor.read(new FileReader(model.search(searchline.getText())),null);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please load a file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void statisticsButtonClick() throws IOException {
        if (hasfile){
            editor.read(new FileReader(model.getstatistics()),null);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please load a file", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setModel() {
        listmodel = new DefaultListModel<String>();
        listmodel.addElement("Select a patient  ");
        list.setModel(listmodel);
    }


}

