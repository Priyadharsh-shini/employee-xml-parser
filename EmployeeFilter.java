import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeFilter {
    public static void main(String[] args) throws Exception {

        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

        DefaultHandler handler = new DefaultHandler() {

            String name = "";
            int salary = 0;
            boolean n = false, s = false;

            public void startElement(String uri, String l, String q, Attributes a) {
                if (q.equals("name")) n = true;
                if (q.equals("salary")) s = true;
            }

            public void characters(char ch[], int start, int len) {
                if (n) { name = new String(ch, start, len); n = false; }
                if (s) { salary = Integer.parseInt(new String(ch, start, len)); s = false; }
            }

            public void endElement(String uri, String l, String q) {
                if (q.equals("employee") && salary > 50000)
                    System.out.println(name + " - " + salary);
            }
        };

        parser.parse("employees.xml", handler);
    }
}