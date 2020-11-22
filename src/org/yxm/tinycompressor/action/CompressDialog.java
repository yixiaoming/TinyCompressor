package org.yxm.tinycompressor.action;

import com.tinify.Tinify;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CompressDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField inputKey;
    private String mPath;
    private String mKey;

    public CompressDialog(String path) {
        mPath = path;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
        mKey = inputKey.getText().trim();
        Tinify.setKey(mKey);
        startCompress(mPath);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Tinify.setKey("GYxI6r3XnvHhmDMgllpjivPrlnYAFIg5");
        actionPerformed();
    }

    static void actionPerformed() {
        String path = "/home/yxm/Codes/idea/TinyCompressor";
        startCompress(path);
    }

    private static void startCompress(String path) {
        File file = new File(path);
        if (file.isFile()) {
            if (path.endsWith(".png")) {
                try {
                    System.out.println("start compress:" + path);
                    Tinify.fromFile(path).toFile(path);
                    System.out.println("end compress:" + path);
                } catch (IOException e) {
                    System.out.println("failed compress:" + path + ", err:" + e);
                    e.printStackTrace();
                }
            }
        } else {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File item : files) {
                startCompress(item.getAbsolutePath());
            }
        }
    }
}
