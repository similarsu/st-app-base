package cn.st.app.base.xml.dom4j;

import java.io.FileWriter;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4jTest {
	/**
	 * 树形展现
	 * @throws Exception
	 */
	@Test
	public void read() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		tree(document);
	}
	/**
	 * 在最后添加 售价 100人民币
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		root.element("书").addElement("售价").setText("100.0人民币");
		
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"),format);
		writer.write(document);
		writer.close();
	}
	/**
	 * 指定位置添加 售价 100人民币
	 * @throws Exception
	 */
	@Test
	public void add2() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		Element author=DocumentHelper.createElement("作者");
		author.setText("海贼王");
		root.element("书").elements().add(2, author);
		
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"),format);
		writer.write(document);
		writer.close();
	}
	/**
	 * 更新<作者>火影</作者>
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		root.element("书").element("作者").setText("火影");
		
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"));
		writer.write(document);
		writer.close();
	}
	/**
	 * 删除<售价>100.0人民币</售价>
	 * @throws Exception
	 */
	@Test
	public void delete() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		Element price=(Element) root.element("书").elements("售价").get(1);
		price.getParent().remove(price);
		
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"));
		writer.write(document);
		writer.close();
	}
	/**
	 * 得到<书 name="xxx"> name 属性
	 * @throws Exception
	 */
	@Test
	public void readAttr() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		System.out.println(root.element("书").attributeValue("name"));
	}
	/**
	 * 添加 date="1989/1/1"
	 * @throws Exception
	 */
	@Test
	public void addAttr() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		root.element("书").addAttribute("date", "1989/1/1");
		
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"));
		writer.write(document);
		writer.close();
	}
	/**
	 * 修改 name="yyyyy"
	 * @throws Exception
	 */
	@Test
	public void updateAttr() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		root.element("书").attribute("name").setText("yyyyyy");
		
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"));
		writer.write(document);
		writer.close();
	}
	/**
	 * 删除 date 属性
	 * @throws Exception
	 */
	@Test
	public void deleteAttr() throws Exception{
		SAXReader reader=new SAXReader();
		Document document= reader.read("src/main/resource/book.xml");
		Element root=document.getRootElement();
		root.element("书").remove(root.element("书").attribute("date"));
		
		XMLWriter writer=new XMLWriter(new FileWriter("src/main/resource/book.xml"));
		writer.write(document);
		writer.close();
	}
	
	private void tree(Document doc){
		tree(doc.getRootElement());
	}
	
	private void tree(Element element){
		System.out.println(element.getName());
		Iterator it=element.elementIterator();
		while(it.hasNext()){
			tree((Element)it.next());
		}
	}
}
