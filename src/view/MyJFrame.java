package view;

import algorithm.Caesar;
import algorithm.RSA;
import algorithm.Vigenere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static algorithm.RSA.*;
import static utils.UtilChar.toChar;

public class MyJFrame extends JFrame implements ActionListener {

    private Container container;
    private int number = 1;
    private JTextArea textPlain;
    private JTextArea textHistoryPlain;
    private JTextArea textCipher;
    private JTextArea textHistoryCipher;
    private JTextField textKey;
    private JButton button_caesar_encrypt;
    private JButton button_caesar_decrypt;
    private JButton button_caesar_crack;
    private JButton button_vige_encrypt;
    private JButton button_vige_decrypt;
    private JButton button_vige_crack;
    private JButton button_rsa_encrypt;
    private JButton button_rsa_decrypt;
    private Caesar caesar;

    public MyJFrame(){

        //1.title,location,size,layout
        super("√‹¬ÎÀ„∑®—› æ»Ìº˛");
        caesar = new Caesar();
        //this.setBounds(300,200,360,200);
        this.setBounds(500,500,1210,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new GridLayout(1,3));

        //component1:textPlain
        JPanel panelPlain = new JPanel(new GridLayout(2,1));
        container.add(panelPlain);
        textPlain = new JTextArea();
        textPlain.setLineWrap(true);
        textPlain.setBorder(BorderFactory.createLineBorder(Color.black,2));
        JScrollPane jp1 = new JScrollPane(textPlain,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelPlain.add(jp1);

        //"this is history plaintext"
        textHistoryPlain = new JTextArea();
        textHistoryPlain.setLineWrap(true);
        textHistoryPlain.setBorder(BorderFactory.createLineBorder(Color.black,2));
        JScrollPane jp2 = new JScrollPane(textHistoryPlain,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelPlain.add(jp2);

//        //component2:panel
        JPanel panel = new JPanel(new GridLayout(8,1));
        container.add(panel);

        textKey = new JTextField(1);
        textKey.setText("key:");
        textKey.addActionListener(this);
        //panel.add(textKey);

        button_caesar_decrypt = new JButton("ø≠»ˆΩ‚√‹");
        button_caesar_decrypt.addActionListener(this);
        panel.add(button_caesar_decrypt);

       //6.buttonEncrypt,buttonCrack,buttonDecrypt
        button_caesar_encrypt = new JButton("ø≠»ˆº”√‹");
        button_caesar_encrypt.addActionListener(this);
        panel.add(button_caesar_encrypt);

        button_caesar_crack = new JButton("ø≠»ˆ∆∆Ω‚");
        button_caesar_crack.addActionListener(this);
        panel.add(button_caesar_crack);

        button_vige_decrypt = new JButton("Œ¨º™ƒ·—«Ω‚√‹");
        button_vige_decrypt.addActionListener(this);
        panel.add(button_vige_decrypt);

        //6.buttonEncrypt,buttonCrack,buttonDecrypt
        button_vige_encrypt = new JButton("Œ¨º™ƒ·—«º”√‹");
        button_vige_encrypt.addActionListener(this);
        panel.add(button_vige_encrypt);

        button_vige_crack = new JButton("Œ¨º™ƒ·—«∆∆Ω‚");
        button_vige_crack.addActionListener(this);
        panel.add(button_vige_crack);

        button_rsa_encrypt = new JButton("RSAº”√‹");
        button_rsa_encrypt.addActionListener(this);
        panel.add(button_rsa_encrypt);

        button_rsa_decrypt = new JButton("RSAΩ‚√‹");
        button_rsa_decrypt.addActionListener(this);
        panel.add(button_rsa_decrypt);

        //component3:textCipher
        JPanel panelCipher = new JPanel(new GridLayout(2,1));
        container.add(panelCipher);
        textCipher = new JTextArea("this is cipher");
        textCipher.setLineWrap(true);
        textCipher.setBorder(BorderFactory.createLineBorder(Color.black,2));
        JScrollPane jp3 = new JScrollPane(textCipher,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelCipher.add(jp3);
        //"this is history cipher"
        textHistoryCipher = new JTextArea();
        textHistoryCipher.setLineWrap(true);
        textHistoryCipher.setBorder(BorderFactory.createLineBorder(Color.black,2));
        JScrollPane jp4 = new JScrollPane(textHistoryCipher,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelCipher.add(jp4);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // ¬º˛º‡Ã˝
        Object[] obj2 = new Object[26];
        for(int i=0;i<25;i++)
            obj2[i] = String.valueOf(i+1);
        Object trigger = e.getSource();
        if(trigger == button_caesar_encrypt){  //Ω¯––ø≠»ˆº”√‹
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "«Î—°‘Òƒ„µƒ√‹‘ø:\n",
                    "√‹‘ø",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(),
                    obj2,
                    "3");
            //System.out.println(s);
            String aLine =  textPlain.getText();
            int key = Integer.valueOf(s);
            //int key = Integer.valueOf(textKey.getText());
            textPlain.setText("");
            char[] cipher = caesar.encrypt(toChar(aLine),key);
            textHistoryPlain.append("plaintext "+number+":\n");
            textHistoryPlain.append(aLine+"\n\n");
            textCipher.setText("");
            textCipher.append(String.valueOf(cipher));
            textHistoryCipher.append("cipher"+number+":\n"+String.valueOf(cipher)+"\n\n");
            //textHistoryCipher.append(String.valueOf(cipher));
        }else if(trigger == button_caesar_decrypt){ //Ω¯––ø≠»ˆΩ‚√‹
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "«Î—°‘Òƒ„µƒ√‹‘ø:\n",
                    "√‹‘ø",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(),
                    obj2,
                    "3");
            String cipher = textCipher.getText();
            //int key = Integer.valueOf(textKey.getText());
            int key = Integer.valueOf(s);
            char []plain = caesar.decrypt(toChar(cipher),key);

            textCipher.setText("");
            textHistoryPlain.append("plain "+number+":\n"+String.valueOf(plain)+"\n\n");
            textPlain.setText("");
            textPlain.append(String.valueOf(plain));
            textHistoryCipher.append("cipher"+number+":\n"+cipher+"\n\n");
        }else if(trigger == button_caesar_crack){ //Ω¯––ø≠»ˆ∆∆Ω‚
            String cipher = textCipher.getText();
            char[]plain = caesar.crack(toChar(cipher),0);
            textCipher.setText("");
            textHistoryPlain.append("plain "+number+":\n"+String.valueOf(plain)+"\n\n");
            textPlain.setText("");
            textPlain.append(String.valueOf(plain));
            textHistoryCipher.append("cipher"+number+":\n"+cipher+"\n\n");
        }else if(trigger == button_vige_encrypt){
            String keyStr = JOptionPane.showInputDialog(button_vige_encrypt,
                    "“™º”√‹ «Î ‰»ÎŒ¨º™ƒ·—«√‹¬Î√‹‘ø£∫",
                    " ‰»Î√‹‘ø",
                    JOptionPane.PLAIN_MESSAGE
            );
            char []key = toChar(keyStr);
            Vigenere v = new Vigenere();
            String plaintext = textPlain.getText();
            char[]cipher = v.encrypt(toChar(plaintext),key);
            textCipher.setText("");
            textCipher.append(String.valueOf(cipher));
            textHistoryCipher.append("cipher"+number+":\n"+String.valueOf(cipher)+"\n\n");
            textPlain.setText("");
            textPlain.append(plaintext);
            textHistoryPlain.append("plaintext"+number+":\n"+plaintext+"\n\n");
        }else if(trigger == button_vige_decrypt){
            String keyStr = JOptionPane.showInputDialog(button_vige_encrypt,
                    "“™Ω‚√‹ «Î ‰»ÎŒ¨º™ƒ·—«√‹¬Î√‹‘ø£∫",
                    " ‰»Î√‹‘ø",
                    JOptionPane.PLAIN_MESSAGE
            );
            char []key = toChar(keyStr);
            Vigenere v = new Vigenere();
            String cipherStr = textCipher.getText();
            char []plaintext = v.decrypt(toChar(cipherStr),key);
            textPlain.setText("");
            textPlain.append(String.valueOf(plaintext));
            textHistoryPlain.append("plaintext"+number+":\n"+String.valueOf(plaintext)+"\n\n");
            textHistoryCipher.append("cipher"+number+":\n"+cipherStr+"\n\n");
        }else if(trigger == button_vige_crack){
            String cipherStr = textCipher.getText();
            char []cipher = toChar(cipherStr);
            Vigenere v = new Vigenere();
            char[]plaintext = v.crack(cipher);
            textPlain.setText("");
            textPlain.append(String.valueOf(plaintext));
            textHistoryPlain.append("plaintext"+number+":\n"+String.valueOf(plaintext)+"\n\n");
            textHistoryCipher.append("cipher"+number+":\n"+cipherStr+"\n\n");
            //v.crack(cipher);
        }else if(trigger==button_rsa_encrypt){
            char []p = "885320963".toCharArray();
            char []q = "238855417".toCharArray();
            RSA r = new RSA(p,q,"1001".toCharArray());
            String aLine =  textPlain.getText();
            char[] plaintext = toChar(aLine);
            long []cipher = encrypt(plaintext,getE(),getN());
            textHistoryCipher.append("cipher"+number+":\n");
            textCipher.setText("");
            for(int i=0;i<cipher.length;i++){
                textHistoryCipher.append(cipher[i] +" ");
                textCipher.append(cipher[i] +" ");
            }
            textHistoryCipher.append("\n\n");
            textHistoryPlain.append("plaintext"+number+":\n"+aLine+"\n\n");
        }else if(trigger==button_rsa_decrypt){
            char []p = "885320963".toCharArray();
            char []q = "238855417".toCharArray();
            RSA r = new RSA(p,q,"1001".toCharArray());
            String aLine =  textCipher.getText();
            String []lines = aLine.split(" ");
            long []cipher = new long[lines.length];
            for(int i=0;i<lines.length;i++){
                cipher[i] = Long.parseLong(lines[i]);
            }
            char[]plaintext = decrypt(cipher,getD(),getN());
            textHistoryPlain.append("plaintext"+number+":\n"+String.valueOf(plaintext)+"\n\n");
            textPlain.setText("");
            textPlain.append(String.valueOf(plaintext));
            textHistoryCipher.append("cipher"+number+":\n"+aLine+"\n\n");
        }
        this.number++;
    }
}