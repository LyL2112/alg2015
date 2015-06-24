/*
 * @author	German Hernandez
*/



import java.applet.Applet;
import java.awt.*;    // import the java.awt package
import java.awt.event.*;  // import the java.awt.event package
import java.util.Random;

public class QuickSelest extends Applet implements ActionListener
{

	static double frec[] = new double[1001];
	static int runs = 1000;
	static int kh = 1;
	static int temp ;
	static int total;
	static double average;
	static double variance;
	static long count1dot5mu;
	static int minFrec;
	static int maxFrec;
	static int compCounter;
	static int n=6;
	static Random random = new Random();
	Label prompt1,prompt2,prompt3,prompt4;      // message that prompts user to input
	TextField input1,input2,input3;   // input values are entered here
	TextArea textarea=new TextArea("",5,10);
	Button button;

	static int QuickSelectUn(int a[], int p, int q, int i)
	{
		int r,k;
		if ( p==q) return a[p];
		r = RandomPartition(a, p, q);
		k=r-p+1;
		if(i==k){
			temp=a[r];
			return temp;
		}
		if(i<k){
			return QuickSelectUn(a, p, r - 1, i);
		}

		else	return QuickSelectUn(a, r + 1, q, i - k);
	}

	static int RandomPartition(int a[], int p, int r)
	{

		swap(a, p,randomPivot(p,r));
		return Partition(a,p,r);

	}

	static int Partition(int a[], int p, int r)
	{


		int pivot = a[p];
		int i = p;

		for(int j=p+1; j <= r; j++)
		{
		    if (incComp() && (a[j] <= pivot)) {
					++i;
					swap(a,i,j);
				}

		}

		swap(a, p, i);
		return i;

	}


static void swap (int v[], int i, int j)
{
	int	t;

	t = v[i];
	v[i] = v[j];
	v[j] = t;
}

 static int randomPivot(int lo, int li)
{

		int a,b,c;

		b=li-lo+1;

		a = random.nextInt(b);

		c = lo+a;

		return c;
	}

 static boolean incComp()
 {
		compCounter ++;
		return true;

 }

 static int CountComparisonsRandomQuickSort(int a[])
 {

		compCounter = 0;

		QuickSelectUn(a, 0, a.length - 1, kh);

		return compCounter;

 }
  public static void distributionCalc(){
				int x[] = new int[n];
				int r,k;
				total = 0;
				for (int j = 0; j < runs; j++)
				{
					for(k=0; k < n; k++){
						x[k]= n-k;
					}
					/*/
					x[0]=6;
					x[1]=10;
					x[2]=13;
					x[3]=5;
					x[4]=8;
					x[5]=3;
					x[6]=2;
					x[7]=11;

					*/
					r = CountComparisonsRandomQuickSort(x);
					frec[r]++;
                    total += r;
                }
	  average = ((double) total) / runs;
}


 public void init()
   {
	setLayout(null);
	resize(600,450);

    prompt1 = new Label( "Enter n:" );
    add( prompt1 );  // put prompt on applet

    input1 = new TextField( 2 );
	input1.setText(Integer.toString(n));
    add(input1);   // put input TextField on applet


   prompt4 = new Label( "Enter kh:" );
   add( prompt4 );  // put prompt on applet

   input3 = new TextField( 2 );
   input3.setText(Integer.toString(kh));
   add( input3 );   // put input TextField on applet

	prompt2 = new Label( "Enter runs:" );
    add( prompt2 );  // put prompt on applet

    input2 = new TextField( 5 );
	input2.setText(Integer.toString(runs));
    add( input2 );   // put input TextField on applet

    button = new Button("Run") ;
	button.setFont(new Font("Helvetica", Font.PLAIN, 14));
	add(button );

    prompt3 = new Label ("Distribution");
	add (prompt3);
    add(textarea);

	//Relocation of elements in the Applet
	textarea.reshape(400,50,150,325);
	prompt1.reshape(50,10,50,20);
	input1.reshape(100,10,30,20);
	prompt2.reshape(150,10,70,20);
	input2.reshape(225,10,70,20);
	button.reshape(430,10,50,20);
	prompt3.reshape(420,30,170,20);
	prompt4.reshape(305,10,60,20);
	input3.reshape(370,10,30,20);

	// "this" applet handles action events for TextField input
	button.addActionListener(this);
	input1.addActionListener( this );
	input2.addActionListener( this );
	input3.addActionListener( this );
     // "this" applet handles action events for TextField input

   }

   // process user's action in TextField input
   public void actionPerformed( ActionEvent e )
   {
      // get the number and convert it to an integer
      if (e.getSource() == button )
			{
				n = Integer.parseInt( input1.getText() );
     		runs = Integer.parseInt( input2.getText());
			kh = Integer.parseInt( input3.getText());
     		repaint();
			}

   }

	public static void main(String[] args) {

	}

	public void paint(Graphics g) {

		int c,k,r;
	 	int v[] = new int[n];
		count1dot5mu = 0;
		average = 0.0;
		variance = 0.0;
		total = 0;

		minFrec=1001;
		maxFrec=0;

		textarea.setText("");

		for(k=0; k<1001; k++) frec[k] = 0;

		distributionCalc();

		for(k = 0; k < 1001; k++){
					if (( frec[k] > 0.0) && (minFrec > k)) minFrec = k;
					if (( frec[k] > 0.0) && (maxFrec < k)) maxFrec = k;
					if ( frec[k] > 0.0 ) variance += ((k-average)*(k-average))* (frec[k]/(double)runs);
					if ( k > (1.5 * average) ) count1dot5mu +=frec[k];
		}
		int i = 0;
		g.setColor(Color.black);
		g.drawString("Probability", 10, 50);
		g.drawString("Steps",350,340);
		g.drawString("n = "+n,155,50);
 		g.drawString("kh = "+temp,155,65);
		g.drawString("Number of permutations = "+runs,155,80);
		g.drawString("Average number of steps =  "+average,50,380);
		g.drawString("Deviation  of the number of steps =  " + Math.sqrt(variance),50,400);
		g.drawString("Probabilty of being larger than 1.5 the average =  " + (double)count1dot5mu /(double)runs,50,420);
		g.drawString("Creado por: Luis Fernando Garcia" ,360,439);
		int y = 80;
		for(i=minFrec; i <= maxFrec; i++) {
			textarea.appendText("P("+i+")="+frec[i]/(double)runs+"["+(int)frec[i]+"]\n");
		}

		g.setColor(Color.red);
		g.drawLine(50,50,50,320);
		g.drawLine(50,320,350,320);

		g.setColor(Color.black);

		g.drawString("1.0", 10, 70);
		g.drawString("0.8", 10, 120);
		g.drawString("0.6", 10, 170);
		g.drawString("0.4", 10, 220);
		g.drawString("0.2", 10, 270);
		g.drawString("0.0", 10, 320);

		int yf;
		int shift = (int) (200.0/(double)(maxFrec-minFrec+1));
		int ishift = (int) (100.0/(double)(maxFrec-minFrec+1));
		g.setColor(Color.blue);
		for (i=minFrec; i< (maxFrec+1); i=i+1){
		  yf = (int) (frec[i]/(double)runs *250.0) ;
			g.fillRect(50+((i-minFrec)*shift),320-yf, shift, yf);
			g.drawString(""+i,50+ishift+((i-minFrec)*shift) , 330);

		}


	}


}


