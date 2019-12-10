/**
 * 
 */
package fr.diginamic.props;

import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * @author fla
 *
 */
public class TestConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String userName = monFichierConf.getString("database.user");
		String passWord = monFichierConf.getString("database.password");
		System.out.println(userName);
		System.out.println(passWord);

	}

}
