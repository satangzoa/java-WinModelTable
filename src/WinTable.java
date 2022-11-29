import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WinTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinTable dialog = new WinTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinTable() {
		setBounds(100, 100, 454, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String columns[] = {"이름","국어","영어","수학","총점","평균"};
				String records[][] = new String[5][6];
				Scanner scanner = new Scanner(System.in);
				for(int i=0;i<5;i++) {
					System.out.print("이름입력:");
					records[i][0]=scanner.next();
					System.out.print("국어입력:");
					records[i][1]=scanner.next();
					System.out.print("영어입력:");
					records[i][2]=scanner.next();
					System.out.print("수학입력:");
					records[i][3]=scanner.next();
					int sum=0;
					sum = Integer.parseInt(records[i][1]);
					sum += Integer.parseInt(records[i][2]);
					sum += Integer.parseInt(records[i][3]);
					records[i][4]=Integer.toString(sum);
					double average = (double)sum/3;
					records[i][5]=Double.toString(average);						
				}				
				table = new JTable(records,columns);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
