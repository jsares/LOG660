import java.sql.*;  
public class Main{

	public static void main(String[] args) { 

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@log660ora12c.logti.etsmtl.ca:1521:LOG660", "EQUIPE112", "3XDs3CZ8");

			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			  
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select * from CLIENTTEST");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			  
			//step5 close the connection object  
			con.close();  
			
			
			
			
			//			Statement st=con.createStatement();
			
//			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//			Document doc = docBuilder.parse (new File("C:\\Users\\JS\\Downloads\\Donnees (1)\\db_latin1\\clients_latin1.xml"));
//			
//			doc.getDocumentElement().normalize();
//			System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
//			NodeList listOfPersons = doc.getElementsByTagName("employee");
//			
//			for(int s=0; s<listOfPersons.getLength(); s++){
//				Node firstPersonNode = listOfPersons.item(s);
//				
//				if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
//					Element firstPersonElement = (Element)firstPersonNode;
//					NodeList nameList = firstPersonElement.getElementsByTagName("name");
//					Element nameElement =(Element)nameList.item(0);
//
//					NodeList textFNList = nameElement.getChildNodes();
//					String name=((Node)textFNList.item(0)).getNodeValue().trim();
//
//					NodeList addressList = firstPersonElement.getElementsByTagName("address");
//					Element addressElement =(Element)addressList.item(0);
//
//					NodeList textLNList = addressElement.getChildNodes();
//					String address= ((Node)textLNList.item(0)).getNodeValue().trim();
//
//					int i=st.executeUpdate("insert into user(name,address) values('"+name+"','"+address+"')");
//				}
//			}
//			
//			System.out.println("Data is successfully inserted!");
			
		}catch (Exception err) {
			System.out.println(" " + err.getMessage ());
		}
	}
}