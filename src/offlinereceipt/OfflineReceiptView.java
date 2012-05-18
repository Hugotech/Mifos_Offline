package offlinereceipt;//GEN-FIRST:event_printActionPerformed
//GEN-LAST:event_printActionPerformed





import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import offlinereceipt.RestClientMain.Chooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.LocalDate;

// Variables declaration - do not modify
public class OfflineReceiptView extends FrameView {

    static java.io.File f;
    private int rowForcell;
    private String loanamt;
    private String savingamt;
    private String fee;
    private String rid;
    private String exid;
    private double loanSum;
    private double savingSum;
    private double feeSum;
    private double  total_of_three;
    

    /*public List<OfficeDto> getOfficeDetails() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "mifos";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "mifos";
        String name = null;
        List<OfficeDto> list = null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement statement = conn.createStatement();
            String sql = "Select office_id,display_name from office where office_level_id=4";
            ResultSet resultSet = statement.executeQuery(sql);

            list = new ArrayList<OfficeDto>();
            while (resultSet.next()) {
                OfficeDto dto = new OfficeDto();
                dto.setGlobalNo(resultSet.getInt(1));
                dto.setDisplayName(resultSet.getString(2));
                list.add(dto);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }*/
    private String s;
    private File file;

    
    public class PaintCover implements Printable {

        int printTotal = (int) (new Double(printLoanAmount) + new Double(printSavingAmount) + new Double(printFeesAmount));

        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(),
                    pageFormat.getImageableY());
            Font fnt = new Font("gautami", Font.PLAIN, 10);

            g.setFont(fnt);

            g.drawString("రుద్రమదేవి మహిళా మ్యాక్స్", 25, 8);
            g.drawString("తేదీ                  : " + printDate, 0, 35);
            g.drawString("రశీదు సంఖ్య       : " + printReciptNo, 0, 50);
            g.drawString("కంఫ్యూటర్ ఐడి    : " + printcustomerId, 0, 65);
            g.drawString("బ్రాంచి పేరు         : " + printGroupName, 0, 80);
            g.drawString("సభ్యురాలి పేరు     : " + printMemberName, 0, 95);
            g.drawString("అప్పు&వడ్డీ        : " + printLoanAmount, 0, 125);
            g.drawString("పొదుపు            : " + printSavingAmount, 0, 110);
            g.drawString("ఫీజు                 : " + printFeesAmount, 0, 140);
            g.drawString(" మొత్తము         : " + printTotal, 0, 160);
            g.drawString("సూపర్ వైజర్      : " + printLoanOfficer, 0, 190);
            return Printable.PAGE_EXISTS;
        }
    }
    
   /* public class PrintFile implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
           
                //throw new UnsupportedOperationException("Not supported yet.");
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                Font fnt = new Font("gautami", Font.PLAIN, 10);
                Color c=new Color(255);

                graphics.setFont(fnt);
                graphics.setColor(c);
                graphics.drawString("రుద్రమదేవి మహిళా మ్యాక్స్", 25, 8);
                graphics.drawString("ExternalId     LA       SA        Fee", 1, 26);
                graphics.drawString("----------------------------------------",0,37);
                
                
                //----
                HSSFCell cell1, cell2, cell3, cell4,cell5;
                exid = externalIdText.getText();
                FileInputStream fis = null;
                int num=45;
                try{
                    fis = new FileInputStream(f.toString());
                    POIFSFileSystem poifs = new POIFSFileSystem(fis);
                    HSSFWorkbook myWorkBook = new HSSFWorkbook(poifs);
                    HSSFSheet mySheet = myWorkBook.getSheetAt(0);
                    for (int i = 1; i <= mySheet.getPhysicalNumberOfRows(); i++) {
                HSSFRow row = mySheet.getRow(i);

                cell1 = row.getCell(6);
                cell2 = row.getCell(7);
                cell3 = row.getCell(8);
                cell4 = row.getCell(10);
                cell5=row.getCell(0);
                
                String cellval5 = cell5.getStringCellValue();
                double cellval1 = cell1.getNumericCellValue();
                double cellval2 = cell2.getNumericCellValue();
                double cellval3 = cell3.getNumericCellValue();
                String cellval4 = cell4.getStringCellValue();
                
                if (cellval1 > 0 || cellval2 > 0 || cellval3 > 0) {
                    System.out.println("Here iss.."+cellval1 + " " + cellval2 + " " + cellval3 );
                   
                    graphics.drawString(cellval5+"    "+Double.toString(cell1.getNumericCellValue()).replace(".0","")+"       "+Double.toString(cell2.getNumericCellValue()).replace(".0","")+"        "+Double.toString(cell3.getNumericCellValue()).replace(".0","") ,0,num);
                    
                    num=num+15;
                }
                
                //graphics.drawString("", 1, 30);
               }
                
             }catch(Exception e){e.printStackTrace();}
                
             graphics.drawString("Total:         "+Double.toString(loanSum).replace(".0","") +"      "+Double.toString(savingSum).replace(".0","")+"      "+Double.toString(feeSum).replace(".0",""), 1, num+15);
             graphics.drawString("*LA=LoanAmmount  *SA=SavingAmmount" ,5, num+30);
        return Printable.PAGE_EXISTS;
    }
    }*/
    
    public OfflineReceiptView(SingleFrameApplication app) {
        super(app);
        initComponents();
        externalId.setFont(fontTeluguGautami10);
        date.setFont(fontTeluguGautami10);
        groupName.setFont(fontTeluguGautami10);
        memberName.setFont(fontTeluguGautami10);
        loanAmount.setFont(fontTeluguGautami10);
        loanIntrest.setFont(fontTeluguGautami10);
        loanPrinciple.setFont(fontTeluguGautami10);
        savingAmount.setFont(fontTeluguGautami10);
        fees.setFont(fontTeluguGautami10);
        save.setFont(fontTeluguGautami10);
        save.setVisible(true);
        total.setFont(fontTeluguGautami10);
        selectBranch.setFont(fontTeluguGautami10);
        cancel.setFont(fontTeluguGautami10);
        accountStatus.setFont(fontTeluguGautami10);
        totalLoan.setFont(fontTeluguGautami10);
        totalInterest.setFont(fontTeluguGautami10);
        totalSavings.setFont(fontTeluguGautami10);
        pastDuePrincipal.setFont(fontTeluguGautami10);
        pastDueInterest.setFont(fontTeluguGautami10);
        currentDemandPrincipal.setFont(fontTeluguGautami10);
        currentDemandInterest.setFont(fontTeluguGautami10);
        mainTotal.setFont(fontTeluguGautami10);
        summary.setFont(fontTeluguGautami10);
        DateofDebt.setFont(fontTeluguGautami10);
        NoOfInst.setFont(fontTeluguGautami10);
        OSPrincipal.setFont(fontTeluguGautami10);
        OSInterest.setFont(fontTeluguGautami10);
        print.setFont(fontTeluguGautami10);
        sum_of_the_three.setFont(fontTeluguGautami10);
        ArrayList<String> al = new ArrayList<String>();
        Vector<String> vector = new Vector<String>();
        try {


            // create BufferedReader to read csv file
            BufferedReader br = new BufferedReader(new FileReader("Details.csv"));
            String strLine = "";
            StringTokenizer st = null;
            int lineNumber = 0, tokenNumber = 0;

            // read comma separated file line by line
            while ((strLine = br.readLine()) != null) {
                lineNumber++;


                // break comma separated line using ","
                st = new StringTokenizer(strLine, ",");

                while (st.hasMoreTokens()) {
                    //System.out.println(st.nextToken());
                    al.add(st.nextToken());
                }

            }

        } catch (Exception e) {
            System.out.println("Exception while reading csv file: " + e);
        }
        for (int i = 0; i < al.size(); i = i + 3) {
            System.out.println(al.get(i));
            vector.add(al.get(i));
        }
        System.out.println(al);

        Collections.sort(vector);
        comboSelect.setModel(new javax.swing.DefaultComboBoxModel(vector));

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = OfflineReceiptApp.getApplication().getMainFrame();
            aboutBox = new OfflineReceiptAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        OfflineReceiptApp.getApplication().show(aboutBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        externalId = new javax.swing.JLabel();
        externalIdText = new javax.swing.JTextField();
        groupName = new javax.swing.JLabel();
        memberName = new javax.swing.JLabel();
        loanPrinciple = new javax.swing.JLabel();
        loanIntrest = new javax.swing.JLabel();
        loanAmount = new javax.swing.JLabel();
        loanAmountText = new javax.swing.JTextField();
        savingAmount = new javax.swing.JLabel();
        savingAmountText = new javax.swing.JTextField();
        fees = new javax.swing.JLabel();
        feesText = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        groupNameValue = new javax.swing.JLabel();
        memberNameValue = new javax.swing.JLabel();
        loanPrincipleValue = new javax.swing.JLabel();
        loanInterestValue = new javax.swing.JLabel();
        dateValue = new javax.swing.JLabel();
        totalValue = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        comboSelect = new javax.swing.JComboBox();
        selectBranch = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        accountStatus = new javax.swing.JLabel();
        totalLoan = new javax.swing.JLabel();
        totalSavings = new javax.swing.JLabel();
        pastDuePrincipal = new javax.swing.JLabel();
        currentDemandPrincipal = new javax.swing.JLabel();
        totalInterest = new javax.swing.JLabel();
        mainTotal = new javax.swing.JLabel();
        summary = new javax.swing.JButton();
        DateofDebt = new javax.swing.JLabel();
        pastDueInterest = new javax.swing.JLabel();
        currentDemandInterest = new javax.swing.JLabel();
        pastDuePrincipalValue = new javax.swing.JLabel();
        statusValue = new javax.swing.JLabel();
        totalLoanValue = new javax.swing.JLabel();
        totalInterestValue = new javax.swing.JLabel();
        totalSavingsValue = new javax.swing.JLabel();
        currentDemandPrincipalValue = new javax.swing.JLabel();
        mainTotalValue = new javax.swing.JLabel();
        currentDemandInterestValue = new javax.swing.JLabel();
        pastDueInterestValue = new javax.swing.JLabel();
        DateofDebtValue = new javax.swing.JLabel();
        NoOfInst = new javax.swing.JLabel();
        NoOfInstValue = new javax.swing.JLabel();
        OSPrincipal = new javax.swing.JLabel();
        OSPrincipalValue = new javax.swing.JLabel();
        OSInterest = new javax.swing.JLabel();
        OSInterestValue = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        sum_of_the_three = new javax.swing.JLabel();
        sum_of_three = new javax.swing.JTextField();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        CallProcItem = new javax.swing.JMenuItem();
        ExportItem = new javax.swing.JMenuItem();
        ImportItem = new javax.swing.JMenuItem();
        ExitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(offlinereceipt.OfflineReceiptApp.class).getContext().getResourceMap(OfflineReceiptView.class);
        date.setText(resourceMap.getString("date.text")); // NOI18N
        date.setToolTipText(resourceMap.getString("date.toolTipText")); // NOI18N
        date.setName("date"); // NOI18N

        externalId.setText(resourceMap.getString("externalId.text")); // NOI18N
        externalId.setName("externalId"); // NOI18N

        externalIdText.setText(resourceMap.getString("externalIdText.text")); // NOI18N
        externalIdText.setName("externalIdText"); // NOI18N
        externalIdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                externalIdTextActionPerformed(evt);
            }
        });

        groupName.setText(resourceMap.getString("groupName.text")); // NOI18N
        groupName.setName("groupName"); // NOI18N

        memberName.setText(resourceMap.getString("memberName.text")); // NOI18N
        memberName.setName("memberName"); // NOI18N

        loanPrinciple.setText(resourceMap.getString("loanPrinciple.text")); // NOI18N
        loanPrinciple.setName("loanPrinciple"); // NOI18N

        loanIntrest.setText(resourceMap.getString("loanIntrest.text")); // NOI18N
        loanIntrest.setName("loanIntrest"); // NOI18N

        loanAmount.setText(resourceMap.getString("loanAmount.text")); // NOI18N
        loanAmount.setName("loanAmount"); // NOI18N

        loanAmountText.setText(resourceMap.getString("loanAmountText.text")); // NOI18N
        loanAmountText.setName("loanAmountText"); // NOI18N
        loanAmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanAmountTextActionPerformed(evt);
            }
        });

        savingAmount.setText(resourceMap.getString("savingAmount.text")); // NOI18N
        savingAmount.setName("savingAmount"); // NOI18N

        savingAmountText.setText(resourceMap.getString("savingAmountText.text")); // NOI18N
        savingAmountText.setName("savingAmountText"); // NOI18N
        savingAmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savingAmountTextActionPerformed(evt);
            }
        });

        fees.setText(resourceMap.getString("fees.text")); // NOI18N
        fees.setName("fees"); // NOI18N

        feesText.setText(resourceMap.getString("feesText.text")); // NOI18N
        feesText.setName("feesText"); // NOI18N
        feesText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feesTextActionPerformed(evt);
            }
        });

        save.setText(resourceMap.getString("save.text")); // NOI18N
        save.setName("save"); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        groupNameValue.setText(resourceMap.getString("groupNameValue.text")); // NOI18N
        groupNameValue.setName("groupNameValue"); // NOI18N

        memberNameValue.setText(resourceMap.getString("memberNameValue.text")); // NOI18N
        memberNameValue.setName("memberNameValue"); // NOI18N

        loanPrincipleValue.setText(resourceMap.getString("loanPrincipleValue.text")); // NOI18N
        loanPrincipleValue.setName("loanPrincipleValue"); // NOI18N

        loanInterestValue.setText(resourceMap.getString("loanInterestValue.text")); // NOI18N
        loanInterestValue.setName("loanInterestValue"); // NOI18N

        dateValue.setText(resourceMap.getString("dateValue.text")); // NOI18N
        dateValue.setName("dateValue"); // NOI18N

        totalValue.setText(resourceMap.getString("totalValue.text")); // NOI18N
        totalValue.setName("totalValue"); // NOI18N

        total.setText(resourceMap.getString("total.text")); // NOI18N
        total.setName("total"); // NOI18N

        comboSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A Village", "Aler", "Bahadurpet", "Bharath_Nagar", "Chintal_Basti", "Katamayya_Nagar", "Kolanupaka-1", "Kolanupaka-2", "Kolanupaka-3", "KranthiNagar", "Manthapuri", "Raghavapoor", "SilkNagar", "SubhashNagar", "Kolluru", "Saigudem" }));
        comboSelect.setName("comboSelect"); // NOI18N
        comboSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSelectActionPerformed(evt);
            }
        });

        selectBranch.setText(resourceMap.getString("selectBranch.text")); // NOI18N
        selectBranch.setName("selectBranch"); // NOI18N

        cancel.setText(resourceMap.getString("cancel.text")); // NOI18N
        cancel.setName("cancel"); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        accountStatus.setText(resourceMap.getString("accountStatus.text")); // NOI18N
        accountStatus.setName("accountStatus"); // NOI18N

        totalLoan.setText(resourceMap.getString("totalLoan.text")); // NOI18N
        totalLoan.setName("totalLoan"); // NOI18N

        totalSavings.setText(resourceMap.getString("totalSavings.text")); // NOI18N
        totalSavings.setName("totalSavings"); // NOI18N

        pastDuePrincipal.setText(resourceMap.getString("pastDuePrincipal.text")); // NOI18N
        pastDuePrincipal.setName("pastDuePrincipal"); // NOI18N

        currentDemandPrincipal.setText(resourceMap.getString("currentDemandPrincipal.text")); // NOI18N
        currentDemandPrincipal.setName("currentDemandPrincipal"); // NOI18N

        totalInterest.setText(resourceMap.getString("totalInterest.text")); // NOI18N
        totalInterest.setName("totalInterest"); // NOI18N

        mainTotal.setText(resourceMap.getString("mainTotal.text")); // NOI18N
        mainTotal.setName("mainTotal"); // NOI18N

        summary.setText(resourceMap.getString("summary.text")); // NOI18N
        summary.setName("summary"); // NOI18N
        summary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                summaryActionPerformed(evt);
            }
        });

        DateofDebt.setText(resourceMap.getString("DateofDebt.text")); // NOI18N
        DateofDebt.setName("DateofDebt"); // NOI18N

        pastDueInterest.setText(resourceMap.getString("pastDueInterest.text")); // NOI18N
        pastDueInterest.setName("pastDueInterest"); // NOI18N

        currentDemandInterest.setText(resourceMap.getString("currentDemandInterest.text")); // NOI18N
        currentDemandInterest.setName("currentDemandInterest"); // NOI18N

        pastDuePrincipalValue.setText(resourceMap.getString("pastDuePrincipalValue.text")); // NOI18N
        pastDuePrincipalValue.setName("pastDuePrincipalValue"); // NOI18N

        statusValue.setText(resourceMap.getString("statusValue.text")); // NOI18N
        statusValue.setName("statusValue"); // NOI18N

        totalLoanValue.setText(resourceMap.getString("totalLoanValue.text")); // NOI18N
        totalLoanValue.setName("totalLoanValue"); // NOI18N

        totalInterestValue.setText(resourceMap.getString("totalInterestValue.text")); // NOI18N
        totalInterestValue.setName("totalInterestValue"); // NOI18N

        totalSavingsValue.setText(resourceMap.getString("totalSavingsValue.text")); // NOI18N
        totalSavingsValue.setName("totalSavingsValue"); // NOI18N

        currentDemandPrincipalValue.setText(resourceMap.getString("currentDemandPrincipalValue.text")); // NOI18N
        currentDemandPrincipalValue.setName("currentDemandPrincipalValue"); // NOI18N

        mainTotalValue.setText(resourceMap.getString("mainTotalValue.text")); // NOI18N
        mainTotalValue.setName("mainTotalValue"); // NOI18N

        currentDemandInterestValue.setText(resourceMap.getString("currentDemandInterestValue.text")); // NOI18N
        currentDemandInterestValue.setName("currentDemandInterestValue"); // NOI18N

        pastDueInterestValue.setText(resourceMap.getString("pastDueInterestValue.text")); // NOI18N
        pastDueInterestValue.setName("pastDueInterestValue"); // NOI18N

        DateofDebtValue.setText(resourceMap.getString("DateofDebtValue.text")); // NOI18N
        DateofDebtValue.setName("DateofDebtValue"); // NOI18N

        NoOfInst.setText(resourceMap.getString("NoOfInst.text")); // NOI18N
        NoOfInst.setName("NoOfInst"); // NOI18N

        NoOfInstValue.setText(resourceMap.getString("NoOfInstValue.text")); // NOI18N
        NoOfInstValue.setName("NoOfInstValue"); // NOI18N

        OSPrincipal.setText(resourceMap.getString("OSPrincipal.text")); // NOI18N
        OSPrincipal.setName("OSPrincipal"); // NOI18N

        OSPrincipalValue.setText(resourceMap.getString("OSPrincipalValue.text")); // NOI18N
        OSPrincipalValue.setName("OSPrincipalValue"); // NOI18N

        OSInterest.setText(resourceMap.getString("OSInterest.text")); // NOI18N
        OSInterest.setName("OSInterest"); // NOI18N

        OSInterestValue.setText(resourceMap.getString("OSInterestValue.text")); // NOI18N
        OSInterestValue.setName("OSInterestValue"); // NOI18N

        print.setText(resourceMap.getString("print.text")); // NOI18N
        print.setName("print"); // NOI18N
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        sum_of_the_three.setText(resourceMap.getString("sum_of_the_three.text")); // NOI18N
        sum_of_the_three.setName("sum_of_the_three"); // NOI18N

        sum_of_three.setText(resourceMap.getString("sum_of_three.text")); // NOI18N
        sum_of_three.setName("sum_of_three"); // NOI18N
        sum_of_three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sum_of_threeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(date)
                            .addComponent(memberName)
                            .addComponent(loanIntrest)
                            .addComponent(total)
                            .addComponent(groupName)
                            .addComponent(externalId)
                            .addComponent(loanPrinciple)
                            .addComponent(fees)
                            .addComponent(selectBranch)
                            .addComponent(savingAmount)
                            .addComponent(loanAmount))
                        .addGap(12, 12, 12))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(sum_of_the_three)
                        .addGap(18, 18, 18)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(summary)
                        .addGap(483, 483, 483))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(totalSavings)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                                .addComponent(totalValue, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                                .addGap(69, 69, 69))
                                            .addComponent(loanInterestValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(loanPrincipleValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(memberNameValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(groupNameValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(externalIdText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(feesText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(loanAmountText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(savingAmountText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(sum_of_three, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                        .addGap(108, 108, 108)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mainTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(currentDemandPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(accountStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(DateofDebt, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(NoOfInst, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pastDuePrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(totalInterest, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(totalLoan, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(OSPrincipal, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NoOfInstValue)
                                    .addComponent(totalLoanValue)
                                    .addComponent(mainTotalValue)
                                    .addComponent(DateofDebtValue)
                                    .addComponent(statusValue)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pastDuePrincipalValue)
                                            .addComponent(totalSavingsValue)
                                            .addComponent(totalInterestValue)
                                            .addComponent(OSPrincipalValue)
                                            .addComponent(currentDemandPrincipalValue))
                                        .addGap(32, 32, 32)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(OSInterest)
                                            .addComponent(currentDemandInterest)
                                            .addComponent(pastDueInterest)
                                            .addComponent(print))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pastDueInterestValue)
                                            .addComponent(currentDemandInterestValue)
                                            .addComponent(OSInterestValue)))))
                            .addComponent(dateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 110, 110))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectBranch)
                    .addComponent(comboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(externalId)
                            .addComponent(externalIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DateofDebt)
                            .addComponent(DateofDebtValue))
                        .addGap(18, 18, 18)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(accountStatus)
                        .addComponent(statusValue)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberName)
                            .addComponent(memberNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NoOfInst)
                            .addComponent(NoOfInstValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OSPrincipal)
                            .addComponent(OSPrincipalValue)
                            .addComponent(OSInterest)
                            .addComponent(OSInterestValue))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(loanPrincipleValue, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                            .addComponent(loanPrinciple))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanIntrest)
                            .addComponent(loanInterestValue, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(savingAmount)
                            .addComponent(savingAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanAmount)
                            .addComponent(loanAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(feesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fees)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalLoan)
                            .addComponent(totalLoanValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalInterest)
                            .addComponent(totalInterestValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalSavings)
                            .addComponent(totalSavingsValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pastDuePrincipal)
                            .addComponent(pastDuePrincipalValue)
                            .addComponent(pastDueInterest)
                            .addComponent(pastDueInterestValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentDemandPrincipal)
                            .addComponent(currentDemandPrincipalValue)
                            .addComponent(currentDemandInterest)
                            .addComponent(currentDemandInterestValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mainTotal)
                            .addComponent(mainTotalValue))))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sum_of_three, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sum_of_the_three))
                .addGap(32, 32, 32)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(cancel)
                    .addComponent(summary)
                    .addComponent(print))
                .addGap(51, 51, 51))
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 951, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText("File");
        jMenu1.setName("jMenu1"); // NOI18N

        CallProcItem.setText(resourceMap.getString("CallProcItem.text")); // NOI18N
        CallProcItem.setName("CallProcItem"); // NOI18N
        CallProcItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CallProcItemActionPerformed(evt);
            }
        });
        jMenu1.add(CallProcItem);

        ExportItem.setText(resourceMap.getString("ExportItem.text")); // NOI18N
        ExportItem.setName("ExportItem"); // NOI18N
        ExportItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportItemActionPerformed(evt);
            }
        });
        jMenu1.add(ExportItem);

        ImportItem.setText(resourceMap.getString("ImportItem.text")); // NOI18N
        ImportItem.setName("ImportItem"); // NOI18N
        ImportItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportItemActionPerformed(evt);
            }
        });
        jMenu1.add(ImportItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(offlinereceipt.OfflineReceiptApp.class).getContext().getActionMap(OfflineReceiptView.class, this);
        ExitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        ExitMenuItem.setText(resourceMap.getString("ExitMenuItem.text")); // NOI18N
        ExitMenuItem.setName("ExitMenuItem"); // NOI18N
        jMenu1.add(ExitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setName("jMenu2"); // NOI18N
        jMenuBar1.add(jMenu2);

        setComponent(mainPanel);
        setMenuBar(jMenuBar1);
        setStatusBar(statusPanel);
    }// </editor-fold>                        

    @SuppressWarnings("CallToThreadDumpStack")
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {                                     
        try {
            /** Creating Input Stream**/
            FileInputStream myInput = new FileInputStream(f.toString());

            /** Create a POIFSFileSystem object**/
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            /** Create a workbook using the File System**/
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            myWorkBook.writeProtectWorkbook("reddy", "Madhukar");
            /** Get the first sheet from workbook**/
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            HSSFRow myRow = mySheet.getRow(rowForcell);
            Date d = new Date();
            String no = "";
            long receiptNo = d.getTime();
            LocalDate localDate = new LocalDate();
            HSSFCell myCell = myRow.getCell(6);
            myCell.setCellValue(Double.parseDouble(loanAmountText.getText()));
            myCell = myRow.getCell(7);
            myCell.setCellValue(Double.parseDouble(savingAmountText.getText()));
            myCell = myRow.getCell(8);
            myCell.setCellValue(Double.parseDouble(feesText.getText()));

            myCell = myRow.getCell(9, Row.CREATE_NULL_AS_BLANK);

            myCell.setCellValue(localDate.getDayOfMonth() + "-" + localDate.getMonthOfYear() + "-" + localDate.getYear());


            myCell = myRow.getCell(10);
            myCell.setCellType(HSSFCell.CELL_TYPE_STRING);

            //System.out.println("Cell at 10 is..."+myCell.getStringCellValue());

            List l = new ArrayList();
            //System.out.println("PhysicalNumberOfRows"+mySheet.getPhysicalNumberOfRows());


            for (int i = 1; i <= mySheet.getPhysicalNumberOfRows()-1; i++) {

                try {
                    HSSFRow row = mySheet.getRow(i);
                    HSSFCell cell = row.getCell(10);
                    String res = cell.getStringCellValue();
                    l.add(new Integer(res));
                   // System.out.println("list contains.." + i);
                    //System.out.println("list contains.." + l);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }

            }


            Object obj = Collections.max(l);
            //System.out.println("Maximum Element of Java ArrayList is : " + obj);
            int value = new Integer(obj.toString());
            int value1 = value + 1;
            String s = new Integer(value1).toString();
            myCell.setCellValue(s);

            FileOutputStream fileOut = new FileOutputStream(f.toString());
            mySheet.protectSheet("reddy");
            myWorkBook.write(fileOut);
            fileOut.close();

            //Sivaji Print
            try {
                FileInputStream myInput1 = new FileInputStream(f.toString());

                /** Create a POIFSFileSystem object **/
                POIFSFileSystem myFileSystem1 = new POIFSFileSystem(myInput1);

                /** Create a workbook using the File System **/
                HSSFWorkbook myWorkBook1 = new HSSFWorkbook(myFileSystem1);
                myWorkBook1.writeProtectWorkbook("madhu", "reddy");

                /** Get the first sheet from workbook **/
                HSSFSheet mySheet1 = myWorkBook1.getSheetAt(0);

                /** We now need something to iterate through the cells. **/
                Iterator rowIter1 = mySheet1.rowIterator();
                HSSFRow myRow1 = mySheet1.getRow(rowForcell);
                List list = new ArrayList();
                Iterator cellItr = myRow1.cellIterator();
                cellItr.next();
                while (cellItr.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellItr.next();
                    list.add(cell.toString());
                }

                String id = externalId.getText();
                printcustomerId = externalIdText.getText();
                printDate = (String) list.get(8);
                printLoanAmount = ((String) list.get(5)).replace(".0", "");
                printSavingAmount = ((String) list.get(6)).replace(".0", "");
                printFeesAmount = ((String) list.get(7)).replace(".0", "");
                printReciptNo = (String) list.get(9);
                printGroupName = (String) list.get(1);
                printMemberName = (String) list.get(2);
                //System.out.println("Member Name is:"+printMemberName);
                printLoanOfficer = (String) list.get(10);

                // System.out.println(list);

            } catch (Exception exp) {
                exp.printStackTrace();
            }

            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pfl = job.defaultPage();
            Paper paper = new Paper();
            paper.setSize(216, 210); // Large Address Dimension
            paper.setImageableArea(29, 4, 210, 210);
            pfl.setPaper(paper);
            pfl.setOrientation(PageFormat.PORTRAIT);
            // Set up a book//
            Book bk = new Book();
            bk.append(new PaintCover(), pfl);

            // bk.append(new PaintContent(), job.defaultPage(), 2);
            // Pass the book to the PrinterJob
            job.setPageable(bk);
            // Put up the dialog box

            //if (job.printDialog()) {

            try {
                job.print();
            } catch (Exception et) { /* handle exception */

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //externalIdText.setText("");
        loanAmountText.setText("");
        savingAmountText.setText("");
        feesText.setText("");
        memberNameValue.setText("");
        groupNameValue.setText("");
        loanInterestValue.setText("");
        loanPrincipleValue.setText("");
        totalValue.setText("");
        currentDemandInterestValue.setText("");
        currentDemandPrincipalValue.setText("");
        pastDueInterestValue.setText("");
        pastDuePrincipalValue.setText("");
        totalLoanValue.setText("");
        totalInterestValue.setText("");
        DateofDebtValue.setText("");
        mainTotalValue.setText("");
        statusValue.setText("");

        DateofDebtValue.setText("");
        statusValue.setText("");
        NoOfInstValue.setText("");
        OSPrincipalValue.setText("");
        OSInterestValue.setText("");
        totalLoanValue.setText("");
        totalInterestValue.setText("");
        totalSavingsValue.setText("");
        pastDuePrincipalValue.setText("");
        pastDueInterestValue.setText("");
        currentDemandPrincipalValue.setText("");
        currentDemandInterestValue.setText("");
        mainTotalValue.setText("");
        sum_of_three.setText("");
        externalIdText.requestFocus();
        externalIdText.moveCaretPosition(9);
        externalIdText.setCaretPosition(8);
        JOptionPane.showMessageDialog(null, "Successfully Updated ");


    }                                    

    private void comboSelectActionPerformed(java.awt.event.ActionEvent evt) {                                            
        comboSelect = (JComboBox) evt.getSource();
        String selected = (String) comboSelect.getSelectedItem();
        System.out.println("Selected Item  = " + selected);
        SumCal sc=new SumCal();
        sc.getBranchName(selected);
        String command = evt.getActionCommand();
        System.out.println("Action Command = " + command);
        File file = new File(".");
        String fname = null;
        try {
            fname = file.getCanonicalPath() + "/";

        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        try {

            // create BufferedReader to read csv file
            BufferedReader br = new BufferedReader(new FileReader("Details.csv"));
            String strLine = "";
            StringTokenizer st = null;
            int lineNumber = 0, tokenNumber = 0;

            // read comma separated file line by line
            while ((strLine = br.readLine()) != null) {
                lineNumber++;


                // break comma separated line using ","
                st = new StringTokenizer(strLine, ",");

                while (st.hasMoreTokens()) {
                    // display csv values
                    while (st.nextToken().equals(selected.trim())) {
                        while (st.hasMoreTokens()) {

                            //System.out.println("the next token is.."+st.nextToken());
                            s = st.nextToken();
                            String s1 = st.nextToken();
                            externalIdText.requestFocus();
                            externalIdText.setText(s);
                            f = new File(fname + s1);
                            System.out.println(f.toString());
                            //System.out.println(s+"\t"+s1);

                        }
                        tokenNumber++;
                    }
                }

                // reset token number
                tokenNumber = 0;

            }

            //System.out.println(s);

        } catch (Exception e) {
            System.err.println("Exception while reading csv file: " + e);
        }
    }                                           

    private void externalIdTextActionPerformed(java.awt.event.ActionEvent evt) {                                               

        externalId.setFont(fontTeluguGautami10);
        loanAmountText.setText("0");
        savingAmountText.setText("0");
        feesText.setText("0");
        if (externalIdText.getText().equals("") || externalIdText.getText() == null) {
            JOptionPane.showMessageDialog(null, "You have not entered anything in text field: " + externalIdText.getText());
        } else {
            LocalDate localDate = new LocalDate();
            dateValue.setText(localDate.getDayOfMonth() + "-" + localDate.getMonthOfYear() + "-" + localDate.getYear());
            Vector cellVectorHolder = new Vector();
            try {
                /** Creating Input Stream**/
                //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
                FileInputStream myInput = new FileInputStream(f.toString());

                /** Create a POIFSFileSystem object**/
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                /** Create a workbook using the File System**/
                HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                /** Get the first sheet from workbook**/
                HSSFSheet mySheet = myWorkBook.getSheetAt(0);

                /** We now need something to iterate through the cells.**/
                Iterator rowIter = mySheet.rowIterator();
                HSSFRow myRow = (HSSFRow) rowIter.next();

                while (rowIter.hasNext()) {
                    //rowIter.next();
                    myRow = (HSSFRow) rowIter.next();
                    Iterator cellIter = myRow.cellIterator();
                    Vector cellStoreVector = new Vector();
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        cellStoreVector.addElement(myCell.toString());

                    }
                    
                    //modified
                    
                    if (cellStoreVector.size() > 0) {
                        // Double d = new Double(externalIdText.getText());
                        //if (externalIdText.getText().length()==9) {
                        if (((String) cellStoreVector.get(0)).equals(externalIdText.getText())) {
                            rowno = myRow.getRowNum();
                            rowForcell = rowno;
                        }
                        //}else
                          //  break;
                                          }
                    cellVectorHolder.add(cellStoreVector);
                }

               // int newRowNo = cellVectorHolder.size() + 1;
                if(rowno != 0){
                HSSFRow row = mySheet.getRow(rowno);
                List list = new ArrayList();
                Iterator cellItr = row.cellIterator();
                while (cellItr.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellItr.next();
                    list.add(cell.toString());
                }
                if (!((String) list.get(6)).equals("0.0") || !((String) list.get(7)).equals("0.0") || !((String) list.get(8)).equals("0.0")) {
                    JOptionPane.showMessageDialog(null, "The Record is already updated");

                }
                    
                savingAmountText.requestFocus();
                System.out.println("at 968 the list size is.."+list.size());
                groupNameValue.setText((String) list.get(2));
                groupNameValue.setFont(fontTeluguGautami10);
                memberNameValue.setText((String) list.get(3));
                memberNameValue.setFont(fontTeluguGautami10);
                loanPrincipleValue.setText(((String) list.get(4)).replace(".0", ""));
                loanInterestValue.setText(((String) list.get(5)).replace(".0", ""));
                String loanPrincpleString = (String) list.get(4);
                Double loanPrincple = Double.parseDouble(loanPrincpleString);
                String interestString = (String) list.get(5);
                Double interest = Double.parseDouble(interestString);
                Double sum = loanPrincple + interest;
                
                totalValue.setText(sum.toString().replace(".0", ""));
                loanAmountText.setText(((String) list.get(6)).replace(".0", ""));
                savingAmountText.setText(((String) list.get(7)).replace(".0", ""));
                feesText.setText(((String) list.get(8)).replace(".0", ""));
                total_of_three=Double.valueOf(list.get(6).toString())+Double.valueOf(list.get(7).toString())+Double.valueOf(list.get(8).toString());
                sum_of_three.setText(Double.toString(total_of_three).replace(".0",""));
                sum_of_three.setEditable(false);
                statusValue.setText((String) list.get(14));
                totalLoanValue.setText(((String) list.get(15)).replace(".0", ""));
                DateofDebtValue.setText(((String) list.get(25)).replace(".0", ""));
                totalInterestValue.setText(((String) list.get(16)).replace(".0", ""));
                pastDuePrincipalValue.setText(((String) list.get(18)).replace(".0", ""));
                pastDueInterestValue.setText(((String) list.get(19)).replace(".0", ""));
                currentDemandPrincipalValue.setText(((String) list.get(20)).replace(".0", ""));
                currentDemandInterestValue.setText(((String) list.get(21)).replace(".0", ""));
                totalSavingsValue.setText(((String) list.get(17)).replace(".0", ""));
                NoOfInstValue.setText(list.get(26).toString().replace(".0", ""));
                OSPrincipalValue.setText(((String) list.get(27)).replace(".0", ""));
                OSInterestValue.setText(((String) list.get(28)).replace(".0", ""));

                Double pastLoanPricipal = Double.parseDouble((String) list.get(18));
                Double pastLoanInterest = Double.parseDouble((String) list.get(19));
                Double currentPrincipal = Double.parseDouble((String) list.get(20));
                Double currentInterest = Double.parseDouble((String) list.get(21));

                Double totalPrinciple = pastLoanPricipal + pastLoanInterest + currentPrincipal + currentInterest;

                mainTotalValue.setText(totalPrinciple.toString().replace(".0", ""));
                rowno = 0;
                }
                else{
            JOptionPane.showMessageDialog(null,"Please enter a valid ExternalId");
            externalIdText.setText(s);
            externalIdText.requestFocus();
            }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        
        savingAmountText.setCaretPosition(1);
        savingAmountText.moveCaretPosition(0);
        

    }                                              

    private void loanAmountTextActionPerformed(java.awt.event.ActionEvent evt) {                                               
        feesText.requestFocus();
        feesText.setCaretPosition(1);
        feesText.moveCaretPosition(0);
    }                                              

    private void savingAmountTextActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        loanAmountText.requestFocus();
        loanAmountText.setCaretPosition(1);
        loanAmountText.moveCaretPosition(0);
    }                                                

    private void feesTextActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int sum_of_above_three= Integer.parseInt(savingAmountText.getText())+Integer.parseInt(loanAmountText.getText())+Integer.parseInt(feesText.getText());
       sum_of_three.setText(Integer.toString(sum_of_above_three).replace(".0",""));
       sum_of_three.setEditable(false);
        //int dialogButton = JOptionPane.YES_OPTION;
          int dialogButton=JOptionPane.showConfirmDialog (null, "click Yes to continue","Confirm",JOptionPane.YES_NO_OPTION);
        if(dialogButton==JOptionPane.YES_OPTION){
        //System.out.println("Correct");
       sum_of_threeActionPerformed(evt);
        }
        else{
            savingAmountText.requestFocus();
            //System.out.println("Wronggg");
        }
        

    }                                        

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        loanPrincipleValue.setText("");
        loanInterestValue.setText("");
        totalValue.setText("");
        groupNameValue.setText("");
        memberNameValue.setText("");
        loanAmountText.setText("0");
        savingAmountText.setText("0");
        feesText.setText("0");
        currentDemandInterestValue.setText("");
        currentDemandPrincipalValue.setText("");
        pastDueInterestValue.setText("");
        pastDuePrincipalValue.setText("");
        totalLoanValue.setText("");
        totalInterestValue.setText("");
        DateofDebtValue.setText("");
        mainTotalValue.setText("");
        statusValue.setText("");

        externalIdText.requestFocus();
        externalIdText.setCaretPosition(9);
        externalIdText.moveCaretPosition(0);
    }                                      

    private void summaryActionPerformed(java.awt.event.ActionEvent evt) {                                        
        Vector cellVectorHolder = new Vector();
        if (f == null) {
            JOptionPane.showMessageDialog(null, "Please Select a Excel File");
        } else {

            try {
                /** Creating Input Stream**/
                FileInputStream myInput = new FileInputStream(f.toString());

                /** Create a POIFSFileSystem object**/
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                /** Create a workbook using the File System**/
                HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                /** Get the first sheet from workbook**/
                HSSFSheet mySheet = myWorkBook.getSheetAt(0);

                /** We now need something to iterate through the cells.**/
                Iterator rowIter = mySheet.rowIterator();
                HSSFRow myRow = (HSSFRow) rowIter.next();

                List l = new ArrayList();
                List l2 = new ArrayList();
                List l3 = new ArrayList();
                while (rowIter.hasNext()) {
                    //rowIter.next();
                    myRow = (HSSFRow) rowIter.next();
                    HSSFCell pv1 = myRow.getCell(6);
                    Double d = pv1.getNumericCellValue();
                    l.add(d);
                    HSSFCell pv2 = myRow.getCell(7);
                    Double d2 = pv2.getNumericCellValue();
                    l2.add(d2);
                    HSSFCell pv3 = myRow.getCell(8);
                    Double d3 = pv3.getNumericCellValue();
                    l3.add(d3);
                }
                 loanSum = 0.0;
                Iterator it1 = l.iterator();
                while (it1.hasNext()) {
                    Double d = (Double) it1.next();
                    loanSum += d;

                }

                 savingSum = 0.0;
                Iterator it2 = l2.iterator();
                while (it2.hasNext()) {
                    Double d = (Double) it2.next();
                    savingSum += d;

                }
                 feeSum = 0.0;
                Iterator it3 = l3.iterator();
                while (it3.hasNext()) {
                    Double d = (Double) it3.next();
                    feeSum += d;

                }
                Double totalSum = loanSum + savingSum + feeSum;
                JOptionPane.showMessageDialog(null, "Sum of Savings is:" + savingSum + "\nSum of Loan Amount is:" + loanSum + "\nSum of Fee is: " + feeSum + "\n-----------------------------\nTotal Sum:" + totalSum + "\n---------------------------");

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }                                       

    private void printActionPerformed(java.awt.event.ActionEvent evt) {                                      
                if (!(externalIdText.getText().equals("") || externalIdText.getText() == null)) {
            try {
                BufferedWriter bw = null;
                String encoding = "UTF8";

                // try {
                // TODO add your handling code here:
                ArrayList arrlist = new ArrayList();


                HSSFCell cell1, cell2, cell3, cell4, cell5, cell6;
                int rownum = 1;
                exid = externalIdText.getText();
                System.out.println("Exter.." + externalIdText.getText());
                //bw = new BufferedWriter(new FileWriter("./PrintFile.txt"));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./PrintFile.txt"), "UTF8"));

                bw.write("Receiptno" + "\t" + "ExternalId" + "\t" + "Name" + "\t" + "\t" + "\t" + "\t" + "LA" + "\t" + "SA" + "\t" + "FEE" + "\t" + "Total");
                bw.newLine();
                bw.write("--------------------------------------------------------------------------------------------");
                bw.newLine();
                FileInputStream fis = null;
                fis = new FileInputStream(f.toString());
                POIFSFileSystem poifs = new POIFSFileSystem(fis);
                HSSFWorkbook myWorkBook = new HSSFWorkbook(poifs);
                HSSFSheet mySheet = myWorkBook.getSheetAt(0);

                for (int i = 1; i <= mySheet.getPhysicalNumberOfRows() - 1; i++) {
                    HSSFRow row = mySheet.getRow(i);
                    double la = row.getCell(6).getNumericCellValue();
                    double sa = row.getCell(7).getNumericCellValue();
                    double feee = row.getCell(8).getNumericCellValue();
                    String recptno = row.getCell(10).getStringCellValue();
                    if (la > 0.0 || sa > 0.0 || feee > 0.0) {

                        arrlist.add(Integer.parseInt(recptno));
                        //System.out.println("addedddddddd.."+recptno);
                    }
                }
                Collections.sort(arrlist);
                Iterator ite = arrlist.iterator();
                while (ite.hasNext()) {
                    String rn = ite.next().toString();
                    for (int r = 1; r <= mySheet.getPhysicalNumberOfRows() - 1; r++) {
                        HSSFRow row = mySheet.getRow(r);
                        if (row.getCell(10).getStringCellValue().equals(rn)) {
                            cell1 = row.getCell(6);
                            cell2 = row.getCell(7);
                            cell3 = row.getCell(8);
                            cell4 = row.getCell(10);
                            cell5 = row.getCell(0);
                            cell6 = row.getCell(3);
                            double cellval1 = cell1.getNumericCellValue();
                            double cellval2 = cell2.getNumericCellValue();
                            double cellval3 = cell3.getNumericCellValue();
                            String cellval4 = cell4.getStringCellValue();
                            String cellval5 = cell5.getStringCellValue();
                            String cellval6 = cell6.getStringCellValue();
                            String tot = Double.toString(cellval1 + cellval2 + cellval3).replace(".0", "");
                            bw.write(cellval4 + "\t" + "\t" + cellval5 + "\t" + cellval6.trim() + "\t" + "\t" + "\t" + Double.toString(cellval1).replace(".0", "").trim() + "\t" + Double.toString(cellval2).replace(".0", "") + "\t" + Double.toString(cellval3).replace(".0", "") + "\t" + tot);
                            bw.newLine();

                            // System.out.println("Got it!!!"+row.getCell(10).getStringCellValue()+"\t"+row.getCell(0).getStringCellValue());
                        }

                    }
                }
                bw.write("----------------------------------------------------------------------------------------------");
                bw.newLine();
                bw.write("\t" + "\t" + "\t" + "\t" + "\t" + "Total:" + "\t" + "\t" + "\t" + ((Double) loanSum).toString().replace(".0", "") + "\t" + ((Double) savingSum).toString().replace(".0", "") + "\t" + ((Double) feeSum).toString().replace(".0", "") + "\t" + Double.toString(loanSum + savingSum + feeSum).replace(".0", ""));
                bw.newLine();
                bw.newLine();
                bw.newLine();
                bw.newLine();
                bw.write("*LA=Loan Ammount   *SA=Saving Ammount");
                bw.flush();
                // System.out.println(arrlist);
           } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Runtime.getRuntime().exec("notepad ./PrintFile.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a branch");
        }

    }                                     

    private void CallProcItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here
        //CallProcedure cp=new CallProcedure();
        JOptionPane.showMessageDialog(null,"Executing the procedure, Click 'Ok' to continue");
        String msg="";
        
            msg = CallProcedure.execProc();
        
        System.out.println(msg);
        JOptionPane.showMessageDialog(null,msg);
    }                                            

    private void ExportItemActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        int globalOfficeId = 0;
        final List<OfficeDto> list = DataRetrieve_Remote.getOfficeDetails();
        Iterator iterator = list.iterator();
        System.out.print("list size is" +list.size());
        JOptionPane.showMessageDialog(null,"Wait! Exporting is in process..Click 'ok' to continue");
        while (iterator.hasNext()) {
        OfficeDto dto = (OfficeDto) iterator.next();
        globalOfficeId = dto.getGlobalNo();
        String branch_Name=dto.getDisplayName().trim();
        DataRetrieve_Remote dataRetrieve_Remote = new DataRetrieve_Remote(globalOfficeId,branch_Name);
        System.out.println("added.."+globalOfficeId+"\tbranch name:"+branch_Name);
        }
        JOptionPane.showMessageDialog(null,"Exporting is Successfull");
    }                                          

    private void ImportItemActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
            JOptionPane.showMessageDialog(null, "make sure that the server is running");        
              RestClientMain.main(null);
              try {
                Runtime.getRuntime().exec("notepad ./report.log");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
    }                                          

    private void sum_of_threeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        saveActionPerformed(evt);
        externalIdText.requestFocus();
        externalIdText.setText(s);
    }                                            

    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem CallProcItem;
    private javax.swing.JLabel DateofDebt;
    private javax.swing.JLabel DateofDebtValue;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JMenuItem ExportItem;
    private javax.swing.JMenuItem ImportItem;
    private javax.swing.JLabel NoOfInst;
    private javax.swing.JLabel NoOfInstValue;
    private javax.swing.JLabel OSInterest;
    private javax.swing.JLabel OSInterestValue;
    private javax.swing.JLabel OSPrincipal;
    private javax.swing.JLabel OSPrincipalValue;
    private javax.swing.JLabel accountStatus;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox comboSelect;
    private javax.swing.JLabel currentDemandInterest;
    private javax.swing.JLabel currentDemandInterestValue;
    private javax.swing.JLabel currentDemandPrincipal;
    private javax.swing.JLabel currentDemandPrincipalValue;
    private javax.swing.JLabel date;
    private javax.swing.JLabel dateValue;
    private javax.swing.JLabel externalId;
    private javax.swing.JTextField externalIdText;
    private javax.swing.JLabel fees;
    private javax.swing.JTextField feesText;
    private javax.swing.JLabel groupName;
    private javax.swing.JLabel groupNameValue;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel loanAmount;
    private javax.swing.JTextField loanAmountText;
    private javax.swing.JLabel loanInterestValue;
    private javax.swing.JLabel loanIntrest;
    private javax.swing.JLabel loanPrinciple;
    private javax.swing.JLabel loanPrincipleValue;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainTotal;
    private javax.swing.JLabel mainTotalValue;
    private javax.swing.JLabel memberName;
    private javax.swing.JLabel memberNameValue;
    private javax.swing.JLabel pastDueInterest;
    private javax.swing.JLabel pastDueInterestValue;
    private javax.swing.JLabel pastDuePrincipal;
    private javax.swing.JLabel pastDuePrincipalValue;
    private javax.swing.JButton print;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton save;
    private javax.swing.JLabel savingAmount;
    private javax.swing.JTextField savingAmountText;
    private javax.swing.JLabel selectBranch;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel statusValue;
    private javax.swing.JLabel sum_of_the_three;
    private javax.swing.JTextField sum_of_three;
    private javax.swing.JButton summary;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalInterest;
    private javax.swing.JLabel totalInterestValue;
    private javax.swing.JLabel totalLoan;
    private javax.swing.JLabel totalLoanValue;
    private javax.swing.JLabel totalSavings;
    private javax.swing.JLabel totalSavingsValue;
    private javax.swing.JLabel totalValue;
    // End of variables declaration                   
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private static final Font fontTeluguGautami10 = new Font("gautami", Font.PLAIN, 13);
    int rowno = 0;
    private JDialog aboutBox;
    //variables for printing
    static String printDate;
    static String printGroupName;
    static String printReciptNo;
    static String printcustomerId;
    static String printMemberName;
    static String printTotal;
    static String printLoanOfficer;
    static String printLoanAmount;
    static String printSavingAmount;
    static String printFeesAmount;
    static String receiptNo;
    
    
    // end of print variables
    //variables for printing
}
