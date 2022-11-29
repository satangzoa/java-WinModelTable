import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinModelTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfKorean;
	private JTextField tfEnglish;
	private JTextField tfMath;
	private JTextField tfTotal;
	private JTextField tfAverage;
	private JTable table;
	JButton btnAdd;
	private JButton btnDelete;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinModelTable dialog = new WinModelTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinModelTable() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tfName.requestFocus();
			}
		});
		setTitle("Model Example(JTable)");
		setBounds(100, 100, 866, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(25, 29, 57, 15);
		contentPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					tfKorean.requestFocus();
			}
		});
		tfName.setBounds(94, 26, 116, 21);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		tfKorean = new JTextField();
		tfKorean.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					tfEnglish.requestFocus();
			}
		});
		tfKorean.setColumns(10);
		tfKorean.setBounds(94, 67, 116, 21);
		contentPanel.add(tfKorean);
		
		JLabel lblKorean = new JLabel("Korean:");
		lblKorean.setBounds(25, 70, 57, 15);
		contentPanel.add(lblKorean);
		
		tfEnglish = new JTextField();
		tfEnglish.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					tfMath.requestFocus();
			}
		});
		tfEnglish.setColumns(10);
		tfEnglish.setBounds(94, 107, 116, 21);
		contentPanel.add(tfEnglish);
		
		JLabel lblEnglish = new JLabel("English:");
		lblEnglish.setBounds(25, 110, 57, 15);
		contentPanel.add(lblEnglish);
		
		tfMath = new JTextField();
		tfMath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					int kor, eng, math, total=0;
					double avg;
					kor = Integer.parseInt(tfKorean.getText());
					eng = Integer.parseInt(tfEnglish.getText());
					math = Integer.parseInt(tfMath.getText());
					total = kor + eng + math;
					avg = (double)total/3;
					tfTotal.setText(Integer.toString(total));
					tfAverage.setText(Double.toString(avg));
					btnAdd.requestFocus();
				}
			}
		});
		tfMath.setColumns(10);
		tfMath.setBounds(94, 148, 116, 21);
		contentPanel.add(tfMath);
		
		JLabel lblMath = new JLabel("Math:");
		lblMath.setBounds(25, 151, 57, 15);
		contentPanel.add(lblMath);
		
		tfTotal = new JTextField();
		tfTotal.setEnabled(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(94, 191, 116, 21);
		contentPanel.add(tfTotal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(25, 194, 57, 15);
		contentPanel.add(lblTotal);
		
		tfAverage = new JTextField();
		tfAverage.setEnabled(false);
		tfAverage.setColumns(10);
		tfAverage.setBounds(94, 232, 116, 21);
		contentPanel.add(tfAverage);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setBounds(25, 235, 57, 15);
		contentPanel.add(lblAverage);
		
		btnAdd = new JButton("레코드 추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				InsertRecord();	
				showRecords();
				clearAll();
			}
		});
		btnAdd.setBounds(25, 279, 185, 23);
		contentPanel.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 10, 616, 342);
		contentPanel.add(scrollPane);
		
		String columnNames[]= {"번호","이름","국어","영어","수학","총점","평균"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(table.getSelectedRow());
				
				
			}
		});
		scrollPane.setViewportView(table);		
		
		btnDelete = new JButton("레코드 삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				int idx = Integer.parseInt(table.getValueAt(row, 0).toString()); // primary key
				if(row != -1) {
					DeleteRecord(idx);					
					dtm.removeRow(row);					
				}				
			}
		});
		btnDelete.setBounds(25, 312, 185, 23);
		contentPanel.add(btnDelete);
		
		showRecords();
	}

	protected void DeleteRecord(int idx) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			String sql = "delete from scoreTBL where idx=" + idx;   
			//System.out.println(sql);
			if(stmt.executeUpdate(sql) > 0)
				;			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}

	private void showRecords() {
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);  // 테이블 행의 수를 0을 만들어라. 싹 지우기
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			String sql = "select * from scoreTBL";   
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			// DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			while(rs.next()) {
				String record[] = new String[7];
				record[0] = rs.getString("idx");
				record[1] = rs.getString("name");
				record[2] = rs.getString("korean");
				record[3] = rs.getString("english");
				record[4] = rs.getString("mathmatics");
				record[5] = rs.getString("total");
				record[6] = rs.getString("average");
				dtm.addRow(record);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}			
	}

	protected void InsertRecord() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			String sql = "insert into scoreTBL values(null,'";
			sql = sql + tfName.getText() + "','";
			sql = sql + tfKorean.getText() + "','";
			sql = sql + tfEnglish.getText() +"','";
			sql = sql + tfMath.getText() + "','";
			sql = sql + tfTotal.getText() + "','";
			sql = sql + tfAverage.getText() + "')";   
			//System.out.println(sql);
			if(stmt.executeUpdate(sql) > 0)
				;			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}		
	}

	protected void clearAll() {
		tfName.setText("");
		tfKorean.setText("");
		tfEnglish.setText("");
		tfMath.setText("");
		tfTotal.setText("");
		tfAverage.setText("");
		tfName.requestFocus();
	}
}
