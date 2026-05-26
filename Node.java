import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.event.ListSelectionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MetroGui extends JFrame {

  private static final long serialVersionUID = 1L; // this is for serializable we can ignore it
  private JPanel contentPane;
  private JTextField txtMgrName;
  private JTextField txtMgrID;
  private JTextField txtPassengerName;
  private JTextField txtPassengerID;
  private JPanel UI;
  private JPanel managerLoginPanel;
  private JPanel managerTasksPanel;
  private JCheckBox NotRobotM;
  private JPanel leftSideBar;

  // Passengers Panels
  private JPanel passengerPanel;
  private JPanel passengerLoginPanel;
  private JPanel passengerCreateAccountPanel;
  private JPanel passengerTasksPanel;
  private JPanel pnlBuyTicket;
  private JPanel pnlDisplayInfo;
  private JPanel pnlAddBalance;

  private Passenger loggedInPassenger = new Passenger("", "", 0);
  private String name = "";
  private String id = "";
  private JCheckBox NotRobotP1;
  private JCheckBox NotRobotP2;
  private JTextField txtPassengerName2;
  private JTextField txtPassengerID2;
  private JPanel mainContetP;
  private JLabel lbHintP;
  private JPanel leftSideBarP;
  private JList ticketsNormal;
  private JList ticketsFirstClass;
  private JLabel lblExceptionMessageP = new JLabel("");
  private JPanel infoBorader;
  private JLabel lbWelcomeP;
  private JLabel lbId;
  private JTextArea Pinfo;

  private JPanel ticketInfo;
  private boolean normalT = false;
  private boolean firstT = false;
  private JTextField txtAmount;
  private JLabel lblAddBalanceMessage;
  private JTextArea Tinfo;
  private String typeTicket;
  private JButton Mytickets;

  private JPanel mainContent;
  private JLabel lbHint;
  private JPanel pnlAddStationTask;
  private JLabel lblExceptionMessage;

  private JLabel BalanceT;

  Station currentStation = null;
  private JTextField txtStationName;
  private JTextField txtStationLocation;
  private JTextField txtStationCap;

  private JPanel pnlEmployeeMain;
  private DefaultListModel<Employee> employeeListModel;
  private JList<Employee> employeeList;
  private JLabel lblEmpName;
  private JLabel lblEmpId;
  private JLabel lblEmpPosition;
  private JScrollPane listScroll;

  private JPanel pnlAddEmployeeTask;
  private JTextField txtEmpName;
  private JTextField txtEmpPosition;
  private JTextField txtEmpId;
  private JLabel lblAddEmpMessage;

  private JPanel pnlRemoveStationTask;
  private JTextField txtRemoveStationName;
  private JLabel lblRemoveMessage;

  private JPanel pnlMaintenanceMain;
  private JLabel lblCurrentProfit;
  private JLabel lblMaintCost;
  private JProgressBar maintProgressBar;
  private JTextArea txtMaintResult;
  private JButton btnRunMaint;

  private JPanel selectStationPanel;
  private JLabel lblExceptionMessage1;
  private JTextField txtSelectStationName;
  private JPanel selectStationPanel2;
  private JPanel leftSideBarSelectStation;
  private JLabel lbHint2;
  private JLabel lbManageStationTitle;
  private JLabel lbStationDisplayName;

  private JPanel pnlAddVehicleTask;

  private JPanel pnlMetro;
  private JTextField txtIDMtetro;
  private JTextField txtCapMetro;
  private JTextField txtLineMetro;
  private JLabel lbAddMetro;

  private JPanel pnlBus;
  private JTextField txtIDBus;
  private JTextField txtCapBus;
  private JTextField txtRouteBus;
  private JLabel lbAddBus;

  private JPanel pnlBusOnD;
  private JTextField txtIDBusOnD;
  private JTextField txtCapBusOnD;
  private JTextField txtRouteBusD;
  private JTextField txtNiBusD;
  private JLabel lbAddBusOnD;

  private JPanel pnlRemoveVehicleTask;

  private JPanel pnlMetroRemove;
  private JTextField txtIDMtetroRemove;
  private JLabel lbMetroRemove;

  private JPanel pnlBusRemove;
  private JTextField txtIDBusRemove;
  private JLabel lbBusRemove;

  private JPanel pnlBusDRemove;
  private JTextField txtIDBusDRemove;
  private JLabel lbBusDRemove;

  private JPanel pnlAssignEmployee;
  private JRadioButton rbMetro;
  private JRadioButton rbBus;
  private JRadioButton rbBusOnDemand;
  private JTextField txtVehicleID;
  private JTextField txtEmployeeID;
  private JLabel lbAssignStatus;

  private JPanel pnlMyTickets;
  private JLabel lbTicketsName;
  private JLabel lbTicketType;
  private JLabel lbExpDate;
  private JLabel lblExceptionMessageP1;
  private JButton btback;
  private boolean isTicketActive = false;

  private MetroNetwork riyadhMetro;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() { // this is for run the program
      public void run() {
        try {
          MetroGui frame = new MetroGui();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  // Create the frame.
  public MetroGui() {

    // properties for MetroGui
    setTitle("Riyadh Metro Simulation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        saveProgress("riyadh_metro.data");// saving progress when closing the window s
      }
    });
    setResizable(false);
    setBounds(100, 100, 800, 600);
    employeeListModel = new DefaultListModel<>();
    loadProgress("riyadh_metro.data");
    // this will hold evey thing
    contentPane = new JPanel();
    contentPane.setBackground(new Color(248, 249, 250));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null); // this allowed as to move things

    // first panal for title and selection main role
    UI = new JPanel();
    UI.setBounds(0, 11, 784, 561);
    UI.setBackground(new Color(248, 249, 250));
    UI.setLayout(null);
    contentPane.add(UI);

    // set title and edit it
    JLabel NewLabelTitle = new JLabel("Riyadh Metro System");
    NewLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
    NewLabelTitle.setForeground(new Color(4, 64, 37));
    NewLabelTitle.setBounds(192, 60, 400, 50);
    UI.add(NewLabelTitle);

    // set title and edit it
    JLabel NewLabelTitle2 = new JLabel("Choose your role");
    NewLabelTitle2.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelTitle2.setFont(new Font("Segoe UI", Font.BOLD, 20));
    NewLabelTitle2.setForeground(new Color(4, 64, 37));
    NewLabelTitle2.setBounds(192, 100, 400, 50);
    UI.add(NewLabelTitle2);

    // set button for manager login
    JButton btnManegerLogin = new JButton("Manager");
    btnManegerLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnManegerLogin.setForeground(Color.WHITE);
    btnManegerLogin.setBackground(new Color(4, 64, 37));
    btnManegerLogin.setOpaque(true);
    btnManegerLogin.setBorderPainted(false);
    btnManegerLogin.setBounds(230, 250, 150, 40);
    btnManegerLogin.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        UI.setVisible(false);
        managerLoginPanel.setVisible(true);
      }
    });
    UI.add(btnManegerLogin);

    // set button for passenger login
    JButton btnPassengerLogin = new JButton("Passenger");
    btnPassengerLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnPassengerLogin.setForeground(Color.WHITE);
    btnPassengerLogin.setBackground(new Color(4, 64, 37));
    btnPassengerLogin.setOpaque(true);
    btnPassengerLogin.setBorderPainted(false);
    btnPassengerLogin.setBounds(410, 250, 150, 40);
    btnPassengerLogin.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        UI.setVisible(false);
        passengerPanel.setVisible(true);
      }
    });
    UI.add(btnPassengerLogin);

    // secnond panel for manager login
    managerLoginPanel = new JPanel();
    managerLoginPanel.setBounds(0, 0, 784, 561);
    managerLoginPanel.setBackground(new Color(248, 249, 250));
    managerLoginPanel.setLayout(null);
    managerLoginPanel.setVisible(false);
    contentPane.add(managerLoginPanel);

    // title for second panel
    JLabel NewLabelML = new JLabel("Manager Login");
    NewLabelML.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelML.setFont((new Font("Segoe UI", Font.BOLD, 26)));
    NewLabelML.setForeground(new Color(4, 64, 37));
    NewLabelML.setBounds(242, 50, 300, 40);
    managerLoginPanel.add(NewLabelML);

    // title for name
    JLabel NewLabelName = new JLabel("Enter Name");
    NewLabelName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelName.setBounds(292, 150, 100, 20);
    managerLoginPanel.add(NewLabelName);

    // textfild to enter name
    txtMgrName = new JTextField();
    txtMgrName.setBounds(292, 175, 200, 30);
    managerLoginPanel.add(txtMgrName);

    // title for id
    JLabel NewLabelID = new JLabel("Enter ID");
    NewLabelID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelID.setBounds(292, 220, 100, 20);
    managerLoginPanel.add(NewLabelID);

    // textfild to enter id
    txtMgrID = new JTextField();
    txtMgrID.setBounds(292, 245, 200, 30);
    managerLoginPanel.add(txtMgrID);

    // check box for not robot
    NotRobotM = new JCheckBox("I'm not a robot");
    NotRobotM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NotRobotM.setBackground(new Color(248, 249, 250));
    NotRobotM.setBounds(292, 280, 150, 25);
    managerLoginPanel.add(NotRobotM);

    // button to conform login and check password
    JButton btnConfirmLogin = new JButton("Login");
    btnConfirmLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnConfirmLogin.setForeground(Color.WHITE);
    btnConfirmLogin.setBackground(new Color(4, 64, 37));
    btnConfirmLogin.setBounds(342, 310, 100, 35);
    btnConfirmLogin.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        String name = txtMgrName.getText();
        String id = txtMgrID.getText();

        if (name.equals("Ghala") && id.equals("admin-01")) {

          if (NotRobotM.isSelected()) {
            JOptionPane.showMessageDialog(MetroGui.this, "Login Successful! Welcome, Manager Ghala");
            // delete the text in the textfields and deselect the check box
            txtMgrName.setText("");
            txtMgrID.setText("");
            NotRobotM.setSelected(false);
            // here move to panel mangerTasks
            managerLoginPanel.setVisible(false);
            managerTasksPanel.setVisible(true);
            lbHint.setVisible(true);
            hideAllManagerPanels();
          } else {
            JOptionPane.showMessageDialog(MetroGui.this, "Please confirm you are not a robot", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          JOptionPane.showMessageDialog(MetroGui.this, "Invalid Name and ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    managerLoginPanel.add(btnConfirmLogin);

    // button back
    JButton btnBack = new JButton("Back");
    btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnBack.setForeground(Color.WHITE);
    btnBack.setBackground(new Color(4, 64, 37));
    btnBack.setBounds(342, 360, 100, 30);
    btnBack.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        managerLoginPanel.setVisible(false);
        UI.setVisible(true);
      }
    });
    managerLoginPanel.add(btnBack);

    // panal manager tasks
    managerTasksPanel = new JPanel();
    managerTasksPanel.setBounds(0, 0, 800, 600);
    managerTasksPanel.setBackground(new Color(240, 242, 245));
    managerTasksPanel.setLayout(null);
    managerTasksPanel.setVisible(false);
    contentPane.add(managerTasksPanel);

    // top part
    JPanel topHeader = new JPanel();
    topHeader.setBackground(new Color(4, 64, 37));
    topHeader.setBounds(0, 0, 800, 70);
    topHeader.setLayout(null);
    managerTasksPanel.add(topHeader);

    // title for top part
    JLabel lbWelcome = new JLabel("Welcome, Manager Ghala");
    lbWelcome.setForeground(Color.WHITE);
    lbWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
    lbWelcome.setBounds(30, 15, 400, 30);
    topHeader.add(lbWelcome);

    JLabel lbAdminId = new JLabel("ID: admin-01");
    lbAdminId.setForeground(new Color(200, 200, 200));
    lbAdminId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lbAdminId.setBounds(30, 42, 200, 20);
    topHeader.add(lbAdminId);

    // main Content Area for tasks
    mainContent = new JPanel();
    mainContent.setBackground(new Color(248, 249, 250));
    mainContent.setBounds(270, 89, 500, 470);
    mainContent.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    mainContent.setLayout(null);
    managerTasksPanel.add(mainContent);

    // hint
    lbHint = new JLabel("Select a task to perform");
    lbHint.setHorizontalAlignment(SwingConstants.CENTER);
    lbHint.setForeground(Color.GRAY);
    lbHint.setBounds(130, 220, 250, 20);
    mainContent.add(lbHint);

    // adding a station
    // setting up the panel visuals
    pnlAddStationTask = new JPanel();
    pnlAddStationTask.setBounds(0, 0, 500, 470);
    pnlAddStationTask.setBackground(new Color(248, 249, 250));
    pnlAddStationTask.setLayout(null);
    pnlAddStationTask.setVisible(false);
    mainContent.add(pnlAddStationTask);

    JLabel lblAddStationTitle = new JLabel("Add a New Station");
    lblAddStationTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblAddStationTitle.setForeground(new Color(4, 64, 37));
    lblAddStationTitle.setBounds(50, 30, 200, 30);
    pnlAddStationTask.add(lblAddStationTitle);

    JLabel lblName = new JLabel("Station Name:");
    lblName.setBounds(50, 90, 120, 25);
    pnlAddStationTask.add(lblName);

    txtStationName = new JTextField();
    txtStationName.setBounds(160, 90, 260, 25);
    pnlAddStationTask.add(txtStationName);

    JLabel lblLocation = new JLabel("Location:");
    lblLocation.setBounds(50, 140, 100, 25);
    pnlAddStationTask.add(lblLocation);

    txtStationLocation = new JTextField();
    txtStationLocation.setBounds(160, 140, 260, 25);
    pnlAddStationTask.add(txtStationLocation);

    JLabel lblCap = new JLabel("Max Capacity:");
    lblCap.setBounds(50, 190, 100, 25);
    pnlAddStationTask.add(lblCap);

    txtStationCap = new JTextField();
    txtStationCap.setBounds(160, 190, 260, 25);
    pnlAddStationTask.add(txtStationCap);

    lblExceptionMessage = new JLabel("");
    lblExceptionMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblExceptionMessage.setBounds(160, 215, 300, 20);
    pnlAddStationTask.add(lblExceptionMessage);

    JButton btnSubmitStation = new JButton("Save Station");
    btnSubmitStation.setBackground(new Color(4, 64, 37));
    btnSubmitStation.setForeground(Color.WHITE);
    btnSubmitStation.setBounds(160, 250, 150, 35);
    pnlAddStationTask.add(btnSubmitStation);

    btnSubmitStation.addActionListener(new ActionListener() {
      /*
       * if we click this button, it takes the users input strings, check if not
       * empty(if empty an error message shows), or if the capacity is a valid
       * integer(if not it throws an exception depending on the input, negative or
       * isnt and integer)
       */
      public void actionPerformed(ActionEvent e) {
        lblExceptionMessage.setText("");

        String sName = txtStationName.getText();
        String locStr = txtStationLocation.getText();
        String capStr = txtStationCap.getText();

        if (sName.isEmpty() || locStr.isEmpty() || capStr.isEmpty()) {
          lblExceptionMessage.setForeground(Color.RED);
          lblExceptionMessage.setText("Error: All fields must be filled.");
          return;
        }

        try {
          int capacity = Integer.parseInt(capStr);

          if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");// exceptionGh
          }

          Station newStation = new Station(sName, locStr, capacity);
          // addind the station if all requirements are met
          if (riyadhMetro.addStation(newStation)) {
            lblExceptionMessage.setForeground(new Color(0, 150, 0));
            lblExceptionMessage.setText("Success! Station '" + sName + "' added.");
            txtStationName.setText("");
            txtStationLocation.setText("");
            txtStationCap.setText("");
          } else {
            lblExceptionMessage.setForeground(Color.RED);
            lblExceptionMessage.setText("Error: Could not add station!");
          }

        } catch (NumberFormatException ex) {
          lblExceptionMessage.setForeground(Color.RED);
          lblExceptionMessage.setText("Error: Capacity must be a valid number.");
        } catch (IllegalArgumentException ex) {
          lblExceptionMessage.setForeground(Color.RED);
          lblExceptionMessage.setText("Error: " + ex.getMessage());
        }
      }
    });

    JButton btnBackTask = new JButton("Back");
    btnBackTask.setBackground(new Color(150, 150, 150));
    btnBackTask.setForeground(Color.WHITE);
    btnBackTask.setBounds(320, 250, 100, 35);
    pnlAddStationTask.add(btnBackTask);

    btnBackTask.addActionListener(new ActionListener() {
      // if we click this btn, it goes back to the main panel resetting the textfields
      // and the error message
      public void actionPerformed(ActionEvent e) {
        txtStationName.setText("");
        txtStationLocation.setText("");
        txtStationCap.setText("");
        lblExceptionMessage.setText("");
        hideAllManagerPanels();
        lbHint.setVisible(true);
      }
    });

    // remove a station, setting the panel visuals
    pnlRemoveStationTask = new JPanel();
    pnlRemoveStationTask.setBounds(0, 0, 500, 470);
    pnlRemoveStationTask.setBackground(new Color(248, 249, 250));
    pnlRemoveStationTask.setLayout(null);
    pnlRemoveStationTask.setVisible(false);
    mainContent.add(pnlRemoveStationTask);
    JLabel lblRemoveStationTitle = new JLabel("Remove a Station");
    lblRemoveStationTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblRemoveStationTitle.setForeground(new Color(4, 64, 37));
    lblRemoveStationTitle.setBounds(50, 30, 200, 30);
    pnlRemoveStationTask.add(lblRemoveStationTitle);
    JLabel lblRemoveName = new JLabel("Station Name:");
    lblRemoveName.setBounds(50, 90, 120, 25);
    pnlRemoveStationTask.add(lblRemoveName);

    txtRemoveStationName = new JTextField();
    txtRemoveStationName.setBounds(160, 90, 260, 25);
    pnlRemoveStationTask.add(txtRemoveStationName);

    lblRemoveMessage = new JLabel("");
    lblRemoveMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblRemoveMessage.setBounds(160, 125, 300, 20);
    pnlRemoveStationTask.add(lblRemoveMessage);

    JButton btnConfirmRemove = new JButton("Remove Station");
    btnConfirmRemove.setBackground(new Color(217, 48, 37));
    btnConfirmRemove.setForeground(Color.WHITE);
    btnConfirmRemove.setBounds(160, 160, 150, 35);
    pnlRemoveStationTask.add(btnConfirmRemove);
    btnConfirmRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {// if this button is clicked, it checks if the station exists, if it
                                                  // does it removes it, if not it shows an error message
        lblRemoveMessage.setText("");
        if (riyadhMetro.getNumOfStations() == 0) {
          lblRemoveMessage.setForeground(Color.RED);
          lblRemoveMessage.setText("Error: No stations to remove.");
          return;
        }

        String sName = txtRemoveStationName.getText();
        if (sName.isEmpty()) {// if the textfield is empty, it shows an error message
          lblRemoveMessage.setForeground(Color.RED);
          lblRemoveMessage.setText("Error: Please enter a station name.");
          return;
        }
        if (riyadhMetro.removeStation(sName)) {
          lblRemoveMessage.setForeground(new Color(0, 150, 0));
          lblRemoveMessage.setText("Success! Station '" + sName + "' removed.");
          txtRemoveStationName.setText("");
        } else {
          lblRemoveMessage.setForeground(Color.RED);
          lblRemoveMessage.setText("Error: Station '" + sName + "' not found.");
        }
      }
    });

    JButton btnBackFromRemove = new JButton("Back");
    btnBackFromRemove.setBackground(new Color(150, 150, 150));
    btnBackFromRemove.setForeground(Color.WHITE);
    btnBackFromRemove.setBounds(320, 160, 100, 35);
    pnlRemoveStationTask.add(btnBackFromRemove);

    btnBackFromRemove.addActionListener(new ActionListener() {// if we click this btn, it goes back to the main panel
                                                              // resetting the textfields and the error message
      public void actionPerformed(ActionEvent e) {
        txtRemoveStationName.setText("");
        lblRemoveMessage.setText("");
        hideAllManagerPanels();
        selectStationPanel2.setVisible(true);
        lbHint2.setVisible(true);
      }
    });

    // employee management, setting the panel visuals> it uses jlist to display the
    // employees and listmodels to actually handle the objects' data
    pnlEmployeeMain = new JPanel();
    pnlEmployeeMain.setBounds(0, 0, 500, 470);
    pnlEmployeeMain.setBackground(new Color(248, 249, 250));
    pnlEmployeeMain.setLayout(null);
    pnlEmployeeMain.setVisible(false);
    mainContent.add(pnlEmployeeMain);
    JLabel lblEmpMainTitle = new JLabel("Manage Employees");
    lblEmpMainTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblEmpMainTitle.setForeground(new Color(4, 64, 37));
    lblEmpMainTitle.setBounds(30, 20, 250, 30);
    pnlEmployeeMain.add(lblEmpMainTitle);
    employeeList = new JList<>(employeeListModel);
    employeeList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// only one employee can be selected at a time
    listScroll = new JScrollPane(employeeList);
    listScroll.setBounds(30, 70, 200, 310);
    pnlEmployeeMain.add(listScroll);
    JPanel pnlempInfo = new JPanel();
    pnlempInfo.setBounds(250, 70, 220, 200);
    pnlempInfo.setBackground(Color.WHITE);
    pnlempInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    pnlempInfo.setLayout(null);
    pnlEmployeeMain.add(pnlempInfo);
    lblEmpName = new JLabel("Name: ");
    lblEmpName.setBounds(15, 30, 190, 25);
    pnlempInfo.add(lblEmpName);
    lblEmpId = new JLabel("ID: ");
    lblEmpId.setBounds(15, 70, 190, 25);
    pnlempInfo.add(lblEmpId);
    lblEmpPosition = new JLabel("Position: ");
    lblEmpPosition.setBounds(15, 110, 190, 25);
    pnlempInfo.add(lblEmpPosition);

    JButton btnFireSelected = new JButton("Fire Selected");
    btnFireSelected.setBackground(new Color(217, 48, 37));
    btnFireSelected.setForeground(Color.WHITE);
    btnFireSelected.setBounds(150, 400, 150, 35);
    pnlEmployeeMain.add(btnFireSelected);

    // if we click this btn, it checks if an employee is selected, if it is, it asks
    // for confirmation, if confirmed, it removes the employee from the list and the
    // listmodel and shows a success message, if not it shows an error message
    btnFireSelected.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selectedIndex = employeeList.getSelectedIndex();
        if (selectedIndex != -1) {
          Employee selectedEmp = employeeListModel.get(selectedIndex);
          int confirm = JOptionPane.showConfirmDialog(MetroGui.this,
              "Are you sure you want to fire " + selectedEmp.getName() + "?", "Confirm Fire",
              JOptionPane.YES_NO_OPTION);
          if (confirm == JOptionPane.YES_OPTION) {
            if (riyadhMetro.removeEmployee(selectedEmp.getId())) {
              employeeListModel.remove(selectedIndex);
              JOptionPane.showMessageDialog(MetroGui.this, "Employee fired successfully.");
              lblEmpName.setText("Name: ");
              lblEmpId.setText("ID: ");
              lblEmpPosition.setText("Position: ");
            } else {
              JOptionPane.showMessageDialog(MetroGui.this, "Failed to fire employee.", "Error",
                  JOptionPane.ERROR_MESSAGE);
            }
          }
        } else {
          JOptionPane.showMessageDialog(MetroGui.this, "Please select an employee first.", "Error",
              JOptionPane.WARNING_MESSAGE);
        }
      }
    });

    JButton btnToAddEmployee = new JButton("Add Employee");
    btnToAddEmployee.setBackground(new Color(4, 64, 37));
    btnToAddEmployee.setForeground(Color.WHITE);
    btnToAddEmployee.setBounds(310, 400, 150, 35);
    pnlEmployeeMain.add(btnToAddEmployee);

    // if we click this btn, it shows the add employee panel and hides the main
    // employee panel, it also updates the list of employees
    employeeList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          Employee selectedEmp = employeeList.getSelectedValue();
          if (selectedEmp != null) {
            lblEmpName.setText("Name: " + selectedEmp.getName());
            lblEmpId.setText("ID: " + selectedEmp.getId());
            lblEmpPosition.setText("Position: " + selectedEmp.getPosition());
          } else {
            lblEmpName.setText("Name: ");
            lblEmpId.setText("ID: ");
            lblEmpPosition.setText("Position: ");
          }
        }
      }
    });
    btnToAddEmployee.addActionListener(new ActionListener() {// if we click this btn, it shows the add employee panel
                                                             // and hides the main employee panel
      public void actionPerformed(ActionEvent e) {
        hideAllManagerPanels();
        pnlAddEmployeeTask.setVisible(true);
      }
    });
    // add employee, setting the panel visuals

    pnlAddEmployeeTask = new JPanel();
    pnlAddEmployeeTask.setBounds(0, 0, 500, 470);
    pnlAddEmployeeTask.setBackground(new Color(248, 249, 250));
    pnlAddEmployeeTask.setLayout(null);
    pnlAddEmployeeTask.setVisible(false);
    mainContent.add(pnlAddEmployeeTask);

    JLabel lblAddEmpTitle = new JLabel("Add a New Employee");
    lblAddEmpTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblAddEmpTitle.setForeground(new Color(4, 64, 37));
    lblAddEmpTitle.setBounds(50, 30, 230, 30);
    pnlAddEmployeeTask.add(lblAddEmpTitle);

    JLabel lblEmpNameList = new JLabel("Employee Name:");
    lblEmpNameList.setBounds(50, 90, 125, 25);
    pnlAddEmployeeTask.add(lblEmpNameList);

    txtEmpName = new JTextField();
    txtEmpName.setBounds(170, 90, 260, 25);
    pnlAddEmployeeTask.add(txtEmpName);

    JLabel lblEmpIdList = new JLabel("Employee ID:");
    lblEmpIdList.setBounds(50, 140, 120, 25);
    pnlAddEmployeeTask.add(lblEmpIdList);

    txtEmpId = new JTextField();
    txtEmpId.setBounds(170, 140, 260, 25);
    pnlAddEmployeeTask.add(txtEmpId);

    JLabel lblEmpPositionList = new JLabel("Position:");
    lblEmpPositionList.setBounds(50, 190, 120, 25);
    pnlAddEmployeeTask.add(lblEmpPositionList);

    txtEmpPosition = new JTextField();
    txtEmpPosition.setBounds(170, 190, 260, 25);
    pnlAddEmployeeTask.add(txtEmpPosition);
    lblAddEmpMessage = new JLabel("");
    lblAddEmpMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblAddEmpMessage.setBounds(160, 225, 300, 20);
    pnlAddEmployeeTask.add(lblAddEmpMessage);

    JButton btnSubmitEmployee = new JButton("Save Employee");
    btnSubmitEmployee.setBackground(new Color(4, 64, 37));
    btnSubmitEmployee.setForeground(Color.WHITE);
    btnSubmitEmployee.setBounds(160, 260, 150, 35);
    pnlAddEmployeeTask.add(btnSubmitEmployee);
    // if we click this btn, it checks if the textfields are empty, if they are, it
    // shows an error message, if not, it creates a new employee and adds it to the
    // listmodel and the riyadhMetro object,
    btnSubmitEmployee.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lblAddEmpMessage.setText("");

        String empName = txtEmpName.getText();
        String empId = txtEmpId.getText();
        String empPos = txtEmpPosition.getText();
        if (empName.isEmpty() || empId.isEmpty() || empPos.isEmpty()) {
          lblAddEmpMessage.setForeground(Color.RED);
          lblAddEmpMessage.setText("Error: All fields must be filled.");
          return;
        }
        Employee emp = new Employee(empName, empId, empPos);
        if (riyadhMetro.addEmployee(emp)) {
          lblAddEmpMessage.setForeground(new Color(0, 150, 0));
          lblAddEmpMessage.setText("Success! Employee '" + empName + "' added.");
          employeeListModel.addElement(emp);
          txtEmpName.setText("");
          txtEmpId.setText("");
          txtEmpPosition.setText("");
        } else {
          lblAddEmpMessage.setForeground(Color.RED);
          lblAddEmpMessage.setText("Error: Could not add employee.");
          return;
        }
      }
    });
    JButton btnBackFromAddEmp = new JButton("Back");
    btnBackFromAddEmp.setBackground(new Color(150, 150, 150));
    btnBackFromAddEmp.setForeground(Color.WHITE);
    btnBackFromAddEmp.setBounds(320, 260, 100, 35);
    pnlAddEmployeeTask.add(btnBackFromAddEmp);
    btnBackFromAddEmp.addActionListener(new ActionListener() {// if we click this btn, it goes back to the main panel
                                                              // resetting the textfields and the error message
      public void actionPerformed(ActionEvent e) {
        txtEmpName.setText("");
        txtEmpId.setText("");
        txtEmpPosition.setText("");
        lblAddEmpMessage.setText("");
        hideAllManagerPanels();
        pnlEmployeeMain.setVisible(true);
      }
    });

    // Select station panel
    selectStationPanel = new JPanel();
    selectStationPanel.setBounds(0, 0, 500, 470);
    selectStationPanel.setBackground(new Color(248, 249, 250));
    selectStationPanel.setLayout(null);
    selectStationPanel.setVisible(false);
    mainContent.add(selectStationPanel);

    // message for exception
    lblExceptionMessage1 = new JLabel("");
    lblExceptionMessage1.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblExceptionMessage1.setBounds(160, 115, 300, 20);
    selectStationPanel.add(lblExceptionMessage1);

    // title for select station
    JLabel lbselectStation = new JLabel("Select a Station");
    lbselectStation.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lbselectStation.setForeground(new Color(4, 64, 37));
    lbselectStation.setBounds(50, 30, 200, 30);
    selectStationPanel.add(lbselectStation);

    // name for select station
    JLabel lblName1 = new JLabel("Station Name:");
    lblName1.setBounds(50, 90, 120, 25);
    selectStationPanel.add(lblName1);

    // textfield to enter name
    txtSelectStationName = new JTextField();
    txtSelectStationName.setBounds(160, 90, 260, 25);
    selectStationPanel.add(txtSelectStationName);

    // button to confirm
    JButton BtnConfirm = new JButton("Confirm");
    BtnConfirm.setBackground(new Color(4, 64, 37));
    BtnConfirm.setForeground(Color.WHITE);
    BtnConfirm.setBounds(160, 150, 120, 35);
    selectStationPanel.add(BtnConfirm);

    BtnConfirm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String inputStation = txtSelectStationName.getText();

        try {// Try to search for the station
          if (inputStation.isEmpty()) { // Check if the input is empty
            lblExceptionMessage1.setForeground(Color.RED);
            lblExceptionMessage1.setText("Error: Please enter a station name.");
          } else { // If the input is not empty, proceed with searching for the station
            currentStation = riyadhMetro.searchStation(inputStation);// Search for the station
            lbStationDisplayName.setText(currentStation.getStationName());// Set the station name

            // show the panel and hide the other panels
            hideAllManagerPanels();
            selectStationPanel2.setVisible(true);
            leftSideBarSelectStation.setVisible(true);
            leftSideBar.setVisible(false);
            lbHint2.setVisible(true);
            lblExceptionMessage1.setText("");
          }
          // Handle the case where the station is not found
        } catch (StationNotFoundException ex) {
          lblExceptionMessage1.setForeground(Color.RED);
          lblExceptionMessage1.setText("Error: Couldn't find station.");
        }

      }
    });

    // select station panel 2
    selectStationPanel2 = new JPanel();
    selectStationPanel2.setBounds(0, 0, 500, 470);
    selectStationPanel2.setBackground(new Color(248, 249, 250));
    selectStationPanel2.setLayout(null);
    selectStationPanel2.setVisible(false);
    mainContent.add(selectStationPanel2);

    // hint
    lbHint2 = new JLabel("Manage Vehicles & Employees");
    lbHint2.setHorizontalAlignment(SwingConstants.CENTER);
    lbHint2.setForeground(Color.GRAY);
    lbHint2.setBounds(130, 220, 250, 20);
    selectStationPanel2.add(lbHint2);

    // left side bar for select station
    leftSideBarSelectStation = new JPanel();
    leftSideBarSelectStation.setBounds(0, 70, 250, 530);
    leftSideBarSelectStation.setBackground(new Color(4, 44, 37));
    leftSideBarSelectStation.setLayout(null);
    leftSideBarSelectStation.setVisible(false);
    managerTasksPanel.add(leftSideBarSelectStation);

    // title for left side bar
    lbManageStationTitle = new JLabel("Manage Station: ");
    lbManageStationTitle.setHorizontalAlignment(SwingConstants.LEFT);
    lbManageStationTitle.setFont((new Font("Segoe UI", Font.BOLD, 16)));
    lbManageStationTitle.setForeground(Color.LIGHT_GRAY);
    lbManageStationTitle.setBounds(10, 10, 230, 40);
    leftSideBarSelectStation.add(lbManageStationTitle);

    // title for left side bar
    lbStationDisplayName = new JLabel("");
    lbStationDisplayName.setHorizontalAlignment(SwingConstants.LEFT);
    lbStationDisplayName.setFont((new Font("Segoe UI", Font.BOLD, 22)));
    lbStationDisplayName.setForeground(Color.WHITE);
    lbStationDisplayName.setBounds(10, 40, 230, 40);
    leftSideBarSelectStation.add(lbStationDisplayName);

    // add botton to new left side bar
    // add vehicle
    pnlAddVehicleTask = new JPanel();
    pnlAddVehicleTask.setBounds(0, 0, 500, 500);
    pnlAddVehicleTask.setBackground(new Color(248, 249, 250));
    pnlAddVehicleTask.setLayout(null);
    pnlAddVehicleTask.setVisible(false);
    mainContent.add(pnlAddVehicleTask);

    // title for add vehicle
    JLabel lbTypeVehicle = new JLabel("What type of vehicle?");
    lbTypeVehicle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lbTypeVehicle.setForeground(new Color(4, 64, 37));
    lbTypeVehicle.setBounds(50, 30, 250, 30);
    pnlAddVehicleTask.add(lbTypeVehicle);

    // buttons for type of vehicle
    JButton BtnMetro = new JButton("Metro");
    BtnMetro.setBackground(new Color(4, 64, 37));
    BtnMetro.setForeground(Color.WHITE);
    BtnMetro.setBounds(50, 90, 100, 35);

    BtnMetro.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels
        pnlMetro.setVisible(true);
        pnlBus.setVisible(false);
        pnlBusOnD.setVisible(false);

      }
    });

    pnlAddVehicleTask.add(BtnMetro);

    // buttons for type of vehicle
    JButton BtnBus = new JButton("Bus");
    BtnBus.setBackground(new Color(4, 64, 37));
    BtnBus.setForeground(Color.WHITE);
    BtnBus.setBounds(160, 90, 100, 35);

    BtnBus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels
        pnlBus.setVisible(true);
        pnlMetro.setVisible(false);
        pnlBusOnD.setVisible(false);
      }
    });

    pnlAddVehicleTask.add(BtnBus);

    // buttons for type of vehicle
    JButton BtnBusOnDemand = new JButton("Bus On Demand");
    BtnBusOnDemand.setBackground(new Color(4, 64, 37));
    BtnBusOnDemand.setForeground(Color.WHITE);
    BtnBusOnDemand.setBounds(270, 90, 150, 35);

    BtnBusOnDemand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels
        pnlBusOnD.setVisible(true);
        pnlMetro.setVisible(false);
        pnlBus.setVisible(false);
      }
    });

    pnlAddVehicleTask.add(BtnBusOnDemand);

    // back button
    JButton BtnBack22 = new JButton("Back");
    BtnBack22.setBackground(new Color(150, 150, 150));
    BtnBack22.setForeground(Color.WHITE);
    BtnBack22.setBounds(290, 420, 100, 35);

    BtnBack22.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // go back to the main panel, main is selectStationPanel2
        selectStationPanel2.setVisible(true);
        lbHint2.setVisible(true);
        lbHint.setVisible(false);
        pnlAddVehicleTask.setVisible(false);

      }
    });

    pnlAddVehicleTask.add(BtnBack22);

    // panels for each type of vehicle to add it
    // metro
    pnlMetro = new JPanel();
    pnlMetro.setBounds(50, 150, 400, 250);
    pnlMetro.setBackground(new Color(248, 249, 250));
    pnlMetro.setLayout(null);
    pnlMetro.setVisible(false);
    pnlAddVehicleTask.add(pnlMetro);

    // labels and textfields for metro
    JLabel lbIDMetro = new JLabel("ID:");
    lbIDMetro.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDMetro.setBounds(0, 10, 110, 25);
    pnlMetro.add(lbIDMetro);

    txtIDMtetro = new JTextField();
    txtIDMtetro.setBounds(120, 10, 220, 30);
    pnlMetro.add(txtIDMtetro);

    JLabel lbCapMetro = new JLabel("Max Capacity:");
    lbCapMetro.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbCapMetro.setBounds(0, 60, 110, 25);
    pnlMetro.add(lbCapMetro);

    txtCapMetro = new JTextField();
    txtCapMetro.setBounds(120, 60, 220, 30);
    pnlMetro.add(txtCapMetro);

    JLabel lbLineMetro = new JLabel("Line:");
    lbLineMetro.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbLineMetro.setBounds(0, 110, 110, 25);
    pnlMetro.add(lbLineMetro);

    txtLineMetro = new JTextField();
    txtLineMetro.setBounds(120, 110, 220, 30);
    pnlMetro.add(txtLineMetro);

    // message for exception
    lbAddMetro = new JLabel("");
    lbAddMetro.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbAddMetro.setBounds(120, 150, 300, 20);
    pnlMetro.add(lbAddMetro);

    // button to add metro
    JButton BtnAddMetro = new JButton("Add Metro");
    BtnAddMetro.setBackground(new Color(4, 64, 37));
    BtnAddMetro.setForeground(Color.WHITE);
    BtnAddMetro.setFont(new Font("Segoe UI", Font.BOLD, 13));
    BtnAddMetro.setBounds(125, 180, 150, 35);

    BtnAddMetro.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // get the text from the textfields
        String id = txtIDMtetro.getText();
        String cap = txtCapMetro.getText();
        String line = txtLineMetro.getText();

        // check if the textfields are empty
        if (id.isEmpty() || cap.isEmpty() || line.isEmpty()) {
          lbAddMetro.setForeground(Color.RED);
          lbAddMetro.setText("Error: All fields must be filled.");
          return;
        }
        try { // check if the capacity is a number
          int capacity = Integer.parseInt(cap);
          if (capacity <= 0) {
            lbAddMetro.setForeground(Color.RED);
            lbAddMetro.setText("Capacity must be greater than 0.");
            throw new IllegalArgumentException("Capacity must be greater than 0.");// exception

          }

          if (currentStation.getTransportUnitById(id) != null) { // check if the id is already exist
            lbAddMetro.setForeground(Color.RED);
            lbAddMetro.setText("Error: This ID is already exist.");
            return;
          }

          if (currentStation.isFull()) { // check if the station is full
            lbAddMetro.setForeground(Color.RED);
            lbAddMetro.setText("Error: Station is full. Cannot add more vehicles.");
            return;
          }

          boolean isAdded = currentStation.addTransportUnit(new Metro(id, capacity, line));// add the metro to the
                                                                                           // station
          if (isAdded) {// if the metro is added successfully
            lbAddMetro.setForeground(new Color(0, 150, 0));
            lbAddMetro.setText("Metro added successfully!");
            txtIDMtetro.setText("");
            txtCapMetro.setText("");
            txtLineMetro.setText("");
          }

        } catch (NumberFormatException ex) {// if the capacity is not a number
          lbAddMetro.setForeground(Color.RED);
          lbAddMetro.setText("Error: Capacity must be a valid number.");// exception

        }

      }
    });

    pnlMetro.add(BtnAddMetro);

    // bus
    pnlBus = new JPanel();
    pnlBus.setBounds(50, 150, 400, 250);
    pnlBus.setBackground(new Color(248, 249, 250));
    pnlBus.setLayout(null);
    pnlBus.setVisible(false);
    pnlAddVehicleTask.add(pnlBus);

    // labels and textfields for bus
    JLabel lbIDBus = new JLabel("ID:");
    lbIDBus.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDBus.setBounds(0, 10, 110, 25);
    pnlBus.add(lbIDBus);

    txtIDBus = new JTextField();
    txtIDBus.setBounds(120, 10, 220, 30);
    pnlBus.add(txtIDBus);

    JLabel lbCapBus = new JLabel("Max Capacity:");
    lbCapBus.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbCapBus.setBounds(0, 60, 110, 25);
    pnlBus.add(lbCapBus);

    txtCapBus = new JTextField();
    txtCapBus.setBounds(120, 60, 220, 30);
    pnlBus.add(txtCapBus);

    JLabel lbRouteBus = new JLabel("Route Number:");
    lbRouteBus.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbRouteBus.setBounds(0, 110, 110, 25);
    pnlBus.add(lbRouteBus);

    txtRouteBus = new JTextField();
    txtRouteBus.setBounds(120, 110, 220, 30);
    pnlBus.add(txtRouteBus);

    // message for exception
    lbAddBus = new JLabel("");
    lbAddBus.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbAddBus.setBounds(120, 150, 300, 20);
    pnlBus.add(lbAddBus);

    // button to add bus
    JButton BtnAddBus = new JButton("Add Bus");
    BtnAddBus.setBackground(new Color(4, 64, 37));
    BtnAddBus.setForeground(Color.WHITE);
    BtnAddBus.setFont(new Font("Segoe UI", Font.BOLD, 13));
    BtnAddBus.setBounds(125, 180, 150, 35);

    BtnAddBus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // clear the message
        lbAddBus.setText("");
        // get the text from the textfields
        String id = txtIDBus.getText();
        String cap = txtCapBus.getText();
        String route = txtRouteBus.getText();

        if (id.isEmpty() || cap.isEmpty() || route.isEmpty()) { // check if the textfields are empty
          lbAddBus.setForeground(Color.RED);
          lbAddBus.setText("Error: All fields must be filled.");
          return;
        }
        try {// check if the capacity is a number
          int capacity = Integer.parseInt(cap);
          if (capacity <= 0) {
            lbAddBus.setForeground(Color.RED);
            lbAddBus.setText("Capacity must be greater than 0.");
            throw new IllegalArgumentException("Capacity must be greater than 0.");// exception

          }

          if (currentStation.getTransportUnitById(id) != null) {// check if the id is already exist
            lbAddBus.setForeground(Color.RED);
            lbAddBus.setText("Error: This ID is already exist.");
            return;
          }
          if (currentStation.isFull()) {// check if the station is full
            lbAddBus.setForeground(Color.RED);
            lbAddBus.setText("Error: Station is full. Cannot add more vehicles.");
            return;
          }

          boolean isAdded = currentStation.addTransportUnit(new Bus(id, capacity, route));// add the bus to the station

          if (isAdded) {// if the bus is added successfully
            lbAddBus.setForeground(new Color(0, 150, 0));
            lbAddBus.setText("Bus added successfully!");
            txtIDBus.setText("");
            txtCapBus.setText("");
            txtRouteBus.setText("");

          }
        } catch (NumberFormatException ex) {// if the capacity is not a number
          lbAddBus.setForeground(Color.RED);
          lbAddBus.setText("Error: Capacity must be a valid number.");

        }

      }
    });

    pnlBus.add(BtnAddBus);

    // bus on demand
    pnlBusOnD = new JPanel();
    pnlBusOnD.setBounds(50, 150, 400, 250);
    pnlBusOnD.setBackground(new Color(248, 249, 250));
    pnlBusOnD.setLayout(null);
    pnlBusOnD.setVisible(false);
    pnlAddVehicleTask.add(pnlBusOnD);

    // labels and textfields for bus on demand
    JLabel lbIDBusOnD = new JLabel("ID:");
    lbIDBusOnD.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDBusOnD.setBounds(0, 10, 110, 25);
    pnlBusOnD.add(lbIDBusOnD);

    txtIDBusOnD = new JTextField();
    txtIDBusOnD.setBounds(120, 10, 220, 30);
    pnlBusOnD.add(txtIDBusOnD);

    JLabel lbCapBusOnD = new JLabel("Max Capacity:");
    lbCapBusOnD.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbCapBusOnD.setBounds(0, 60, 110, 25);
    pnlBusOnD.add(lbCapBusOnD);

    txtCapBusOnD = new JTextField();
    txtCapBusOnD.setBounds(120, 60, 220, 30);
    pnlBusOnD.add(txtCapBusOnD);

    JLabel lbRouteBusD = new JLabel("Route Number:");
    lbRouteBusD.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbRouteBusD.setBounds(0, 110, 110, 25);
    pnlBusOnD.add(lbRouteBusD);

    txtRouteBusD = new JTextField();
    txtRouteBusD.setBounds(120, 110, 220, 30);
    pnlBusOnD.add(txtRouteBusD);

    JLabel lbNiBusD = new JLabel("Neighborhood:");
    lbNiBusD.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbNiBusD.setBounds(0, 160, 110, 25);
    pnlBusOnD.add(lbNiBusD);

    txtNiBusD = new JTextField();
    txtNiBusD.setBounds(120, 160, 220, 30);
    pnlBusOnD.add(txtNiBusD);

    // message for exception
    lbAddBusOnD = new JLabel("");
    lbAddBusOnD.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbAddBusOnD.setBounds(120, 195, 300, 20);
    pnlBusOnD.add(lbAddBusOnD);

    // button to add bus on demand
    JButton BtnAddBusOnD = new JButton("Add Bus On Demand");
    BtnAddBusOnD.setBackground(new Color(4, 64, 37));
    BtnAddBusOnD.setForeground(Color.WHITE);
    BtnAddBusOnD.setFont(new Font("Segoe UI", Font.BOLD, 10));
    BtnAddBusOnD.setBounds(120, 220, 180, 35);

    BtnAddBusOnD.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // get the text from the textfields
        String id = txtIDBusOnD.getText();
        String cap = txtCapBusOnD.getText();
        String route = txtRouteBusD.getText();
        String ni = txtNiBusD.getText();

        if (id.isEmpty() || cap.isEmpty() || route.isEmpty() || ni.isEmpty()) {// check if the textfields are empty
          lbAddBusOnD.setForeground(Color.RED);
          lbAddBusOnD.setText("Error: All fields must be filled.");
          return;
        }
        try {// check if the capacity is a number
          int capacity = Integer.parseInt(cap);
          if (capacity <= 0) {
            lbAddBusOnD.setForeground(Color.RED);
            lbAddBusOnD.setText("Capacity must be greater than 0.");
            throw new IllegalArgumentException("Capacity must be greater than 0.");// exception

          }

          if (currentStation.getTransportUnitById(id) != null) {// check if the id is already exist
            lbAddBusOnD.setForeground(Color.RED);
            lbAddBusOnD.setText("Error: This ID is already exist.");
            return;
          }

          if (currentStation.isFull()) {// check if the station is full
            lbAddBusOnD.setForeground(Color.RED);
            lbAddBusOnD.setText("Error: Station is full. Cannot add more vehicles.");
            return;
          }
          boolean isAdded = currentStation.addTransportUnit(new BusOnDemand(id, capacity, route, ni));// add the bus on
                                                                                                      // demand to the
                                                                                                      // station

          if (isAdded) {// if the bus on demand is added successfully
            lbAddBusOnD.setForeground(new Color(0, 150, 0));
            lbAddBusOnD.setText("Bus On Demand added successfully!");
            txtIDBusOnD.setText("");
            txtCapBusOnD.setText("");
            txtRouteBusD.setText("");
            txtNiBusD.setText("");

          }
        } catch (NumberFormatException ex) {// if the capacity is not a number
          lbAddBusOnD.setForeground(Color.RED);
          lbAddBusOnD.setText("Error: Capacity must be a valid number.");// exception

        }

      }
    });

    pnlBusOnD.add(BtnAddBusOnD);

    // remove vehicle
    pnlRemoveVehicleTask = new JPanel();
    pnlRemoveVehicleTask.setBounds(0, 0, 500, 470);
    pnlRemoveVehicleTask.setBackground(new Color(248, 249, 250));
    pnlRemoveVehicleTask.setLayout(null);
    pnlRemoveVehicleTask.setVisible(false);
    mainContent.add(pnlRemoveVehicleTask);

    // title for remove vehicle
    JLabel lbTypeVehicleRemove = new JLabel("What type of vehicle to remove?");
    lbTypeVehicleRemove.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lbTypeVehicleRemove.setForeground(new Color(4, 64, 37));
    lbTypeVehicleRemove.setBounds(50, 30, 450, 30);
    pnlRemoveVehicleTask.add(lbTypeVehicleRemove);

    // buttons for type of vehicle
    // metro
    JButton BtnMetro1 = new JButton("Metro");
    BtnMetro1.setBackground(new Color(4, 64, 37));
    BtnMetro1.setForeground(Color.WHITE);
    BtnMetro1.setBounds(50, 90, 100, 35);
    pnlRemoveVehicleTask.add(BtnMetro1);

    BtnMetro1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels the panel
        pnlMetroRemove.setVisible(true);
        pnlBusDRemove.setVisible(false);
        pnlBusRemove.setVisible(false);
      }
    });

    // bus
    JButton BtnBus1 = new JButton("Bus");
    BtnBus1.setBackground(new Color(4, 64, 37));
    BtnBus1.setForeground(Color.WHITE);
    BtnBus1.setBounds(160, 90, 100, 35);
    pnlRemoveVehicleTask.add(BtnBus1);

    BtnBus1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels
        pnlBusRemove.setVisible(true);
        pnlMetroRemove.setVisible(false);
        pnlBusDRemove.setVisible(false);

      }
    });

    // bus on demand
    JButton BtnBusOnD = new JButton("Bus On Demand");
    BtnBusOnD.setBackground(new Color(4, 64, 37));
    BtnBusOnD.setForeground(Color.WHITE);
    BtnBusOnD.setBounds(270, 90, 150, 35);
    pnlRemoveVehicleTask.add(BtnBusOnD);

    BtnBusOnD.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels
        pnlBusDRemove.setVisible(true);
        pnlMetroRemove.setVisible(false);
        pnlBusRemove.setVisible(false);
      }
    });

    // back button
    JButton BtnBack221 = new JButton("Back");
    BtnBack221.setBackground(new Color(150, 150, 150));
    BtnBack221.setForeground(Color.WHITE);
    BtnBack221.setBounds(290, 400, 100, 35);

    BtnBack221.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // go back to the main panel, main is selectStationPanel2
        selectStationPanel2.setVisible(true);
        lbHint2.setVisible(true);
        lbHint.setVisible(false);
        pnlAddVehicleTask.setVisible(false);
        pnlRemoveVehicleTask.setVisible(false);

      }
    });

    pnlRemoveVehicleTask.add(BtnBack221);

    // panels for each type of vehicle to remove it
    // metro
    pnlMetroRemove = new JPanel();
    pnlMetroRemove.setBounds(50, 150, 400, 250);
    pnlMetroRemove.setBackground(new Color(248, 249, 250));
    pnlMetroRemove.setLayout(null);
    pnlMetroRemove.setVisible(false);
    pnlRemoveVehicleTask.add(pnlMetroRemove);

    // labels and textfields for metro
    JLabel lbIDMetroRemove = new JLabel("ID:");
    lbIDMetroRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDMetroRemove.setBounds(0, 10, 80, 25);
    pnlMetroRemove.add(lbIDMetroRemove);

    txtIDMtetroRemove = new JTextField();
    txtIDMtetroRemove.setBounds(90, 10, 220, 30);
    pnlMetroRemove.add(txtIDMtetroRemove);

    // message for exception
    lbMetroRemove = new JLabel("");
    lbMetroRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbMetroRemove.setBounds(0, 60, 110, 25);
    pnlMetroRemove.add(lbMetroRemove);

    // button to remove metro
    JButton BtnRemoveMetro = new JButton("Remove Metro");
    BtnRemoveMetro.setBackground(new Color(4, 64, 37));
    BtnRemoveMetro.setForeground(Color.WHITE);
    BtnRemoveMetro.setFont(new Font("Segoe UI", Font.BOLD, 13));
    BtnRemoveMetro.setBounds(100, 110, 150, 35);

    BtnRemoveMetro.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {// get the text from the textfield
          String id = txtIDMtetroRemove.getText();

          if (id.isEmpty()) {// check if the textfield is empty
            lbMetroRemove.setForeground(Color.RED);
            lbMetroRemove.setText("Error: All fields must be filled.");
            throw new IllegalArgumentException("Error: All fields must be filled.");// exception
          }
          TransportUnit unit = currentStation.getTransportUnitById(id);// check if the id is exist
          if (unit == null) {// if the id is not exist
            lbMetroRemove.setForeground(Color.RED);
            lbMetroRemove.setText("Error: This Metro is not exist.");
            throw new IllegalArgumentException("Error: This Metro is not exist.");// exception
          }

          // remove the metro from the station
          currentStation.removeTransportUnit(id);
          lbMetroRemove.setForeground(new Color(0, 150, 0));
          lbMetroRemove.setText("Metro removed successfully!");
          txtIDMtetroRemove.setText("");

        } catch (Exception ex) {// if there is an error in textfield or id
          lbMetroRemove.setForeground(Color.RED);
          lbMetroRemove.setText("Error: An unexpected error occurred.");
        }

      }
    });

    pnlMetroRemove.add(BtnRemoveMetro);

    // bus
    pnlBusRemove = new JPanel();
    pnlBusRemove.setBounds(50, 150, 400, 250);
    pnlBusRemove.setBackground(new Color(248, 249, 250));
    pnlBusRemove.setLayout(null);
    pnlBusRemove.setVisible(false);
    pnlRemoveVehicleTask.add(pnlBusRemove);

    // labels and textfields for bus
    JLabel lbIDBusRemove = new JLabel("ID:");
    lbIDBusRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDBusRemove.setBounds(0, 10, 80, 25);
    pnlBusRemove.add(lbIDBusRemove);

    txtIDBusRemove = new JTextField();
    txtIDBusRemove.setBounds(90, 10, 220, 30);
    pnlBusRemove.add(txtIDBusRemove);

    // message for exception
    lbBusRemove = new JLabel("");
    lbBusRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbBusRemove.setBounds(0, 60, 350, 25);
    pnlBusRemove.add(lbBusRemove);

    // button to remove bus
    JButton BtnRemoveBus = new JButton("Remove Bus");
    BtnRemoveBus.setBackground(new Color(4, 64, 37));
    BtnRemoveBus.setForeground(Color.WHITE);
    BtnRemoveBus.setFont(new Font("Segoe UI", Font.BOLD, 13));
    BtnRemoveBus.setBounds(100, 110, 150, 35);

    BtnRemoveBus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {// get the text from the textfield
          String id = txtIDBusRemove.getText();

          if (id.isEmpty()) {// check if the textfield is empty
            lbBusRemove.setForeground(Color.RED);
            lbBusRemove.setText("Error: All fields must be filled.");
            throw new IllegalArgumentException("Error: All fields must be filled.");// exception
          }
          TransportUnit unit = currentStation.getTransportUnitById(id);// check if the id is exist
          if (unit == null) {// if the id is not exist
            lbBusRemove.setForeground(Color.RED);
            lbBusRemove.setText("Error: This Bus is not exist.");
            throw new IllegalArgumentException("Error: This Bus is not exist.");// exception
          }

          // remove the bus from the station
          currentStation.removeTransportUnit(id);
          lbBusRemove.setForeground(new Color(0, 150, 0));
          lbBusRemove.setText("Bus removed successfully!");
          // lbBusRemove.setText("");
        } catch (Exception ex) {// if there is an error in textfield or id
          lbBusRemove.setForeground(Color.RED);
          lbBusRemove.setText("Error: An unexpected error occurred.");
        }

      }
    });

    pnlBusRemove.add(BtnRemoveBus);

    // bus on demand
    pnlBusDRemove = new JPanel();
    pnlBusDRemove.setBounds(50, 150, 400, 250);
    pnlBusDRemove.setBackground(new Color(248, 249, 250));
    pnlBusDRemove.setLayout(null);
    pnlBusDRemove.setVisible(false);
    pnlRemoveVehicleTask.add(pnlBusDRemove);

    // labels and textfields for bus on demand
    JLabel lbIDBusDRemove = new JLabel("ID:");
    lbIDBusDRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbIDBusDRemove.setBounds(0, 10, 80, 25);
    pnlBusDRemove.add(lbIDBusDRemove);

    txtIDBusDRemove = new JTextField();
    txtIDBusDRemove.setBounds(90, 10, 220, 30);
    pnlBusDRemove.add(txtIDBusDRemove);

    // message for exception
    lbBusDRemove = new JLabel("");
    lbBusDRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lbBusDRemove.setBounds(90, 215, 300, 20);
    pnlBusDRemove.add(lbBusDRemove);

    // button to remove bus on demand
    JButton BtnRemoveBusD = new JButton("Remove Bus On Demand");
    BtnRemoveBusD.setBackground(new Color(4, 64, 37));
    BtnRemoveBusD.setForeground(Color.WHITE);
    BtnRemoveBusD.setFont(new Font("Segoe UI", Font.BOLD, 11));
    BtnRemoveBusD.setBounds(100, 110, 200, 35);

    BtnRemoveBusD.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {// get the text from the textfield
          String id = txtIDBusDRemove.getText();

          if (id.isEmpty()) {// check if the textfield is empty
            lbBusDRemove.setForeground(Color.RED);
            lbBusDRemove.setText("Error: All fields must be filled.");
            throw new IllegalArgumentException("Error: All fields must be filled.");// exception
          }
          TransportUnit unit = currentStation.getTransportUnitById(id);// check if the id is exist
          if (unit == null) {// if the id is not exist
            lbBusDRemove.setForeground(Color.RED);
            lbBusDRemove.setText("Error: This Bus is not exist.");
            throw new IllegalArgumentException("Error: This Bus On Demand is not exist.");// exception
          }

          // remove the bus on demand from the station
          currentStation.removeTransportUnit(id);
          lbBusDRemove.setForeground(new Color(0, 150, 0));
          lbBusDRemove.setText("Bus On Demand removed successfully!");
          lbBusDRemove.setText("");
        } catch (Exception ex) {// if there is an error in textfield or id
          lbBusDRemove.setForeground(Color.RED);
          lbBusDRemove.setText("Error: An unexpected error occurred.");
        }
      }
    });

    pnlBusDRemove.add(BtnRemoveBusD);

    // add botton to new left side bar
    // add vehicle to the station
    JButton btnAddVehicle = new JButton("Add Vehicle");
    btnAddVehicle.setForeground(Color.WHITE);
    btnAddVehicle.setBackground(new Color(4, 64, 37));
    btnAddVehicle.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnAddVehicle.setFocusPainted(false);
    btnAddVehicle.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnAddVehicle.setBounds(20, 90, 210, 45);
    btnAddVehicle.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels

        hideAllManagerPanels();
        pnlAddVehicleTask.setVisible(true);
        selectStationPanel2.setVisible(false);
        lbHint2.setVisible(false);
        pnlAddVehicleTask.setVisible(true);
        lbHint.setVisible(false);
        pnlAddStationTask.setVisible(false);
        pnlRemoveStationTask.setVisible(false);
        pnlAddEmployeeTask.setVisible(false);
        pnlEmployeeMain.setVisible(false);

      }
    });
    leftSideBarSelectStation.add(btnAddVehicle);

    // remove vehicle from the station
    JButton btnRemoveVehicle = new JButton("Remove Vehicle");
    btnRemoveVehicle.setForeground(Color.WHITE);
    btnRemoveVehicle.setBackground(new Color(4, 64, 37));
    btnRemoveVehicle.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnRemoveVehicle.setFocusPainted(false);
    btnRemoveVehicle.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnRemoveVehicle.setBounds(20, 140, 210, 45);
    btnRemoveVehicle.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels

        selectStationPanel2.setVisible(false);
        lbHint2.setVisible(false);
        pnlRemoveVehicleTask.setVisible(true);
        lbHint.setVisible(false);
        pnlAddStationTask.setVisible(false);
        pnlRemoveStationTask.setVisible(false);
        pnlAddEmployeeTask.setVisible(false);
        pnlAddVehicleTask.setVisible(false);
        pnlEmployeeMain.setVisible(false);

      }
    });

    leftSideBarSelectStation.add(btnRemoveVehicle);

    // assign employee to the station
    pnlAssignEmployee = new JPanel();
    pnlAssignEmployee.setBounds(0, 0, 500, 470);
    pnlAssignEmployee.setBackground(new Color(248, 249, 250));
    pnlAssignEmployee.setLayout(null);
    pnlAssignEmployee.setVisible(false);
    mainContent.add(pnlAssignEmployee);

    // JRadioButton for type of vehicle to assign employee in it
    // Metro
    rbMetro = new JRadioButton("Metro");
    rbMetro.setBounds(100, 40, 70, 30);
    rbMetro.setForeground(Color.WHITE);
    rbMetro.setBackground(new Color(4, 64, 37));
    rbMetro.setFont(new Font("Segoe UI", Font.BOLD, 13));
    rbMetro.setFocusPainted(false);
    rbMetro.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    rbMetro.setSelected(true); // by default is chosen
    pnlAssignEmployee.add(rbMetro);

    // Bus
    rbBus = new JRadioButton("Bus");
    rbBus.setBounds(180, 40, 70, 30);
    rbBus.setForeground(Color.WHITE);
    rbBus.setBackground(new Color(4, 64, 37));
    rbBus.setFont(new Font("Segoe UI", Font.BOLD, 13));
    rbBus.setFocusPainted(false);
    rbBus.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    pnlAssignEmployee.add(rbBus);

    // Bus On Demand
    rbBusOnDemand = new JRadioButton("Bus On Demand");
    rbBusOnDemand.setForeground(Color.WHITE);
    rbBusOnDemand.setBackground(new Color(4, 64, 37));
    rbBusOnDemand.setFont(new Font("Segoe UI", Font.BOLD, 13));
    rbBusOnDemand.setFocusPainted(false);
    rbBusOnDemand.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    rbBusOnDemand.setBounds(260, 40, 130, 30);
    pnlAssignEmployee.add(rbBusOnDemand);

    // Group the radio buttons to make sure only one is selected at a time
    ButtonGroup bgVehicleType = new ButtonGroup();
    bgVehicleType.add(rbMetro);
    bgVehicleType.add(rbBus);
    bgVehicleType.add(rbBusOnDemand);

    // labels and textfields for assign employee by id
    JLabel lbVID = new JLabel("Vehicle ID:");
    lbVID.setBounds(50, 90, 100, 25);
    pnlAssignEmployee.add(lbVID);

    txtVehicleID = new JTextField();
    txtVehicleID.setBounds(150, 90, 180, 30);
    pnlAssignEmployee.add(txtVehicleID);

    JLabel lbEID = new JLabel("Employee ID:");
    lbEID.setBounds(50, 130, 100, 25);
    pnlAssignEmployee.add(lbEID);

    txtEmployeeID = new JTextField();
    txtEmployeeID.setBounds(150, 130, 180, 30);
    pnlAssignEmployee.add(txtEmployeeID);

    // message for exception
    lbAssignStatus = new JLabel("");
    lbAssignStatus.setBounds(100, 170, 400, 20);
    pnlAssignEmployee.add(lbAssignStatus);

    // button to assign employee to the vehicle
    JButton btnAssignConfirm = new JButton("Assign Employee");
    btnAssignConfirm.setForeground(Color.WHITE);
    btnAssignConfirm.setBackground(new Color(4, 64, 37));
    btnAssignConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnAssignConfirm.setFocusPainted(false);
    btnAssignConfirm.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnAssignConfirm.setBounds(130, 200, 150, 35);
    pnlAssignEmployee.add(btnAssignConfirm);

    btnAssignConfirm.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {// get the text from the textfields
          String vID = txtVehicleID.getText().trim();// trim to remove any spaces
          String eID = txtEmployeeID.getText().trim();

          if (vID.isEmpty() || eID.isEmpty()) {// check if the textfields are empty
            lbAssignStatus.setForeground(Color.RED);
            lbAssignStatus.setText("Please fill all ID fields.");
            throw new Exception("Please fill all ID fields.");// exception

          }

          TransportUnit unit = currentStation.getTransportUnitById(vID);// check if the id vehicle is exist
          if (unit == null) {// if the id is not exist
            lbAssignStatus.setForeground(Color.RED);
            lbAssignStatus.setText("Vehicle ID not found in this station.");
            throw new Exception("Vehicle ID not found in this station.");// exception
          }

          Employee emp = riyadhMetro.getEmployeeById(eID);// check if the id employee is exist
          if (emp == null) {// if the id is not exist
            lbAssignStatus.setForeground(Color.RED);
            lbAssignStatus.setText("Employee ID not found in the system.");
            throw new Exception("Employee ID not found in the system.");// exception
          }

          if (rbBus.isSelected() || rbBusOnDemand.isSelected()) {// check if the vehicle is bus or bus on demand
            if (!emp.getPosition().equals("Driver")) {// check if the employee is driver only for bus and bus on demand
              lbAssignStatus.setForeground(Color.RED);
              lbAssignStatus.setText("Only a Driver can be assigned to a Bus.");
              throw new Exception("Error: Only a Driver can be assigned to a Bus.");// exception
            }
          }

          boolean assigned = currentStation.assignEmployee(emp);

          if (assigned) {
            lbAssignStatus.setForeground(new Color(0, 150, 0));
            lbAssignStatus.setText("Success: Employee assigned to " + vID);
            txtVehicleID.setText("");
            txtEmployeeID.setText("");
          } else {
            throw new Exception("Failed: Station/Vehicle is full.");// exception
          }

        } catch (Exception ex) {// if there is an error in textfield or id
          lbAssignStatus.setForeground(Color.RED);
          lbAssignStatus.setText("Error: " + ex.getMessage());
        }
      }
    });

    // back button
    JButton BtnBack2211 = new JButton("Back");
    BtnBack2211.setBackground(new Color(150, 150, 150));
    BtnBack2211.setForeground(Color.WHITE);
    BtnBack2211.setBounds(300, 200, 100, 35);

    BtnBack2211.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // go back to the main panel, main is selectStationPanel2
        selectStationPanel2.setVisible(true);
        lbHint2.setVisible(true);
        lbHint.setVisible(false);
        pnlAddVehicleTask.setVisible(false);
        pnlRemoveVehicleTask.setVisible(false);
        pnlAssignEmployee.setVisible(false);

      }
    });

    pnlAssignEmployee.add(BtnBack2211);

    // assign employee to the station
    JButton btnAssignEmployee = new JButton("Assign Employee");
    btnAssignEmployee.setForeground(Color.WHITE);
    btnAssignEmployee.setBackground(new Color(4, 64, 37));
    btnAssignEmployee.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnAssignEmployee.setFocusPainted(false);
    btnAssignEmployee.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnAssignEmployee.setBounds(20, 190, 210, 45);
    btnAssignEmployee.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels
        selectStationPanel2.setVisible(false);
        lbHint2.setVisible(false);
        pnlAssignEmployee.setVisible(true);
        lbHint.setVisible(false);
        pnlAddStationTask.setVisible(false);
        pnlRemoveStationTask.setVisible(false);
        pnlAddEmployeeTask.setVisible(false);
        pnlEmployeeMain.setVisible(false);
        pnlRemoveVehicleTask.setVisible(false);

      }
    });
    leftSideBarSelectStation.add(btnAssignEmployee);

    // back button to main menu
    JButton BtnBack = new JButton("Back to Main Menu");
    BtnBack.setForeground(Color.WHITE);
    BtnBack.setBackground(new Color(4, 64, 37));
    BtnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
    BtnBack.setFocusPainted(false);
    BtnBack.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    BtnBack.setBounds(20, 290, 210, 45);
    leftSideBarSelectStation.add(BtnBack);

    BtnBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // go back to the main panel, main is selectStationPanel
        selectStationPanel.setVisible(true);
        selectStationPanel2.setVisible(false);
        leftSideBarSelectStation.setVisible(false);
        lbHint2.setVisible(false);
        leftSideBar.setVisible(true);
        lbHint.setVisible(false);
        txtSelectStationName.setText("");

      }
    });

    // Maintenance Panel
    pnlMaintenanceMain = new JPanel();
    pnlMaintenanceMain.setBounds(0, 0, 500, 470);
    pnlMaintenanceMain.setBackground(new Color(248, 249, 250));
    pnlMaintenanceMain.setLayout(null);
    pnlMaintenanceMain.setVisible(false);
    mainContent.add(pnlMaintenanceMain);

    JLabel lblMaintMainTitle = new JLabel("System Maintenance");
    lblMaintMainTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblMaintMainTitle.setForeground(new Color(4, 64, 37));
    lblMaintMainTitle.setBounds(30, 20, 350, 30);
    pnlMaintenanceMain.add(lblMaintMainTitle);

    JPanel pnlFinance = new JPanel();
    pnlFinance.setBounds(30, 70, 440, 80);
    pnlFinance.setBackground(Color.WHITE);
    pnlFinance.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
        "Financial Status"));
    pnlFinance.setLayout(null);
    pnlMaintenanceMain.add(pnlFinance);
    lblCurrentProfit = new JLabel("Current Profit: $" + riyadhMetro.getProfit());
    lblCurrentProfit.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblCurrentProfit.setForeground(new Color(0, 100, 0));
    lblCurrentProfit.setBounds(20, 30, 200, 25);
    pnlFinance.add(lblCurrentProfit);

    lblMaintCost = new JLabel("Estimated Repair Cost: $0");
    lblMaintCost.setFont(new Font("Segoe UI", Font.BOLD, 14));
    lblMaintCost.setForeground(new Color(200, 0, 0));
    lblMaintCost.setBounds(200, 30, 230, 25);
    pnlFinance.add(lblMaintCost);

    btnRunMaint = new JButton("Do Maintenance Check");
    btnRunMaint.setBackground(new Color(4, 64, 37));
    btnRunMaint.setForeground(Color.WHITE);
    btnRunMaint.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnRunMaint.setBounds(120, 170, 260, 45);
    pnlMaintenanceMain.add(btnRunMaint);

    // progress bar visual
    maintProgressBar = new JProgressBar();
    maintProgressBar.setBounds(50, 230, 400, 20);
    maintProgressBar.setStringPainted(true);
    maintProgressBar.setVisible(false);
    pnlMaintenanceMain.add(maintProgressBar);

    txtMaintResult = new JTextArea();
    txtMaintResult.setEditable(false);
    txtMaintResult.setLineWrap(true);
    txtMaintResult.setWrapStyleWord(true);
    txtMaintResult.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    txtMaintResult.setBackground(new Color(230, 235, 240));
    txtMaintResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JScrollPane maintScroll = new JScrollPane(txtMaintResult);
    maintScroll.setBounds(50, 270, 400, 140);
    maintScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    pnlMaintenanceMain.add(maintScroll);
    // if we click this btn, it runs the maintenance check and updates the profit.
    // the progress bar is animated to show the process and resets at every click.
    // it also disables the button during the process.
    btnRunMaint.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Prevent double-clicks during animation
        btnRunMaint.setEnabled(false);
        txtMaintResult.setText("Scanning network...");

        // Reset and show progress bar every run
        maintProgressBar.setValue(0);
        maintProgressBar.setVisible(true);

        // Refresh the profit label to current value before starting
        lblCurrentProfit.setText("Current Profit: $" + riyadhMetro.getProfit());
        lblMaintCost.setText("Estimated Repair Cost: calculating...");

        Timer timer = new Timer(30, new ActionListener() {
          int progress = 0;

          public void actionPerformed(ActionEvent evt) {
            progress += 2;
            maintProgressBar.setValue(progress);

            if (progress >= 100) {
              ((Timer) evt.getSource()).stop();

              // Run maintenance and capture the result
              String resultMessage = riyadhMetro.maintenance();
              txtMaintResult.setText(resultMessage);

              // Update profit label after maintenance deducts costs
              lblCurrentProfit.setText("Current Profit: $" + riyadhMetro.getProfit());

              double newProfit = riyadhMetro.getProfit();
              lblMaintCost.setText("Estimated Repair Cost: see report below");

              // Hide bar and re-enable button
              maintProgressBar.setVisible(false);
              maintProgressBar.setValue(0);
              btnRunMaint.setEnabled(true);
            }
          }
        });

        timer.start();
      }
    });

    // left side bar for manager tasks
    leftSideBar = new JPanel();
    leftSideBar.setBounds(0, 70, 250, 530);
    leftSideBar.setBackground(new Color(4, 44, 37));
    leftSideBar.setLayout(null);
    managerTasksPanel.add(leftSideBar);

    // buttons

    JButton btnAddStation = new JButton("Add Station");
    btnAddStation.setForeground(Color.WHITE);
    btnAddStation.setBackground(new Color(150, 150, 150));
    btnAddStation.setForeground(Color.WHITE);
    btnAddStation.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnAddStation.setFocusPainted(false);
    btnAddStation.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnAddStation.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover
    btnAddStation.setBounds(290, 150, 130, 35);
    btnAddStation.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {// disabling all other panels and enabling the add station panel
        hideAllManagerPanels();
        pnlAddStationTask.setVisible(true);
      }
    });
    selectStationPanel.add(btnAddStation);

    // remove station button
    JButton btnRemoveStation = new JButton("Remove Station");
    btnRemoveStation.setForeground(Color.WHITE);
    btnRemoveStation.setBackground(new Color(4, 64, 37));
    btnRemoveStation.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnRemoveStation.setFocusPainted(false);

    btnRemoveStation.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnRemoveStation.setBounds(20, 240, 210, 45);

    btnRemoveStation.addActionListener(new ActionListener() {
      // if we click this btn do this, disaling all other panels and enabling the
      // remove station panel
      public void actionPerformed(ActionEvent e) {
        hideAllManagerPanels();
        pnlRemoveStationTask.setVisible(true);

      }
    });
    leftSideBarSelectStation.add(btnRemoveStation);

    // add botton Manage Stations to left side bar
    JButton btnMangStation = new JButton("Manage Stations");
    btnMangStation.setForeground(Color.WHITE);
    btnMangStation.setBackground(new Color(4, 64, 37));
    btnMangStation.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnMangStation.setFocusPainted(false);
    btnMangStation.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnMangStation.setBounds(20, 20, 210, 45);
    btnMangStation.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels
        selectStationPanel.setVisible(true);
        lbHint.setVisible(false);
        pnlAddStationTask.setVisible(false);
        pnlRemoveStationTask.setVisible(false);
        pnlAddEmployeeTask.setVisible(false);
        pnlEmployeeMain.setVisible(false);
        pnlMaintenanceMain.setVisible(false);
        lblExceptionMessage1.setText("");
        txtSelectStationName.setText("");

      }
    });

    leftSideBar.add(btnMangStation);

    // add button Manage Employees to left side bar
    JButton btnEmployeeManage = new JButton("Manage Employees");
    btnEmployeeManage.setForeground(Color.WHITE);
    btnEmployeeManage.setBackground(new Color(4, 64, 37));
    btnEmployeeManage.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnEmployeeManage.setFocusPainted(false);
    btnEmployeeManage.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnEmployeeManage.setBounds(20, 70, 210, 45);
    btnEmployeeManage.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // show the panel and hide the other panels
        hideAllManagerPanels();
        pnlEmployeeMain.setVisible(true);

      }
    });
    leftSideBar.add(btnEmployeeManage);

    // add botton Maintenance to left side bar
    JButton btnMaintenance = new JButton("Maintenance");
    btnMaintenance.setForeground(Color.WHITE);
    btnMaintenance.setBackground(new Color(4, 64, 37));
    btnMaintenance.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnMaintenance.setFocusPainted(false);
    btnMaintenance.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnMaintenance.setBounds(20, 120, 210, 45);
    btnMaintenance.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels
        hideAllManagerPanels();
        pnlMaintenanceMain.setVisible(true);
      }
    });
    leftSideBar.add(btnMaintenance);

    // add botton Display Network Info to left side bar
    JButton btnDisplayInfo = new JButton("Display Network Info");
    btnDisplayInfo.setForeground(Color.WHITE);
    btnDisplayInfo.setBackground(new Color(4, 64, 37));
    btnDisplayInfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnDisplayInfo.setFocusPainted(false);
    btnDisplayInfo.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    btnDisplayInfo.setBounds(20, 170, 210, 45);
    btnDisplayInfo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show the panel and hide the other panels
        /*
         * lbHint.setVisible(false);
         * pnlAddStationTask.setVisible(false);
         * pnlRemoveStationTask.setVisible(false);
         * pnlAddEmployeeTask.setVisible(false);
         * pnlEmployeeMain.setVisible(false);
         * pnlMaintenanceMain.setVisible(false);
         */
        hideAllManagerPanels();
        DisplayNetworkFrame displayFrame = new DisplayNetworkFrame(riyadhMetro);// create new frame to display the info
        displayFrame.setVisible(true);
      }
    });
    leftSideBar.add(btnDisplayInfo);
    // add botton Logout to left side bar
    JButton btnLogout = new JButton("Logout");
    btnLogout.setBackground(new Color(217, 48, 37));
    btnLogout.setForeground(Color.WHITE);
    btnLogout.setBounds(20, 450, 210, 45);
    btnLogout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        saveProgress("riyadh_metro.data");// saving progress when closing the window inside the program
        managerTasksPanel.setVisible(false);// hide the panel
        UI.setVisible(true);// show the panel
      }
    });
    leftSideBar.add(btnLogout);

    // Passenger login panel
    passengerPanel = new JPanel();
    // passengerPanel.setLayout(null);
    passengerPanel.setBounds(0, 0, 784, 561);
    passengerPanel.setBackground(new Color(248, 249, 250));
    passengerPanel.setLayout(null);
    passengerPanel.setVisible(false);

    contentPane.add(passengerPanel);

    // title for second panel
    JLabel NewLabelPL = new JLabel("Passenger Login");
    NewLabelPL.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelPL.setFont((new Font("Segoe UI", Font.BOLD, 26)));
    NewLabelPL.setForeground(new Color(4, 64, 37));
    NewLabelPL.setBounds(242, 50, 300, 40);

    passengerPanel.add(NewLabelPL);

    // Login to existing account button
    JButton btnLogin = new JButton("Login to existing account");
    btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnLogin.setForeground(Color.WHITE);
    btnLogin.setBackground(new Color(4, 64, 37));
    btnLogin.setBorderPainted(false);
    btnLogin.setBounds(267, 225, 255, 35);
    // btnLogin.setBounds(342, 225, 100, 35);
    btnLogin.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        passengerLoginPanel.setVisible(true);
        passengerPanel.setVisible(false);
      }

    });
    passengerPanel.add(btnLogin);

    // Create new account button
    JButton btnCreate = new JButton("Creat new account");
    btnCreate.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnCreate.setForeground(Color.WHITE);
    btnCreate.setBackground(new Color(4, 64, 37));
    btnCreate.setBorderPainted(false);
    btnCreate.setBounds(294, 275, 200, 35);
    btnCreate.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        passengerPanel.setVisible(false);
        passengerCreateAccountPanel.setVisible(true);
      }
    });
    passengerPanel.add(btnCreate);

    // adding button back to passenger panel
    JButton btnBackPassenger = new JButton("Back");
    btnBackPassenger.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnBackPassenger.setForeground(Color.WHITE);
    btnBackPassenger.setBackground(new Color(4, 64, 37));
    btnBackPassenger.setBorderPainted(false);
    btnBackPassenger.setBounds(342, 325, 100, 30);
    btnBackPassenger.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        passengerPanel.setVisible(false);
        UI.setVisible(true);
      }
    });
    passengerPanel.add(btnBackPassenger);

    // passenger login panel
    passengerLoginPanel = new JPanel();
    passengerLoginPanel.setBounds(0, 0, 784, 561);
    passengerLoginPanel.setBackground(new Color(248, 249, 250));
    passengerLoginPanel.setLayout(null);
    passengerLoginPanel.setVisible(false);
    contentPane.add(passengerLoginPanel);

    // title for second panel
    JLabel NewLabelPL2 = new JLabel("Login to existing account");
    NewLabelPL2.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelPL2.setFont((new Font("Segoe UI", Font.BOLD, 26)));
    NewLabelPL2.setForeground(new Color(4, 64, 37));
    NewLabelPL2.setBounds(157, 50, 500, 40);
    passengerLoginPanel.add(NewLabelPL2);

    // title for name
    JLabel NewLabelName2 = new JLabel("Enter Name");
    NewLabelName2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelName2.setBounds(292, 150, 100, 20);
    passengerLoginPanel.add(NewLabelName2);

    // textfield to enter name
    txtPassengerName = new JTextField();
    txtPassengerName.setBounds(292, 175, 200, 30);
    passengerLoginPanel.add(txtPassengerName);

    // title for id
    JLabel NewLabelID2 = new JLabel("Enter ID");
    NewLabelID2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelID2.setBounds(292, 220, 100, 20);
    passengerLoginPanel.add(NewLabelID2);

    // textfield to enter id
    txtPassengerID = new JTextField();
    txtPassengerID.setBounds(292, 245, 200, 30);
    passengerLoginPanel.add(txtPassengerID);

    // check box for not robot
    NotRobotP1 = new JCheckBox("I'm not a robot");
    NotRobotP1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NotRobotP1.setBackground(new Color(248, 249, 250));
    NotRobotP1.setBounds(292, 280, 150, 25);
    passengerLoginPanel.add(NotRobotP1);

    // button to conform login and check password
    JButton btnConfirmLogin2 = new JButton("Login");
    btnConfirmLogin2.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnConfirmLogin2.setForeground(Color.WHITE);
    btnConfirmLogin2.setBackground(new Color(4, 64, 37));
    btnConfirmLogin2.setBorderPainted(false);
    btnConfirmLogin2.setBounds(342, 310, 100, 35);
    btnConfirmLogin2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String name = txtPassengerName.getText();
        String id = txtPassengerID.getText();
        loggedInPassenger = riyadhMetro.getPassenger(id);

        // Check if the passenger exists in the system
        if (loggedInPassenger == null) {
          JOptionPane.showMessageDialog(MetroGui.this, "Account not found. Please try again or create an account.",
              "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          if (NotRobotP1.isSelected()) {
            JOptionPane.showMessageDialog(MetroGui.this,
                "Login Successful! Welcome, Passenger " + loggedInPassenger.getName());
            setPassengerInfo();
            passengerLoginPanel.setVisible(false);
            passengerTasksPanel.setVisible(true);
            txtPassengerName.setText("");
            txtPassengerID.setText("");
            NotRobotP1.setSelected(false);
          } else {
            JOptionPane.showMessageDialog(MetroGui.this, "Please confirm you are not a robot", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    passengerLoginPanel.add(btnConfirmLogin2);

    // button back
    JButton btnBack2 = new JButton("Back");
    btnBack2.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnBack2.setForeground(Color.WHITE);
    btnBack2.setBackground(new Color(4, 64, 37));
    btnBack2.setBorderPainted(false);
    btnBack2.setBounds(342, 360, 100, 30);
    btnBack2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        passengerLoginPanel.setVisible(false);
        passengerPanel.setVisible(true);
      }
    });

    passengerLoginPanel.add(btnBack2);

    // passenger create account panel
    passengerCreateAccountPanel = new JPanel();
    passengerCreateAccountPanel.setBounds(0, 0, 784, 561);
    passengerCreateAccountPanel.setBackground(new Color(248, 249, 250));
    passengerCreateAccountPanel.setLayout(null);
    passengerCreateAccountPanel.setVisible(false);
    contentPane.add(passengerCreateAccountPanel);

    // title for second panel
    JLabel NewLabelPL3 = new JLabel("Create new account");
    NewLabelPL3.setHorizontalAlignment(SwingConstants.CENTER);
    NewLabelPL3.setFont((new Font("Segoe UI", Font.BOLD, 26)));
    NewLabelPL3.setForeground(new Color(4, 64, 37));
    NewLabelPL3.setBounds(242, 50, 300, 40);
    passengerCreateAccountPanel.add(NewLabelPL3);

    // title for name
    JLabel NewLabelName3 = new JLabel("Enter Name");
    NewLabelName3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelName3.setBounds(292, 150, 100, 20);
    passengerCreateAccountPanel.add(NewLabelName3);

    // textfield to enter name
    txtPassengerName2 = new JTextField();
    txtPassengerName2.setBounds(292, 175, 200, 30);
    passengerCreateAccountPanel.add(txtPassengerName2);

    // title for id
    JLabel NewLabelID3 = new JLabel("Enter ID");
    NewLabelID3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NewLabelID3.setBounds(292, 220, 100, 20);
    passengerCreateAccountPanel.add(NewLabelID3);

    // textfield to enter id
    txtPassengerID2 = new JTextField();
    txtPassengerID2.setBounds(292, 245, 200, 30);
    passengerCreateAccountPanel.add(txtPassengerID2);

    NotRobotP2 = new JCheckBox("I'm not a robot");
    NotRobotP2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    NotRobotP2.setBackground(new Color(248, 249, 250));
    NotRobotP2.setBounds(292, 280, 150, 25);
    passengerCreateAccountPanel.add(NotRobotP2);

    // button to conform login and check password
    JButton btnConfirmLogin3 = new JButton("Create");
    btnConfirmLogin3.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnConfirmLogin3.setForeground(Color.WHITE);
    btnConfirmLogin3.setBackground(new Color(4, 64, 37));
    btnConfirmLogin3.setBorderPainted(false);
    btnConfirmLogin3.setBounds(342, 310, 100, 35);
    btnConfirmLogin3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          String newName = txtPassengerName2.getText();
          String newId = txtPassengerID2.getText();
          // Check if the fields are empty
          if (newName.isEmpty() || newId.isEmpty()) {
            JOptionPane.showMessageDialog(MetroGui.this, "Error: All fields must be filled.", "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
          }
          // Check if the 'I'm not a robot' checkbox is selected
          if (NotRobotP2.isSelected()) {
            if (riyadhMetro.getPassenger(newId) != null) {
              JOptionPane.showMessageDialog(MetroGui.this, "Error: This ID already exists.", "Error",
                  JOptionPane.ERROR_MESSAGE);
              return;
            }
            // Create a new passenger and add them to the system
            loggedInPassenger = new Passenger(newName, newId, 0);
            riyadhMetro.addPassenger(loggedInPassenger);

            JOptionPane.showMessageDialog(MetroGui.this, "Account created successfully!");
            // Update the welcome message and ID label
            setPassengerInfo();

            passengerCreateAccountPanel.setVisible(false);
            pnlBuyTicket.setVisible(false);
            pnlDisplayInfo.setVisible(false);
            passengerTasksPanel.setVisible(true);
            txtPassengerName2.setText("");
            txtPassengerID2.setText("");
            NotRobotP2.setSelected(false);

          } else {
            JOptionPane.showMessageDialog(MetroGui.this, "Please confirm you are not a robot", "Error",
                JOptionPane.ERROR_MESSAGE);
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(MetroGui.this, "Error: Could not create account.", "Error",
              JOptionPane.ERROR_MESSAGE);
        }

      }
    });
    passengerCreateAccountPanel.add(btnConfirmLogin3);

    // button back
    JButton btnBack3 = new JButton("Back");
    btnBack3.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btnBack3.setForeground(Color.WHITE);
    btnBack3.setBackground(new Color(4, 64, 37));
    btnBack3.setBorderPainted(false);
    btnBack3.setBounds(342, 360, 100, 30);
    btnBack3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        passengerCreateAccountPanel.setVisible(false);
        passengerPanel.setVisible(true);
      }
    });
    passengerCreateAccountPanel.add(btnBack3);

    // passenger tasks panel
    passengerTasksPanel = new JPanel();
    passengerTasksPanel.setBounds(0, 0, 800, 600);
    passengerTasksPanel.setBackground(new Color(248, 249, 250));
    passengerTasksPanel.setLayout(null);
    passengerTasksPanel.setVisible(false);
    contentPane.add(passengerTasksPanel);

    JPanel topHeaderP = new JPanel();
    topHeaderP.setBackground(new Color(4, 64, 37));
    topHeaderP.setBounds(0, 0, 800, 70);
    topHeaderP.setLayout(null);
    passengerTasksPanel.add(topHeaderP);

    // changes to the welcome message according to the user specificly
    lbWelcomeP = new JLabel("");
    lbWelcomeP.setForeground(Color.WHITE);
    lbWelcomeP.setFont(new Font("Segoe UI", Font.BOLD, 20));
    lbWelcomeP.setBounds(30, 15, 400, 30);
    topHeaderP.add(lbWelcomeP);

    // changes to the id according to the user specificly
    lbId = new JLabel("");
    lbId.setForeground(new Color(200, 200, 200));
    lbId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lbId.setBounds(30, 42, 200, 20);
    topHeaderP.add(lbId);

    // main Content Area for tasks
    mainContetP = new JPanel();
    mainContetP.setBackground(new Color(248, 249, 250));
    mainContetP.setBounds(270, 89, 500, 470);
    mainContetP.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    mainContetP.setLayout(null);
    passengerTasksPanel.add(mainContetP);

    // hint
    lbHintP = new JLabel("Select a task to perform");
    lbHintP.setHorizontalAlignment(SwingConstants.CENTER);
    lbHintP.setForeground(Color.GRAY);
    lbHintP.setBounds(130, 220, 250, 20);
    mainContetP.add(lbHintP);

    // left side bar
    leftSideBarP = new JPanel();
    leftSideBarP.setBounds(0, 70, 250, 530);
    leftSideBarP.setBackground(new Color(4, 44, 37));
    leftSideBarP.setLayout(null);
    passengerTasksPanel.add(leftSideBarP);

    // buy ticket button
    JButton btnBuyTicket = new JButton("Buy Ticket");
    btnBuyTicket.setForeground(Color.WHITE);
    btnBuyTicket.setBackground(new Color(4, 64, 37));
    btnBuyTicket.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnBuyTicket.setBorderPainted(false);
    btnBuyTicket.setBounds(20, 20, 210, 45);
    btnBuyTicket.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lblExceptionMessageP.setText("");
        lblExceptionMessageP1.setText("");
        ticketsNormal.setVisible(false);
        ticketsFirstClass.setVisible(false);
        pnlBuyTicket.setVisible(true);
        lbHintP.setVisible(false);
        pnlDisplayInfo.setVisible(false);
        pnlAddBalance.setVisible(false);

      }
    });
    leftSideBarP.add(btnBuyTicket);

    // display info button
    JButton btnDisplayInfoP = new JButton("Display Info");
    btnDisplayInfoP.setForeground(Color.WHITE);
    btnDisplayInfoP.setBackground(new Color(4, 64, 37));
    btnDisplayInfoP.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnDisplayInfoP.setBorderPainted(false);
    btnDisplayInfoP.setBounds(20, 70, 210, 45);
    btnDisplayInfoP.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setPassengerInfo();
        pnlDisplayInfo.setVisible(true);
        pnlBuyTicket.setVisible(false);
        lbHintP.setVisible(false);
        pnlAddBalance.setVisible(false);

      }
    });
    leftSideBarP.add(btnDisplayInfoP);

    // refund ticket button
    JButton btnRefundTicket = new JButton("Refund Ticket");
    btnRefundTicket.setForeground(Color.WHITE);
    btnRefundTicket.setBackground(new Color(4, 64, 37));
    btnRefundTicket.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnRefundTicket.setBorderPainted(false);
    btnRefundTicket.setBounds(20, 120, 210, 45);
    btnRefundTicket.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // disable all the panels
        pnlBuyTicket.setVisible(false);
        pnlDisplayInfo.setVisible(false);
        lbHintP.setVisible(true);
        pnlAddBalance.setVisible(false);
        // check if the user has a ticket
        if (loggedInPassenger.getTicket() == null) {
          JOptionPane.showMessageDialog(passengerTasksPanel, "You don't have a ticket to refund", "Error",
              JOptionPane.ERROR_MESSAGE);
          return; // Exit the method if there's no ticket to refund
        }

        if (isTicketActive) {
          // if the user activated the ticket, they can't refund it
          JOptionPane.showMessageDialog(passengerTasksPanel,
              "You cannot refund a ticket that has already been activated!",
              "Refund Error",
              JOptionPane.ERROR_MESSAGE);
          return;
        }
        // asking the user to confirm the refund
        int choice = JOptionPane.showConfirmDialog(passengerTasksPanel, "Are you sure you want to refund your ticket?",
            "Confirm Refund", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          loggedInPassenger.refundTicket();
          normalT = false;
          firstT = false;
          isTicketActive = false;
          Mytickets.setText("Activate Ticket");
          setPassengerInfo();
          JOptionPane.showMessageDialog(passengerTasksPanel, "Ticket refunded successfully!");
        }
      }
    });
    leftSideBarP.add(btnRefundTicket);

    // add balance button
    JButton btnAddBalance = new JButton("Add Balance");
    btnAddBalance.setForeground(Color.WHITE);
    btnAddBalance.setBackground(new Color(4, 64, 37));
    btnAddBalance.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnAddBalance.setBorderPainted(false);
    btnAddBalance.setBounds(20, 170, 210, 45);
    btnAddBalance.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lbHintP.setVisible(false);
        pnlBuyTicket.setVisible(false);
        pnlDisplayInfo.setVisible(false);
        lblAddBalanceMessage.setText("");
        pnlAddBalance.setVisible(true);
      }
    });
    leftSideBarP.add(btnAddBalance);

    // logout button
    JButton btnLogoutP = new JButton("Logout");
    btnLogoutP.setBackground(new Color(217, 48, 37));
    btnLogoutP.setForeground(Color.WHITE);
    btnLogoutP.setBorderPainted(false);
    btnLogoutP.setBounds(20, 450, 210, 45);
    btnLogoutP.addActionListener(new ActionListener() {
      // if we click this btn do this
      public void actionPerformed(ActionEvent e) {
        saveProgress("riyadh_metro.data");
        passengerTasksPanel.setVisible(false);
        UI.setVisible(true);
      }
    });
    leftSideBarP.add(btnLogoutP);

    // passenger buttons panels

    // setting buy ticket panel
    pnlBuyTicket = new JPanel();
    pnlBuyTicket.setBounds(0, 0, 500, 470);
    pnlBuyTicket.setBackground(new Color(248, 249, 250));
    pnlBuyTicket.setLayout(null);
    pnlBuyTicket.setVisible(false);
    mainContetP.add(pnlBuyTicket);

    BalanceT = new JLabel("Balance: " + loggedInPassenger.getBalance());
    BalanceT.setForeground(new Color(4, 64, 37));
    BalanceT.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    BalanceT.setBounds(50, 50, 200, 20);
    pnlBuyTicket.add(BalanceT);

    JLabel lblBuyTicketTitle = new JLabel("Ticket Types");
    lblBuyTicketTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblBuyTicketTitle.setForeground(new Color(4, 64, 37));
    lblBuyTicketTitle.setBounds(40, 20, 200, 30);
    pnlBuyTicket.add(lblBuyTicketTitle);

    lblExceptionMessageP = new JLabel("");
    lblExceptionMessageP.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblExceptionMessageP.setBounds(55, 150, 300, 20);
    pnlBuyTicket.add(lblExceptionMessageP);

    // a button to show the normal tickets
    JButton NormalT = new JButton("Normal Ticket");
    NormalT.setBackground(new Color(4, 64, 37));
    NormalT.setForeground(Color.WHITE);
    NormalT.setBounds(50, 80, 200, 35);
    pnlBuyTicket.add(NormalT);
    NormalT.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ticketsFirstClass.setVisible(false);
        ticketsFirstClass.clearSelection();
        ticketsNormal.setVisible(true);
      }
    });

    // a button to show the first class tickets
    JButton FirstClassT = new JButton("First Class Ticket");
    FirstClassT.setBackground(new Color(4, 64, 37));
    FirstClassT.setForeground(Color.WHITE);
    FirstClassT.setBounds(250, 80, 200, 35);
    pnlBuyTicket.add(FirstClassT);
    FirstClassT.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ticketsNormal.setVisible(false);
        ticketsFirstClass.setVisible(true);
        ticketsNormal.clearSelection();
      }
    });
    // a list for both normal and first class tickets
    String ticketsN[] = { "2 hours ticket (4 SR)", "7 days ticket (40 SR)", "30 days ticket (140 SR)" };
    String ticketsF[] = { "2 hours first class ticket (10 SR)", "7 days first class ticket (100 SR)",
        "30 days first class ticket (350 SR)" };
    // setting a list in jlist to display the tickets
    ticketsNormal = new JList(ticketsN);
    ticketsNormal.setBounds(50, 115, 400, 70);
    ticketsNormal.setBackground(new Color(248, 249, 250));
    ticketsNormal.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    ticketsNormal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ticketsNormal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    ticketsNormal.setVisible(false);
    pnlBuyTicket.add(ticketsNormal);

    ticketsFirstClass = new JList(ticketsF);
    ticketsFirstClass.setBounds(50, 115, 400, 70);
    ticketsFirstClass.setBackground(new Color(248, 249, 250));
    ticketsFirstClass.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    ticketsFirstClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ticketsFirstClass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    ticketsFirstClass.setVisible(false);
    pnlBuyTicket.add(ticketsFirstClass);

    JButton btnBuy = new JButton("Buy");
    btnBuy.setBackground(new Color(4, 64, 37));
    btnBuy.setForeground(Color.WHITE);
    btnBuy.setBounds(200, 300, 100, 35);
    btnBuy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lblExceptionMessageP1.setText("");
        lblExceptionMessageP.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblExceptionMessageP.setBounds(55, 190, 300, 20);
        // check if the user selected a ticket in either list
        if (ticketsNormal.getSelectedIndex() != -1) {
          int price = 0;
          // if it did select a ticket from the normal list, get the price of the ticket
          switch (ticketsNormal.getSelectedIndex()) {
            case 0:
              price = 4;
              break;
            case 1:
              price = 40;
              break;
            case 2:
              price = 140;
          }
          // checks if the user has the balance to buy the ticket, if not it shows an
          // error
          switch (loggedInPassenger.buyTicket(price)) {
            case 1:
              lblExceptionMessageP.setForeground(new Color(0, 150, 0));
              lblExceptionMessageP.setText("Ticket purchased successfully!");
              normalT = true;
              firstT = false;
              setPassengerInfo();
              break;
            case 2:
              lblExceptionMessageP.setForeground(Color.RED);
              lblExceptionMessageP.setText("Not enough balance to buy the ticket.");
              break;
            case 3:
              lblExceptionMessageP.setForeground(Color.RED);
              lblExceptionMessageP.setText("You already have a ticket.");
              break;
          }
          // if it did select a ticket from the first class list, get the price of the
          // ticket
        } else if (ticketsFirstClass.getSelectedIndex() != -1) {
          int price = 0;
          switch (ticketsFirstClass.getSelectedIndex()) {
            case 0:
              price = 10;
              break;
            case 1:
              price = 100;
              break;
            case 2:
              price = 350;
          }
          // checks if the user has the balance to buy the ticket, if not it shows an
          // error
          switch (loggedInPassenger.buyTicket(price)) {
            case 1:
              lblExceptionMessageP.setForeground(new Color(0, 150, 0));
              lblExceptionMessageP.setText("Ticket purchased successfully!");
              setPassengerInfo();
              normalT = false;
              firstT = true;
              break;
            case 2:
              lblExceptionMessageP.setForeground(Color.RED);
              lblExceptionMessageP.setText("Not enough balance to buy the ticket.");
              break;
            case 3:
              lblExceptionMessageP.setForeground(Color.RED);
              lblExceptionMessageP.setText("You already have a ticket.");
              break;
          }
        } else {
          lblExceptionMessageP.setForeground(Color.RED);
          lblExceptionMessageP.setText("Error: Please select a ticket type.");
        }

      }
    });
    pnlBuyTicket.add(btnBuy);

    // my tickets panel
    pnlMyTickets = new JPanel();
    pnlMyTickets.setBounds(100, 20, 300, 420);
    pnlMyTickets.setLayout(null);
    pnlMyTickets.setVisible(false);
    mainContetP.add(pnlMyTickets);

    // back button
    btback = new JButton("Back");
    btback.setBounds(100, 340, 100, 35);
    btback.setFocusPainted(false);
    btback.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // back to buy ticket panel
        pnlMyTickets.setBackground(new Color(248, 249, 250));// change the color of the panel
        btback.setBackground(new Color(240, 240, 240));
        btback.setForeground(Color.BLACK);
        pnlMyTickets.setVisible(false);
        pnlBuyTicket.setVisible(true);
      }
    });
    pnlMyTickets.add(btback);

    // ticket info
    lbTicketsName = new JLabel();
    lbTicketsName.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lbTicketsName.setForeground(Color.WHITE);
    lbTicketsName.setBounds(30, 30, 250, 30);
    pnlMyTickets.add(lbTicketsName);

    lbTicketType = new JLabel();
    lbTicketType.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lbTicketType.setForeground(Color.WHITE);
    lbTicketType.setBounds(30, 70, 250, 30);
    pnlMyTickets.add(lbTicketType);

    lbExpDate = new JLabel();
    lbExpDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lbExpDate.setForeground(Color.WHITE);
    lbExpDate.setBounds(30, 110, 250, 30);
    pnlMyTickets.add(lbExpDate);

    // message for exception
    lblExceptionMessageP1 = new JLabel("");
    lblExceptionMessageP1.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblExceptionMessageP1.setBounds(130, 400, 400, 20);
    pnlBuyTicket.add(lblExceptionMessageP1);

    // my tickets button to show the ticket
    Mytickets = new JButton("Activate Ticket");
    Mytickets.setForeground(Color.WHITE);
    Mytickets.setBackground(new Color(4, 64, 37));
    Mytickets.setFont(new Font("Segoe UI", Font.BOLD, 13));
    Mytickets.setBorderPainted(false);
    Mytickets.setBounds(150, 360, 200, 35);
    Mytickets.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (loggedInPassenger.getTicket() == null) {// if the passenger doesn't have a ticket
          lblExceptionMessageP1.setForeground(Color.RED);
          lblExceptionMessageP1.setText("You don't have a ticket to activate.");
          return;
        }

        if (isTicketActive == true) {// if the ticket is already active
          // show the panel and hide the other panels
          pnlMyTickets.setVisible(true);
          pnlBuyTicket.setVisible(false);
          lblExceptionMessageP1.setText("");
          if (firstT == true) {
            pnlMyTickets.setBackground(new Color(223, 193, 109));
            btback.setBackground(Color.WHITE);
            btback.setForeground(new Color(223, 193, 109));
          } else if (normalT == true) {
            pnlMyTickets.setBackground(new Color(0, 80, 160));
            btback.setBackground(Color.WHITE);
            btback.setForeground(new Color(0, 80, 160));
          }
          return;
        }

        int confirm = JOptionPane.showConfirmDialog(MetroGui.this, "Are you sure you want to activate your ticket?", // confirm
            // the
            // ticket
            // activation
            "Confirm ative", JOptionPane.YES_NO_OPTION);// confirm the ticket activation
        if (confirm == JOptionPane.YES_OPTION) {// if the passenger confirmed the ticket activation
          // set the ticket to active
          isTicketActive = true;
          // set the ticket info
          lbTicketsName.setText("Passenger: " + loggedInPassenger.getName());
          lbTicketType.setText("Type: " + loggedInPassenger.getTicket().getType());// first we need to set the ticket
                                                                                   // type
          lbExpDate.setText("Expiration Date: " + loggedInPassenger.getTicket().getcalculateExpiryDate());// then we
                                                                                                          // need to set
                                                                                                          // the ticket
                                                                                                          // expiration
          // date
          Mytickets.setText("Display Ticket");

          try {// add the ticket icon to the panel
            java.io.File imgFile = new java.io.File("src/main/QQR.png");// select main path of the image
            if (!imgFile.exists()) {// another plane if the image is not found in the path
              imgFile = new java.io.File("QQR.png");
            }
            if (imgFile.exists()) {// final check if the image is found in the path
              // read the image and resize it
              ImageIcon myImage = new ImageIcon(imgFile.getAbsolutePath());
              java.awt.Image img = javax.imageio.ImageIO.read(imgFile);
              java.awt.Image newImg = img.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);// change the size of
                                                                                                   // the image
              JLabel ticketIcon = new JLabel(new ImageIcon(newImg));// add the image to the label
              ticketIcon.setBounds(70, 170, 160, 160);
              pnlMyTickets.add(ticketIcon);

            } else {// if the image is not found in the path
              System.out.println("Image file not found.");
            }
          } catch (Exception ex) {// if there is an error in the image path
            System.out.println("Error loading image: " + ex.getMessage());
          }

          // show the panel and hide the other panels
          pnlBuyTicket.setVisible(false);
          lbHintP.setVisible(false);
          pnlMyTickets.setVisible(true);

        }
        if (firstT == true) {// if the ticket is first class to set the color of the panel ticket
          pnlMyTickets.setBackground(new Color(223, 193, 109));
          btback.setBackground(Color.WHITE);
          btback.setForeground(new Color(223, 193, 109));
        } else if (normalT == true) {// if the ticket is normal to set the color of the panel ticket
          pnlMyTickets.setBackground(new Color(150, 150, 150));
          btback.setBackground(Color.WHITE);
          btback.setForeground(new Color(150, 150, 150));

        }

      }

    });
    pnlBuyTicket.add(Mytickets);

    // setting the Display info panel
    pnlDisplayInfo = new JPanel();
    pnlDisplayInfo.setBounds(0, 0, 500, 470);
    pnlDisplayInfo.setBackground(new Color(248, 249, 250));
    pnlDisplayInfo.setLayout(null);
    pnlDisplayInfo.setVisible(false);
    mainContetP.add(pnlDisplayInfo);

    JLabel lblDisplayInfoTitle = new JLabel("Passenger Information");
    lblDisplayInfoTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblDisplayInfoTitle.setForeground(new Color(4, 64, 37));
    lblDisplayInfoTitle.setBounds(50, 30, 250, 30);
    pnlDisplayInfo.add(lblDisplayInfoTitle);

    infoBorader = new JPanel();
    infoBorader.setBounds(45, 80, 450, 100);
    infoBorader.setLayout(null);
    infoBorader.setBackground(new Color(248, 249, 250));
    infoBorader.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    pnlDisplayInfo.add(infoBorader);

    Pinfo = new JTextArea("");
    Pinfo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
    Pinfo.setLayout(null);
    Pinfo.setBounds(15, 10, 280, 180);
    Pinfo.setEditable(false);
    Pinfo.setOpaque(false);
    infoBorader.add(Pinfo);

    ticketInfo = new JPanel();
    ticketInfo.setBounds(45, 195, 450, 75);
    ticketInfo.setLayout(null);
    ticketInfo.setBackground(new Color(248, 249, 250));
    ticketInfo.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 2));
    pnlDisplayInfo.add(ticketInfo);

    Tinfo = new JTextArea("testing");
    Tinfo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
    Tinfo.setLayout(null);
    Tinfo.setBounds(15, 10, 450, 180);
    Tinfo.setEditable(false);
    Tinfo.setOpaque(false);
    ticketInfo.add(Tinfo);

    // delete account button
    JButton deleteAccount = new JButton("Delete Account");
    deleteAccount.setBackground(new Color(217, 48, 37));
    deleteAccount.setForeground(Color.WHITE);
    deleteAccount.setBounds(150, 350, 200, 35);
    deleteAccount.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // make sure the user is sure of deleting the account
        int choice = JOptionPane.showConfirmDialog(pnlDisplayInfo, "Are you sure of deleting yout account?",
            "Confirm Action", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(pnlDisplayInfo, "Account deleted successfully!");
          riyadhMetro.removePassenger(loggedInPassenger.getId()); // remove the passenger from the system
          loggedInPassenger = null; // set the logged in passenger to null
          passengerTasksPanel.setVisible(false);
          UI.setVisible(true);
          saveProgress("riyadh_metro.data"); // save the progress
        }
      }
    });
    pnlDisplayInfo.add(deleteAccount);

    // setting the Add Balance panel
    pnlAddBalance = new JPanel();
    pnlAddBalance.setBounds(0, 0, 500, 470);
    pnlAddBalance.setBackground(new Color(248, 249, 250));
    pnlAddBalance.setLayout(null);
    pnlAddBalance.setVisible(false);
    mainContetP.add(pnlAddBalance);

    JLabel lblAddBalanceTitle = new JLabel("Add Balance");
    lblAddBalanceTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
    lblAddBalanceTitle.setForeground(new Color(4, 64, 37));
    lblAddBalanceTitle.setBounds(50, 30, 200, 30);
    pnlAddBalance.add(lblAddBalanceTitle);

    JLabel lblAmount = new JLabel("Enter Amount:");
    lblAmount.setBounds(50, 90, 120, 25);
    pnlAddBalance.add(lblAmount);

    txtAmount = new JTextField();
    txtAmount.setBounds(160, 90, 260, 25);
    pnlAddBalance.add(txtAmount);

    lblAddBalanceMessage = new JLabel("");
    lblAddBalanceMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblAddBalanceMessage.setBounds(160, 125, 300, 20);
    pnlAddBalance.add(lblAddBalanceMessage);

    JButton btnConfirmAddBalance = new JButton("Add Balance");
    btnConfirmAddBalance.setBackground(new Color(4, 64, 37));
    btnConfirmAddBalance.setForeground(Color.WHITE);
    btnConfirmAddBalance.setBounds(160, 160, 150, 35);
    btnConfirmAddBalance.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String amountText = txtAmount.getText();
        // make sure the user entered a valid amount
        if (amountText.isEmpty()) {
          lblAddBalanceMessage.setForeground(Color.RED);
          lblAddBalanceMessage.setText("Error: Please enter an amount.");
          return;
        }
        try {
          double amount = Double.parseDouble(amountText);
          if (amount <= 0) {
            throw new NumberFormatException();
          }
          loggedInPassenger.addBalance(amount);
          lblAddBalanceMessage.setForeground(new Color(0, 150, 0));
          lblAddBalanceMessage.setText("Success! Added " + amount + " SR to your balance.");
          txtAmount.setText(""); // Clear the text field after successful addition
          setPassengerInfo();
        } catch (NumberFormatException ex) {
          lblAddBalanceMessage.setForeground(Color.RED);
          lblAddBalanceMessage.setText("Error: Please enter a valid value.");
          return;
        }
      }
    });
    pnlAddBalance.add(btnConfirmAddBalance);

  }

  // method to update the passenger info
  public void setPassengerInfo() {
    if (loggedInPassenger != null) {
      lbWelcomeP.setText("Welcome, Passenger " + loggedInPassenger.getName());
      lbId.setText("ID: " + loggedInPassenger.getId());
      BalanceT.setText("Balance: " + loggedInPassenger.getBalance() + " SR");

      Pinfo.setText("Name: " + loggedInPassenger.getName() +
          "\nID: " + loggedInPassenger.getId() +
          "\nBalance: " + loggedInPassenger.getBalance() + " SR");

      if (loggedInPassenger.getTicket() != null) {
        Tinfo.setText("Ticket Info: " + loggedInPassenger.getTicket().getType());
        typeTicket = loggedInPassenger.getTicket().getType();
      } else
        Tinfo.setText("Ticket Info: No ticket purchased");
    }
  }

  // files
  public void saveProgress(String fileName) {
    File f;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    try {
      f = new File(fileName);
      fos = new FileOutputStream(f);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(riyadhMetro);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Error saving your changes: " + e.getMessage());
    } finally {
      try {
        if (oos != null)
          oos.close();
        if (fos != null)
          fos.close();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving your changes:: " + e.getMessage());
      }
    }

  }

  public void loadProgress(String fileName) {
    File f = new File(fileName);
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    if (!f.exists()) {
      riyadhMetro = new MetroNetwork("Riyadh Metro");
      return;
    }
    try {
      fis = new FileInputStream(f);
      ois = new ObjectInputStream(fis);
      riyadhMetro = (MetroNetwork) ois.readObject();
      employeeListModel.clear();
      for (int i = 0; i < riyadhMetro.getNumOfEmployees(); i++) {
        employeeListModel.addElement(riyadhMetro.getEmployee(i));
      }
    } catch (ClassNotFoundException e) {
      riyadhMetro = new MetroNetwork("Riyadh Metro");
    } catch (IOException e) {
      riyadhMetro = new MetroNetwork("Riyadh Metro");
    } finally {
      try {
        if (ois != null)
          ois.close();
        if (fis != null)
          fis.close();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "An Error occured!: " + e.getMessage());
      }
    }
  }
  // method to hide all panels from the manager main menu

  public void hideAllManagerPanels() {
    lbHint.setVisible(false);
    lbHint2.setVisible(false);
    selectStationPanel.setVisible(false);
    selectStationPanel2.setVisible(false);
    pnlAddStationTask.setVisible(false);
    pnlRemoveStationTask.setVisible(false);
    pnlEmployeeMain.setVisible(false);
    pnlAddEmployeeTask.setVisible(false);
    pnlMaintenanceMain.setVisible(false);
    pnlAddVehicleTask.setVisible(false);
    pnlRemoveVehicleTask.setVisible(false);
    pnlAssignEmployee.setVisible(false);
  }

}
