package comm;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.comm.*;
class SendComm{
	static Enumeration  portlist;
	static String comname;
	static SerialPort sp;
    static BufferedOutputStream os;
	public static void main(String args[]) throws PortInUseException, IOException, UnsupportedCommOperationException  {	
	 portlist = CommPortIdentifier.getPortIdentifiers();
	 while(portlist.hasMoreElements()) {
		 CommPortIdentifier portid = ( CommPortIdentifier)portlist.nextElement();
		 System.out.print(portid.getName());
	    if (portid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
	    	if(portid.getName().equals(comname)) {
	    		sp=(SerialPort)portid.open("PortDemo",1000);
	    		os=(BufferedOutputStream)sp.getOutputStream();
	    	}
	    }
	 }
	 sp.setSerialPortParams(9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	 os.write(0);
	 os.flush();
	 os.close();
	 sp.close(); 
}
}
