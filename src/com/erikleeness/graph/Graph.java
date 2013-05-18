package com.erikleeness.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graph
{
	private final int width;
	private final int height;
	private final int inputHeight;
	private boolean hasInputPanel;
	private double xmax;
	private double xmin;
	private double ymax;
	private double ymin;
	private double tmin;
	private double tmax;
	private double tstep;
	
	private ArrayList<MathFunction> functions;
	private ArrayList<MathParametric> parametrics;
	private ArrayList<DataSet> dataSets;
	
	private JFrame frame;
	private GraphPanel graphPanel;
	private InputPanel inputPanel;
	private JButton inputSubmitButton;
	
	private JTextField inputTextField;
	
	private BufferedImage cachedImage;
	
	public static void main(String args[])
	{
		
		Graph graph = new GraphBuilder()
			.xRange(-5, 5)
			.yRange(-5, 5)
			.tRange(0, 10)
			.windowSize(500,500)
			.enableInputPanel()
			//.addFunction(new ParsedMathFunction("sin(x)", Color.red))
			.build();
		
		graph.view();
	}
	
	public Graph(double xmin, double xmax, double ymin, double ymax,
			double tmin, double tmax, double tstep, 
			int width, int height, int inputHeight, boolean hasInputPanel)
	{
		/* It is recommended that clients use GraphBuilder in lieu of the constructor
		 * in order to better handle all the arguments of this object.
		 */
		
		this.width = width;
		this.height = height;
		this.inputHeight = inputHeight;
		this.hasInputPanel = hasInputPanel;

		this.xmax = xmax;
		this.xmin = xmin;
		this.ymax = ymax;
		this.ymin = ymin;
		this.tmin = tmin;
		this.tmax = tmax;
		this.tstep = tstep;
		
		functions = new ArrayList<MathFunction>();
		parametrics = new ArrayList<MathParametric>();
		dataSets = new ArrayList<DataSet>();
		
		
	}
	
	public void view()
	{
		frame = new JFrame();
		
		graphPanel = new GraphPanel();
		graphPanel.setPreferredSize(new Dimension(width, height));
		graphPanel.redraw(); // To initialize cachedImage
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.add(graphPanel);
		
		if (hasInputPanel) {
			loadInputPanel();
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Image icon = cachedImage;
		frame.setIconImage(icon);
		frame.setLocation((screenSize.width - width) / 2, (screenSize.height - (height + inputHeight)) / 2);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addFunction(MathFunction f)
	{
		functions.add(f);
	}
	
	public void addParametric(MathParametric p)
	{
		parametrics.add(p);
	}
	
	public void addDataSet(DataSet d)
	{
		dataSets.add(d);
	}
	
	public void removeDataSet(DataSet d)
	{
		dataSets.remove(d);
	}
	
	/* Note in these functions that:
	 * Natives:    0     w/4     w     3w/4     w
	 * xmax's:     0     1/4     1/2   3/4      1   also applies to ymin
	 * xmin's:     1     3/4     1/2   1/4      0   also applies to ymax
	 */
	private double c2n_x(double cartesian_x)
	{
		double native_x = width*(cartesian_x - xmin) / (xmax - xmin);
		return native_x;
	}
	
	private double c2n_y(double cartesian_y)
	{
		double native_y = height*(cartesian_y - ymax) / (ymin - ymax);
		return native_y;
	}
	
	private double n2c_x(double native_x)
	{
		double fraction = native_x / width;
		double cartesian_x = fraction*xmax + (1 - fraction)*xmin;
		return cartesian_x;
	}
	
	private double n2c_y(double native_y)
	{
		double fraction = native_y / height;
		double cartesian_y = (1 - fraction)*ymax + fraction*ymin;
		return cartesian_y;
	}
	
	private Vector2D c2n(Vector2D cartesian_v)
	{
		Vector2D native_v = new Vector2D(c2n_x(cartesian_v.x), c2n_y(cartesian_v.y));
		return native_v;
	}
	
	private Vector2D n2c(Vector2D native_v)
	{
		Vector2D cartesian_v = new Vector2D(n2c_x(native_v.x), n2c_y(native_v.y));
		return cartesian_v;
		
	}
	
	public void redraw()
	{
		// Redirects a client to graphPanel.redraw()
		graphPanel.redraw();
		frame.repaint();
	}
	
	public void clear()
	{
		functions = new ArrayList<MathFunction>();
		parametrics = new ArrayList<MathParametric>();
		redraw();
	}
	
	private void loadInputPanel()
	{
		inputPanel = new InputPanel();
		inputPanel.setPreferredSize(new Dimension(width, inputHeight));
		
		inputTextField = new JTextField(20);
		inputPanel.add(inputTextField);
		
		inputSubmitButton = new JButton("Draw");
		inputSubmitButton.addMouseListener(new SubmitMouseListener());
		inputPanel.add(inputSubmitButton);
		
		frame.add(inputPanel);
	}
	
	public class InputPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)
		{
			g.setColor(Color.black);
			g.drawLine(0, 0, width, 0);
		}
	}
	
	public class SubmitMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		
		public void mouseReleased(MouseEvent e)
		{
			MathFunction f = new ParsedMathFunction(inputTextField.getText(), Color.blue);
			functions.add(f);
			redraw();
		}
		
	}
	public class GraphPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)
		{
			// This just paints the current cached image.
			// For the actual structure of drawing the image, see draw().
			g.drawImage(cachedImage, 0, 0, null);
			
		}
		
		public void draw()
		{
			Graphics g = cachedImage.createGraphics();
			
			g.setColor(Color.black);
			drawAxes(g);
			drawFunctions(g);
			drawParametrics(g);
			drawDataSets(g);
			
			// Unless we free the memory from the graphics we created,
			// this method will keep creating Graphics objects!
			g.dispose();
		}
		
		public void redraw()
		{
			// Called when we want to clear the current image completely before drawing it.
			cachedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			draw();
			frame.setIconImage(cachedImage);
		}
		
		/* Some helper functions. These are mainly aimed at breaking up draw()
		 * into some "baby bells"
		 */
		
		private void drawAxes(Graphics g)
		{
			// Set up the axes, including small lines for every whole number along them
			// Note that this alters the argument g
			
			// X axis
			g.drawLine((int) c2n_x(xmin), (int) c2n_y(0), (int) c2n_x(xmax), (int) c2n_y(0));
			// X axis lines
			for (int cx = (int) Math.ceil(xmin); cx <= (int) Math.floor(xmax); cx++) {
				g.drawLine((int) c2n_x(cx), (int) c2n_y(0) - 5, (int) c2n_x(cx), (int) c2n_y(0) + 5);
			}
			// Y axis
			g.drawLine((int) c2n_x(0), (int) c2n_y(ymin), (int) c2n_x(0), (int) c2n_y(ymax));
			// Y axis lines
			for (int cy = (int) Math.ceil(ymin); cy <= (int) Math.floor(ymax); cy++) {
					g.drawLine((int) c2n_x(0) - 5, (int) c2n_y(cy), (int) c2n_x(0) + 5, (int) c2n_y(cy));
			}
		}
		
		private void drawFunctions(Graphics g)
		{
			// Note that this alters the argument g
			
			for (MathFunction f : functions) {
				g.setColor(f.getColor());
				Vector2D lastPointN = null;
				for (int nx = 0; nx <= width; nx++) {
					double cx = n2c(new Vector2D(nx, 0)).x;
					Vector2D vn = c2n(new Vector2D(cx, f.calculate(cx)));
					
					if (lastPointN != null 
							&& lastPointN.isFinite() 
							&& vn.isFinite()
							&& (lastPointN.inBounds(0, 0, width, height) || vn.inBounds(0, 0, width, height))) {
						// There is a last point AND either the current or last point is on the screen AND neither point is infinity
						g.drawLine((int) lastPointN.x, (int) lastPointN.y, (int) vn.x, (int) vn.y);
					}
					
					lastPointN = new Vector2D(vn.x, vn.y);
				}
			}
		}
		
		private void drawParametrics(Graphics g)
		{
			// Note that this alters the argument g
			
			for (MathParametric p : parametrics) {
				g.setColor(p.getColor());
				Vector2D lastPointN = null;
				for (double t = tmin; t <= tmax; t += tstep) {
					Vector2D vn = c2n(p.calculate(t));
					
					if (lastPointN != null) {
						g.drawLine((int) lastPointN.x, (int) lastPointN.y, (int) vn.x, (int) vn.y);
					}
					
					lastPointN = vn;
				}
			}
		}
		
		private void drawDataSets(Graphics g)
		{
			// Note that this alters the argument g
			
			for (DataSet d : dataSets) {
				g.setColor(d.getColor());
				for (Vector2D vc : d) {
					
					Vector2D vn = c2n(vc);
					
					int radius = 2;
					g.fillRect((int) vn.x - radius, (int)  vn.y - radius, radius*2, radius*2);
				}
			}
			
		}
	}
}
