package cn.st.app.base.xml.sax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import cn.st.app.base.xml.Book;

public class JaxpTest {
	@Test
	public void read() throws Exception {
		// 创建解析器工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 创建解析器
		SAXParser sp = factory.newSAXParser();
		// 获取读取器
		XMLReader reader = sp.getXMLReader();
		// 设置内容控制器
		reader.setContentHandler(new AllTagHandler());
		// 解析xml
		reader.parse("src/main/resource/book.xml");
	}

	@Test
	public void read2() throws Exception {
		// 创建解析器工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 创建解析器
		SAXParser sp = factory.newSAXParser();
		// 获取读取器
		XMLReader reader = sp.getXMLReader();
		// 设置内容控制器
		reader.setContentHandler(new TagNameHandler("书名", 2));
		// 解析xml
		reader.parse("src/main/resource/book.xml");
	}
	
	@Test
	public void read3() throws Exception {
		// 创建解析器工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 创建解析器
		SAXParser sp = factory.newSAXParser();
		// 获取读取器
		XMLReader reader = sp.getXMLReader();
		// 设置内容控制器
		ListHandler handler=new ListHandler();
		reader.setContentHandler(handler);
		// 解析xml
		reader.parse("src/main/resource/book.xml");
		System.out.println(handler.getBooks());
	}
}

// 获取所有标签及属性
class AllTagHandler extends DefaultHandler {

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.print("<" + qName);
		for (int i = 0; attributes != null && i < attributes.getLength(); i++) {
			System.out.print(" " + attributes.getQName(i) + "=\""
					+ attributes.getValue(i) + "\" ");
		}
		System.out.println(">");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("</" + qName + ">");
	}

}

class TagNameHandler extends DefaultHandler {
	private String tagName;
	private int whichNumber;
	private String curTagName;
	private int curNumber;

	public TagNameHandler(String tagName, int whichNumber) {
		this.tagName = tagName;
		this.whichNumber = whichNumber;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		curTagName = qName;
		if (tagName.equals(curTagName))
			curNumber++;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tagName.equals(curTagName) && curNumber == whichNumber) {
			System.out.println(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		curTagName = null;
	}

}

class ListHandler extends DefaultHandler {
	private List<Book> bookList = new ArrayList<Book>();
	private Book book;
	private String curTag;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		curTag=qName;
		if("书".equals(curTag)){
			book=new Book();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("书名".equals(curTag)){
			String name=new String(ch,start,length);
			book.setName(name);
		}
		if("作者".equals(curTag)){
			String author=new String(ch,start,length);
			book.setAuthor(author);
		}
		if("售价".equals(curTag)){
			String price=new String(ch,start,length);
			book.setPrice(price);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("书".equals(qName)){
			bookList.add(book);
			book=null;
		}
		curTag=null;
	}

	public List<Book> getBooks() {
		return bookList;
	}
	
	
	
}
