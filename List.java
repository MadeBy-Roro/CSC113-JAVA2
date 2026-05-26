import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DisplayNetworkFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // reference to the metro network
    private MetroNetwork riyadhMetro;

    private JPanel contentPane;
    private JLabel lblTotalStations;
    private JLabel lblTotalProfit;
    private JList<String> stationList;
    private DefaultListModel<String> stationListModel;
    private JTextArea txtFullInfo;

    public DisplayNetworkFrame(MetroNetwork network) {// constructor
        this.riyadhMetro = network;
        // frame settings
        setTitle("Network Information - Riyadh Metro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(150, 150, 820, 600);
        // main panel
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 249, 250));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        // top header
        JPanel topHeader = new JPanel();
        topHeader.setBackground(new Color(4,64,37));
        topHeader.setBounds(0, 0, 820, 70);
        topHeader.setLayout(null);
        contentPane.add(topHeader);

        JLabel lbTitle = new JLabel("Display Network Info");
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbTitle.setBounds(30, 15, 400, 30);
        topHeader.add(lbTitle);

        JLabel lbSubtitle = new JLabel("Riyadh Metro System");
        lbSubtitle.setForeground(new Color(200, 200, 200));
        lbSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSubtitle.setBounds(30, 42, 300, 20);
        topHeader.add(lbSubtitle);

        JButton btnClose = new JButton("Close");
        btnClose.setBackground(new Color(217, 48, 37));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.setBounds(700, 20, 90, 32);
        btnClose.addActionListener(new ActionListener() {// when close button is clicked, the frame will be disposed
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        topHeader.add(btnClose);

        JPanel pnlSummary = new JPanel();
        pnlSummary.setBackground(new Color(240, 242, 245));
        pnlSummary.setBounds(0, 70, 820, 90);
        pnlSummary.setLayout(null);
        contentPane.add(pnlSummary);
        // used buildCard method to create the cards for the summary
        JPanel cardStations = buildCard("Total Stations", new Color(4,64,37));
        cardStations.setBounds(15, 15, 370, 60);
        pnlSummary.add(cardStations);
        lblTotalStations = (JLabel) cardStations.getComponent(1);// get the label from the card, index 1 is the label
                                                                 // since index 0 is the title

        JPanel cardProfit = buildCard("Total Profit", new Color(140, 80, 0));
        cardProfit.setBounds(435, 15, 370, 60);
        pnlSummary.add(cardProfit);
        lblTotalProfit = (JLabel) cardProfit.getComponent(1);

        JLabel lblStationListTitle = new JLabel("Stations");
        lblStationListTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblStationListTitle.setForeground(new Color(4,64,37));
        lblStationListTitle.setBounds(20, 175, 200, 25);
        contentPane.add(lblStationListTitle);

        stationListModel = new DefaultListModel<>();// create a default list model for the station list
        stationList = new JList<>(stationListModel);
        stationList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// only one station can be selected at a time
        stationList.setBackground(Color.WHITE);
        stationList.setFixedCellHeight(30);
        // scroll pane for the station list
        JScrollPane listScroll = new JScrollPane(stationList);
        listScroll.setBounds(20, 205, 220, 315);
        listScroll.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 1));
        contentPane.add(listScroll);

        JLabel lblDetailTitle = new JLabel("Network Details");
        lblDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblDetailTitle.setForeground(new Color(4,64,37));
        lblDetailTitle.setBounds(260, 175, 200, 25);
        contentPane.add(lblDetailTitle);

        txtFullInfo = new JTextArea("Select a station from the list, or click \"Show Full Report\" below.");
        txtFullInfo.setFont(new Font("Courier New", Font.PLAIN, 13));
        txtFullInfo.setEditable(false);
        txtFullInfo.setLineWrap(false);
        txtFullInfo.setBackground(Color.WHITE);
        txtFullInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        // scroll pane for the text area
        JScrollPane detailScroll = new JScrollPane(txtFullInfo);
        detailScroll.setBounds(260, 205, 540, 315);
        detailScroll.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170), 1));
        contentPane.add(detailScroll);

        JButton btnFullReport = new JButton("Show Full Report");
        btnFullReport.setBackground(new Color(4,64,37));
        btnFullReport.setForeground(Color.WHITE);
        btnFullReport.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnFullReport.setBorderPainted(false);
        btnFullReport.setFocusPainted(false);
        btnFullReport.setBounds(20, 535, 175, 35);
        btnFullReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// when full report button is clicked, the full report will be
                                                        // displayed in the text area
                stationList.clearSelection();
                txtFullInfo.setText(riyadhMetro.displayNetworkInfo());
                txtFullInfo.setCaretPosition(0);// scroll to the top of the text area
            }
        });
        contentPane.add(btnFullReport);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(150, 150, 150));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBounds(205, 535, 100, 35);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        contentPane.add(btnRefresh);// refresh button to refresh the data so the user doesnt have to close and
                                    // reopen the frame

        stationList.addListSelectionListener(new ListSelectionListener() {// when a station is selected, the station
                                                                          // info will be displayed in the text area
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = stationList.getSelectedValue();
                    if (selectedName != null) {
                        showStationInfo(selectedName);
                    }
                }
            }
        });

        loadData();
    }

    /*
     * Builds a styled card panel to display summary, retutns a formatted JPanel
     * containing the title and an empty value label.
     */
    private JPanel buildCard(String title, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, accentColor));

        JLabel lblCardTitle = new JLabel(title);
        lblCardTitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblCardTitle.setForeground(Color.GRAY);
        lblCardTitle.setBounds(10, 8, 340, 18);
        card.add(lblCardTitle); // index 0 is always the title

        JLabel lblCardValue = new JLabel("—");
        lblCardValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCardValue.setForeground(accentColor);
        lblCardValue.setBounds(10, 28, 340, 28);
        card.add(lblCardValue); // index 1

        return card;
    }

    private void loadData() {// load the data from the metro network
        lblTotalStations.setText(String.valueOf(riyadhMetro.getNumOfStations()));
        lblTotalProfit.setText("$" + riyadhMetro.getProfit());

        stationListModel.clear();
        for (int i = 0; i < riyadhMetro.getNumOfStations(); i++) {
            try {
                Station s = riyadhMetro.getStation(i);
                if (s != null) {
                    stationListModel.addElement(s.getStationName());
                }
            } catch (Exception e) {// skips if station couldnt be found
            }
        }

        txtFullInfo.setText("Select a station from the list, or click \"Show Full Report\" below.");
    }

    private void showStationInfo(String stationName) {// show the station info in the text area.simply displays the
                                                      // station info string.
        try {
            Station s = riyadhMetro.searchStation(stationName);

            String info = "";
            info += " -------------------------------------------------\n";
            info += " Station Name: " + s.getStationName() + "\n";
            info += " Number of Vehicles: " + s.getCurrentUnits() + "\n";
            info += " Number of Employees: " + s.getNumOfEmployees() + "\n";
            info += " Number of Passengers: " + s.calNumOfPassengers() + "\n";
            info += " -------------------------------------------------\n";

            for (int j = 0; j < s.getCurrentUnits(); j++) {
                TransportUnit unit = s.getTransportUnits().get(j);
                info += " -------------------------------------------------\n";
                info += "                    Vehicle: " + (j + 1) + "\n";
                info += " -------------------------------------------------\n";
                info += " Vehicle ID: " + unit.getID() + "\n";
                info += " Number of Passengers: " + unit.getCurrentPassengers() + "\n";
                info += " -------------------------------------------------\n";

                if (unit.getCurrentPassengers() == 0) {
                    info += "| There are no passengers in this vehicle.        |\n";
                    info += " -------------------------------------------------\n";
                } else {
                    info += " -------------------------------------------------------------------------------------\n";
                    info += "|                                      Passengers                                     |\n";
                    info += " -------------------------------------------------------------------------------------\n";
                    info += unit.displayPassengers();
                    info += " -------------------------------------------------------------------------------------\n";
                }
            }

            txtFullInfo.setText(info);
            txtFullInfo.setCaretPosition(0);

        } catch (StationNotFoundException ex) {
            txtFullInfo.setText("Error: Station '" + stationName + "' not found.");
        }
    }
}