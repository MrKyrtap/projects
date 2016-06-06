import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//http://wklej.to/U91mx

/**
 * Created by student on 2016-04-23.
 */
public class MainWindow extends JFrame {
    private JButton addButton;
    private JButton delButton;
    private JTextField tf_name;
    private JTextField tf_phone;
    private JComboBox cb_category;
    private JList list1;
    private JPanel namePanel;
    private JLabel Płec;
    private JComboBox cb_plec;
    private JTextField tf_mail;
    private JButton bt_edit;

    public void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu saveMenu = new JMenu("save");
        JMenu loadMenu = new JMenu("load");

        JMenuItem exportToText = new JMenuItem("Ex to txt");
        JMenuItem saveTo = new JMenuItem("Save");
        saveTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jc = new JFileChooser();
                FileNameExtensionFilter filtr = new FileNameExtensionFilter("TExt files", "txt");
                jc.setFileFilter(filtr);
                if (jc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = jc.getSelectedFile().getAbsolutePath() + ".txt";
                    //zapis
                }
                if(jc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    String path = jc.getSelectedFile().getAbsolutePath();
                }

            }
        });
        saveTo.setMnemonic('S');
        saveTo.setIcon(new ImageIcon("resources/img/img.gif"));
        saveTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
        saveMenu.add(exportToText);
        saveMenu.add(saveTo);
        fileMenu.add(saveMenu);
        JMenuItem importFromTxt = new JMenuItem("Im from txt");
        JMenuItem loadFrom = new JMenuItem("Load");

        loadMenu.add(importFromTxt);
        loadMenu.add(loadFrom);
        fileMenu.add(loadMenu);

        this.setJMenuBar(menuBar);
    }


    public MainWindow() {
        setContentPane(namePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenu();
        pack();

        bt_edit.setEnabled(false);
        cb_category.setModel(new DefaultComboBoxModel<>(Category.values()));
        cb_plec.setModel(new DefaultComboBoxModel<>(Plec.values()));
        ContactModel model = new ContactModel();
        list1.setModel(model);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = tf_name.getText();
                Long phone = Long.parseLong(tf_phone.getText());
                Category cat = (Category) cb_category.getSelectedItem();
                String mail = tf_mail.getText();
                Plec plec = (Plec) cb_plec.getSelectedItem();
                Contact c = new Contact(name, phone, mail, plec, cat);
                ContactModel model = (ContactModel) list1.getModel();
                model.add(c);
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                bt_edit.setEnabled(true);
                Contact c = (Contact) list1.getSelectedValue();
                tf_name.setText(c.getName());
                tf_phone.setText(String.valueOf(c.getOphone()));
                cb_category.setSelectedItem(c.getCategory());
                cb_plec.setSelectedItem((c.getPlec()));
                tf_mail.setText(c.getEmail());
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeContact();
            }
        });

        bt_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editContact();
            }
        });
        list1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int result = JOptionPane.showConfirmDialog(null, "Jesteś pewmy?");
                    if (result == JOptionPane.OK_OPTION) {
                        removeContact();
                    }
                }
            }
        });
    }

    private void removeContact() {
        ContactModel model = (ContactModel) list1.getModel();
        Contact c = (Contact) list1.getSelectedValue();
        model.remove(c);
    }

    private void editContact() {
        ContactModel model = (ContactModel) list1.getModel();
        Contact c = (Contact) list1.getSelectedValue();

        String name = tf_name.getText();
        Long phone = Long.parseLong(tf_phone.getText());
        Category cat = (Category) cb_category.getSelectedItem();
        String mail = tf_mail.getText();
        Plec plec = (Plec) cb_plec.getSelectedItem();

        c.setCategory(cat);
        c.setEmail(mail);
        c.setName(name);
        c.setPhone(phone);
        c.setPlec(plec);
    }


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}
