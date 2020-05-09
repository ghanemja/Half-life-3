import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class AdminPanel {
    private JPanel adminPage = new JPanel();
    private String[][] users;
    private String[][] requests;
    private JButton promote = new JButton("Set Role");
    private JButton approve = new JButton("Approve");
    private JButton deny = new JButton("Deny");
    private JButton back = new JButton("Back");
    private JButton add = new JButton("add");

    private JScrollPane userList;

    private TextField userName = new TextField("", 20);
    private TextField requestNumber = new TextField("", 5);
    private TextField gameInfo = new TextField("", 40);

    private Admin admin = new Admin("");

    public AdminPanel() {
        adminPage.setLayout(new FlowLayout());
        adminPage.setPreferredSize(new Dimension(1000, 800));

        userTable();
        JLabel labelTop = new JLabel("Role Management:");
        JPanel top1 = new JPanel();
        top1.setPreferredSize(new Dimension(100000,1));
        JPanel top2 = new JPanel();
        top2.setPreferredSize(new Dimension(100000,1));
        JPanel top3 = new JPanel();
        top3.setPreferredSize(new Dimension(100000,1));

        String[] columnNames = { "UserID", "Roles" };
        JTable userTable = new JTable(users, columnNames);
        userList = new JScrollPane(userTable);
        userList.setPreferredSize(new Dimension(800, 200));
        userTable.setFillsViewportHeight(true);

        JLabel labelMid = new JLabel("Game Requests:");
        JPanel mid1 = new JPanel();
        mid1.setPreferredSize(new Dimension(100000,1));
        JPanel mid2 = new JPanel();
        mid2.setPreferredSize(new Dimension(100000,1));
        JPanel mid3 = new JPanel();
        mid3.setPreferredSize(new Dimension(100000,1));
        JPanel mid4 = new JPanel();
        mid4.setPreferredSize(new Dimension(100000,1));

        requestTable();
        String[] requestColumnNames = { "Request#", "Title" };
        JTable requestTable = new JTable(requests, requestColumnNames);
        JScrollPane requestList = new JScrollPane(requestTable);
        requestList.setPreferredSize(new Dimension(800, 200));
        userTable.setFillsViewportHeight(true);

        //set roles
        JLabel label = new JLabel("Which user do you wish to change role (UserID, Role name):");
        promote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!userName.getText().equals("")) {

                    String[] s = userName.getText().split(",");
                    promote(s[0], s[1]);
                    userList = new JScrollPane(new JTable(users, columnNames));
                    adminPage.revalidate();
                    adminPage.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid username.");
                }
            }
        });

        //Requests
        JLabel label2 = new JLabel("Which request do you wish to deal with(Enter a Request#):");
        approve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!requestNumber.getText().equals("")) {
                    manageRequest(Integer.parseInt(requestNumber.getText()));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid request#.");
                }
            }
        });

        deny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!requestNumber.getText().equals("")) {
                    manageRequest(Integer.parseInt(requestNumber.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid request#.");
                }
            }
        });


        JLabel labelAdd = new JLabel("Enter game info to add(Title, path):");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameInfo.getText().equals("")) {
                    String[] s = gameInfo.getText().split(",");
                    admin.addGame(s[0], s[1]);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid request#.");
                }
            }
        });

        adminPage.add(labelTop);
        adminPage.add(top1);
        adminPage.add(userList);
        adminPage.add(top2);
        adminPage.add(label);
        adminPage.add(userName);
        adminPage.add(promote);
        adminPage.add(top3);



        adminPage.add(labelMid);
        adminPage.add(mid1);
        adminPage.add(requestList);
        adminPage.add(mid2);

        adminPage.add(label2);
        adminPage.add(requestNumber);
        adminPage.add(approve);
        adminPage.add(deny);
        adminPage.add(mid3);

        adminPage.add(labelAdd);
        adminPage.add(gameInfo);
        adminPage.add(add);

        adminPage.add(mid4);
        adminPage.add(back);

        adminPage.setVisible(true);

    }

    private void requestTable() {
        try {
            File input = new File("requests.csv");
            Scanner reader = new Scanner(input);
            int count = 0;
            String dummy = "";
            while (reader.hasNextLine()) {
                dummy = reader.nextLine();
                count++;
            }
            Scanner reader2 = new Scanner(input);
            requests = new String[count][2];
            count = 0;
            while (reader2.hasNextLine()) {
                dummy = reader2.nextLine();
                String[] info = dummy.split(",");
                requests[count][0] = info[0];
                requests[count][1] = info[1];
                count++;
            }
            reader2.close();
            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void userTable() {
        try {
            File input = new File("userlogin.csv");
            Scanner reader = new Scanner(input);
            int count = 0;
            String dummy = "";
            while (reader.hasNextLine()) {
                dummy = reader.nextLine();
                count++;
            }
            Scanner reader2 = new Scanner(input);
            users = new String[count][2];
            count = 0;
            while (reader2.hasNextLine()) {
                dummy = reader2.nextLine();
                String[] info = dummy.split(",");
                users[count][0] = info[0];
                if (info[2].equals("0")) {
                    users[count][1] = "User";
                } else if (info[2].equals("1")) {
                    users[count][1] = "Moderator";
                } else {
                    users[count][1] = "Admin";
                }

                count++;
            }
            reader2.close();
            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void promote(String user, String role) {
        try {

            int roleNum = 0;
            String temp = role.toLowerCase();
            if (temp.equals("moderator")) {
                roleNum = 1;
            } else if (temp.equals("admin")) {
                roleNum = 2;
            } else if (temp.equals("user")) {
                roleNum = 0;
            }

            admin.setRole(user, roleNum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manageRequest(int num) {
        try {

            File temp = new File("tempRequests.csv");
            File input = new File("requests.csv");

            Scanner reader = new Scanner(input);

            FileWriter write = new FileWriter(temp, true);

            String dummy;
            int count = 0;
            while (reader.hasNextLine()) {
                dummy = reader.nextLine();
                String[] request = dummy.split(",");
                if (Integer.parseInt(request[0]) != num) {
                    write.append(count + "," + request[1] + "\n");
                    count++;
                }
            }

            write.close();
            reader.close();
            input.delete();
            temp.renameTo(input);
            temp.delete();
            JOptionPane.showMessageDialog(null, "Completed.");


        } catch (IOException e) {           
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return adminPage;
    }

    public void setActListBack(ActionListener e) {
        back.addActionListener(e);
    }

    public JButton getBackBtn() {
        return back;
    }
}
