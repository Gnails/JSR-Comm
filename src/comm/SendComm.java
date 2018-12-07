package comm;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
//import javax.comm.*;
import gnu.io.*;
import gnu.io.SerialPort;
class SendComm{
	static Enumeration  portlist;
	static String comname;
	static SerialPort sp;
    static OutputStream os;
	public static void main(String args[]) throws PortInUseException, IOException, UnsupportedCommOperationException, InterruptedException  {	
	 portlist = CommPortIdentifier.getPortIdentifiers();
	 while(portlist.hasMoreElements()) {
		 CommPortIdentifier portid = ( CommPortIdentifier)portlist.nextElement();
		 System.out.print(portid.getName());
	    if (portid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
	    	if(portid.getName().equals("COM5")) {
	    		System.out.println("open coming");
	    		sp=(SerialPort)portid.open("SendComm",1000);
	    		System.out.println("open sp stream");
	    		os=sp.getOutputStream();
	    	}
	    }
	 }
	 try {
		 System.out.println("set param");
	 sp.setSerialPortParams(9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	 for(int j =0; j<5;j++)
	 for(int i=0; i< 5;i++) {
     String s = null;
     switch(i) {
     case 0:s="0";break;
     case 1:s="1";break;
     case 2:s="2";break;
     case 3:s="3";break;
     case 4:s="4";break;
     }
     byte[] bt=s.getBytes();
     System.out.printf("Modele%s\n",s);
	 os.write(bt,0,1);
	 os.flush();
	 Thread.sleep(2000);
}
	 os.write("0".getBytes(),0,1);
	 }
	 catch(Exception e) {
		 e.printStackTrace();
		 
	 }
	 finally {
		 if(!os.equals(null))
	 os.close();
		 if(!sp.equals(null))
	 sp.close();
	 }
}
}
