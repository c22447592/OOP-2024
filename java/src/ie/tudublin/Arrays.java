package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet
{
	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String minMonth = months[0];
	String maxMonth = months[0];

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};
	float min = rainfall[0];
	float max = rainfall[0];
	float avg = 0;


	public float map1(float a, float b, float c, float d, float e)
		{
			float range1 = c - b;
			float range2 = e - d;
			float value = a - b;
			float ratio = value / range1;
			return d + range2 * ratio;
		}
	
	public void drawBarchart()
	{
		colorMode(HSB);
		// draw barchart for every month and their rainfall
		for(int i = 0; i < months.length; i++)
		{
			float x = map1(i, 0, months.length, 50, 450); // maps i to a value between 50 and 450 (x-axis)
			float y = map1(rainfall[i], 0, 500, 450, 50); // maps rainfall[i] to a value between 450 and 50 (y-axis)
			float h = map1(rainfall[i], 0, 500, 0, 400); // maps rainfall[i] to a value between 0 and 400 (height of the bar)
			fill(255); // sets the fill color to white
			textAlign(CENTER, CENTER); // sets the text alignment to center
			text(months[i], x, 475); // draws the month name at the bottom of the bar
			fill(255, 255, 255); // sets the fill color to red
			rect(x, y, 25, h); // draws the bar
		}
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;

		for(int i = 0; i < months.length; i++)
		{
			println("Month: "+ months[i] + "\t" + rainfall[i] + "mm");
		}

		for (String s : m1) {
			println(s);
		}

		for (int i = 0; i < months.length; i++)
		{
			if(min > rainfall[i])
			{
				min = rainfall[i];
				minMonth = months[i];
			}
			if(max < rainfall[i])
			{
				max = rainfall[i];
				maxMonth = months[i];
			}
			avg = avg + rainfall[i];
		}
		avg = avg/months.length;

		println("Minimum Rainfall: Month: " + minMonth + "\tRainfall: " + min);
		println("Maximum Rainfall: Month: " + maxMonth + "\tRainfall: " + max);
		println("Average Rainfall: " + avg + "mm");

		//a b-c d-e
		println(map1(5, 0, 10, 0, 100));

		println(map1(25, 20, 30, 200, 300));

		println(map1(26, 25, 35, 0, 100));

		//draw barchart for months and rainfall
		

	}

	public void setup() {
		colorMode(HSB);
		background(0);

		
		
	}

	
	public void draw()
	{
		drawBarchart();
	}
}
