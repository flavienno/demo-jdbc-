/**
 * 
 */
package fr.diginamic.props;

import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

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
		Set<String> keys = monFichierConf.keySet();
		for (String key : keys) {
			userName = monFichierConf.getString(key);
			System.out.println(userName);
		}

	}

}
