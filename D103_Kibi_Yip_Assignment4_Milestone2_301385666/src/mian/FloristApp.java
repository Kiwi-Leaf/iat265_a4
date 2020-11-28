package mian;

import javax.swing.JFrame;





public class FloristApp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public FloristApp( String title) {
		// TODO Auto-generated constructor stub
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FloristPanel flowerShop = new FloristPanel(this);
		this.add(flowerShop); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FloristApp("A Day at the Florist's");
	}

}
