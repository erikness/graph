package com.erikleeness.graph.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.erikleeness.graph.DataSet;
import com.erikleeness.graph.Graph;
import com.erikleeness.graph.GraphBuilder;
import com.erikleeness.graph.Vector2D;

/**
 * Graphs the digits of pi
 * @author Erik
 *
 */
public class PiFrequencyGrapher
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		new PiFrequencyGrapher().createGraph();
	}
	
	public void createGraph() throws IOException, FileNotFoundException
	{
		File piFile = new File( this.getClass().getResource("/resources/examples/pi_1million.txt").getPath() );
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(piFile));
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		
		int[] digit_count = new int[10];
		
		int unicodeDigit;
		while ( (unicodeDigit = reader.read()) != -1 ) {
			char charDigit = (char) unicodeDigit;
			if (charDigit == '.' || charDigit == '\n') continue;
			int digit = Character.getNumericValue(charDigit);
			digit_count[digit]++;
		}
		
		int highest_count = max(digit_count);
		DataSet dataSet = new DataSet();
		for (int i = 0; i < 10; i++) {
			dataSet.add(new Vector2D(i, digit_count[i] / (double) highest_count));
			
		}
		
		Graph graph = new GraphBuilder()
			.xRange(0, 9)
			.yRange(0.95, 1.05)
			.windowSize(500,500)
			.enableInputPanel()
			.addDataSet(dataSet)
			.build();
		
		graph.view();
		
		try {
			reader.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private int max(int[] data)
	{
		int max = data[0];
		for (int i : data) {
			if (i > max) max = i;
		}
		return max;
	}
}
