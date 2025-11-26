package za.ac.tut.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import za.ac.tut.cand.PracticalCandidate;
import za.ac.tut.utils.PracticalRoster;

public class UI_B1 extends JFrame {
    //panels
    private JPanel mainPl;
    private JPanel leanerTestPl;
    private JPanel leanerNamePl;
    private JPanel languagePl;
    private JPanel durationPl;
    private JPanel enviromentalPl;
    private JPanel enviromentTypesPl;
    private JPanel actionPl;
    private JPanel notesPl;
    private JPanel textAreaPl;
    private JPanel ideRadioPl;
    private JPanel checkBoxPl;
    
    //labels
    private JLabel learnerNameLb;
    private JLabel languegeLb;
    private JLabel durationLb;
    private JLabel notesLb;
    
    //JtextField
    private JTextField learnerNameTxtFld;
    
    //JCombobox
    private JComboBox languegeCombBx;
    private JComboBox durationCombBx;
    
    //Radio buttons
    private JRadioButton intellijRdB;
    private JRadioButton eclipseRdB;
    private JRadioButton vscodeRdB;
    
    // Group of Radio buttons
    private ButtonGroup bntGrp;
    
    // checkbox
    private JCheckBox darkModeCheckBx;
    private JCheckBox disableInternetCheckBx;
    
    // Buttons
    private JButton beginTestbtn;
    private JButton saveconfigbtn;
    private JButton cancelbtn;
    private JButton resetbtn;
    private JButton verifybtn;
    
    //textArea
    private JTextArea detailsArea;
    
    // Jscroll
    private JScrollPane detailsScroll;
    
    // PracticalRoster for GUI integration
    private PracticalRoster roster;
    
    public UI_B1(){
        setTitle("Exam B: Practical Coding test Configurator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(500, 600);
        setForeground(Color.red);
        setResizable(true);
        
        // Initialize roster with error handling
        initializeRoster();
        
        initializeComponents();
        layoutComponents();
        attachListeners();
        setVisible(true);     
    }
    
    private void initializeRoster() {
        try {
            File rosterFile = new File("roster.txt");
            // Create the file if it doesn't exist
            if (!rosterFile.exists()) {
                rosterFile.createNewFile();
                System.out.println("Created new roster.txt file");
            }
            roster = new PracticalRoster(rosterFile);
        } catch (Exception e) {
            System.err.println("Error initializing roster: " + e.getMessage());
            // Create a dummy roster to prevent NullPointerException
            roster = createDummyRoster();
        }
    }
    
    private PracticalRoster createDummyRoster() {
        // Create a temporary file for the roster
        try {
            File tempFile = File.createTempFile("roster", ".txt");
            tempFile.deleteOnExit();
            return new PracticalRoster(tempFile);
        } catch (Exception e) {
            System.err.println("Failed to create dummy roster: " + e.getMessage());
            return null;
        }
    }
    
    private void initializeComponents() {
        // Learner Name Panel
        leanerNamePl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        learnerNameLb = new JLabel("Learner Name:      ");
        learnerNameTxtFld = new JTextField(20);
        leanerNamePl.add(learnerNameLb);
        leanerNamePl.add(learnerNameTxtFld);
        
        // Language Panel
        languagePl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languegeLb = new JLabel("Language:      ");
        languegeCombBx = new JComboBox();
        languegeCombBx.addItem("Java");
        languegeCombBx.addItem("Python");
        languegeCombBx.addItem("C++");
        languegeCombBx.setSelectedItem("Java"); // Set default as per requirement
        languagePl.add(languegeLb);
        languagePl.add(languegeCombBx);
        
        // Duration Panel
        durationPl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        durationLb = new JLabel("Duration:      ");
        durationCombBx = new JComboBox();
        durationCombBx.addItem("60 min");
        durationCombBx.addItem("90 min"); 
        durationCombBx.addItem("120 min");
        durationCombBx.addItem("80 min"); // As per requirement
        durationCombBx.setSelectedItem("80 min"); // Set default as per requirement
        durationPl.add(durationLb);
        durationPl.add(durationCombBx);  
        
        // IDE Radio buttons
        intellijRdB = new JRadioButton("IntelliJ IDEA");
        eclipseRdB = new JRadioButton("Eclipse");
        vscodeRdB = new JRadioButton("VS Code");
        
        // Group of Radio buttons
        bntGrp = new ButtonGroup();
        bntGrp.add(intellijRdB);
        bntGrp.add(eclipseRdB);
        bntGrp.add(vscodeRdB);
        eclipseRdB.setSelected(true); // Default selection
        
        // IDE Radio Panel
        ideRadioPl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ideRadioPl.add(new JLabel("IDE:"));
        ideRadioPl.add(intellijRdB);
        ideRadioPl.add(eclipseRdB);
        ideRadioPl.add(vscodeRdB);
        
        // Checkboxes
        darkModeCheckBx = new JCheckBox("Dark Mode");
        disableInternetCheckBx = new JCheckBox("Disable Internet");
        
        // Checkbox Panel
        checkBoxPl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkBoxPl.add(darkModeCheckBx);
        checkBoxPl.add(disableInternetCheckBx);
        
        // Buttons
        beginTestbtn = new JButton("Begin Test");
        saveconfigbtn = new JButton("Save Config");
        cancelbtn = new JButton("Cancel");
        resetbtn = new JButton("Reset"); // 1.1 Reset button
        verifybtn = new JButton("Verify"); // 1.5 Verify button
        
        // Action Panel
        actionPl = new JPanel(new FlowLayout());
        actionPl.add(beginTestbtn);
        actionPl.add(saveconfigbtn);
        actionPl.add(cancelbtn);
        actionPl.add(resetbtn); // Added Reset button
        actionPl.add(verifybtn); // Added Verify button
        
        // Notes Panel
        notesPl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notesLb = new JLabel("Notes will appear here");
        notesPl.add(notesLb);
        
        // Details Text Area (1.3)
        detailsArea = new JTextArea(10, 40);
        detailsArea.setEditable(false);
        detailsScroll = new JScrollPane(detailsArea);
        textAreaPl = new JPanel(new BorderLayout());
        textAreaPl.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Details"));
        textAreaPl.add(detailsScroll, BorderLayout.CENTER);
    }
    
    private void layoutComponents() {
        // Learner & Test Panel  
        leanerTestPl = new JPanel(new GridLayout(3, 1));
        leanerTestPl.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Learner & Test"));
        leanerTestPl.add(leanerNamePl);
        leanerTestPl.add(languagePl);
        leanerTestPl.add(durationPl);
        
        // Environment Panel
        enviromentalPl = new JPanel(new GridLayout(2, 1));
        enviromentalPl.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Environment"));
        enviromentalPl.add(ideRadioPl);
        enviromentalPl.add(checkBoxPl);
        
        // Action Panel with border
        JPanel actionPanelWithBorder = new JPanel(new BorderLayout());
        actionPanelWithBorder.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Actions"));
        actionPanelWithBorder.add(actionPl, BorderLayout.CENTER);
        
        // Main Panel layout
        mainPl = new JPanel(new BorderLayout(10, 10));
        mainPl.add(leanerTestPl, BorderLayout.NORTH);
        mainPl.add(enviromentalPl, BorderLayout.CENTER);
        mainPl.add(actionPanelWithBorder, BorderLayout.SOUTH);
        
        // Create a container for notes and details
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(notesPl, BorderLayout.NORTH);
        bottomPanel.add(textAreaPl, BorderLayout.CENTER);
        
        // Final layout
        setLayout(new BorderLayout(10, 10));
        add(mainPl, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
    }
    
    private void attachListeners() {
        // 1.1 Reset button listener
        resetbtn.addActionListener(e -> {
            learnerNameTxtFld.setText("");
        });
        
        // 1.2 Cancel button listener - clean termination
        cancelbtn.addActionListener(e -> {
            System.exit(0);
        });
        
        // 1.4 Begin Test button listener
        beginTestbtn.addActionListener(e -> {
            String name = learnerNameTxtFld.getText().trim();
            String duration = (String) durationCombBx.getSelectedItem();
            String language = (String) languegeCombBx.getSelectedItem();
            String ide = getSelectedIDE();
            String internetStatus = disableInternetCheckBx.isSelected() ? "Disabled" : "Enabled";
            
            // Append to details area as per 1.4
            detailsArea.append(name + " has started a practical\n");
            detailsArea.append("Duration: " + duration + "\n");
            detailsArea.append("Language: " + language + "\n");
            detailsArea.append("IDE: " + ide + "\n");
            detailsArea.append("Internet: " + internetStatus + "\n");
            
            // 1.8 GUI integration for Begin Test - with null check
            if (roster != null) {
                PracticalCandidate candidate = new PracticalCandidate(name, language, duration);
                boolean onRoster = roster.registered(candidate);
                notesLb.setText(onRoster ? "On roster" : "Not on roster");
                
                // Append session report
                File reportFile = new File("practical_report.txt");
                roster.appendSessionReport(reportFile, candidate, ide, false, onRoster);
            } else {
                notesLb.setText("Roster not available");
            }
        });
        
        // 1.5 Verify button listener
        verifybtn.addActionListener(e -> {
            String nameText = learnerNameTxtFld.getText();
            String duration = (String) durationCombBx.getSelectedItem();
            
            boolean nameValid = nameText.length() >= 3 && nameText.length() <= 30;
            boolean durationValid = duration.equals("60 min") || 
                                  duration.equals("90 min") || 
                                  duration.equals("120 min");
            
            if (nameValid && durationValid) {
                detailsArea.append("Verified\n");
            } else {
                detailsArea.append("Not Verified\n");
            }
        });
        
        // 1.8 Save Config button listener
        saveconfigbtn.addActionListener(e -> {
            String name = learnerNameTxtFld.getText().trim();
            String duration = (String) durationCombBx.getSelectedItem();
            String language = (String) languegeCombBx.getSelectedItem();
            String ide = getSelectedIDE();
            
            PracticalCandidate candidate = new PracticalCandidate(name, language, duration);
            
            // Append session report - with null check
            if (roster != null) {
                File reportFile = new File("practical_report.txt");
                roster.appendSessionReport(reportFile, candidate, ide, false, false);
                notesLb.setText("Configuration saved.");
            } else {
                notesLb.setText("Roster not available - config not saved");
            }
        });
    }
    
    private String getSelectedIDE() {
        if (intellijRdB.isSelected()) return "IntelliJ IDEA";
        if (eclipseRdB.isSelected()) return "Eclipse";
        if (vscodeRdB.isSelected()) return "VS Code";
        return "Eclipse"; // default
    }
    

}

/*Reset Button (1.1): Clears the learner name field

Exit Behavior (1.2): Cancel button cleanly terminates the application

Details Area (1.3): Non-editable JTextArea wrapped in JScrollPane

Begin Test Logging (1.4): Appends test details to detailsArea

Verify Button (1.5): Validates name length and duration, appends verification result

PracticalCandidate Model (1.6): Simple model with constructor and getters

PracticalRoster Utility (1.7): File loading, membership checking, and report appending

GUI Integration (1.8): Integrated with Begin Test and Save Config buttons*/