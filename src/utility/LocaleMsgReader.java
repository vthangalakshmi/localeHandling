package utility;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleMsgReader {

	public static String getMessage(String code)  throws IOException {
		PropertyReader reader=new PropertyReader();
		Locale locale=new Locale(reader.getLanguage(),reader.getCountry());
		ResourceBundle mybundle = ResourceBundle.getBundle("ApplicationResources",locale);
		String mes=mybundle.getString(code);
		return mes;
	}

}