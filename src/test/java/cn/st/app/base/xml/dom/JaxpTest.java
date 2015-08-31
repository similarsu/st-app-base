package cn.st.app.base.xml.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JaxpTest {
	
	/**
	 * 树形展现
	 * @param nodeList
	 */
	public void tree(NodeList nodeList){
		for(int i=0;i<nodeList.getLength();i++){
			Node node=nodeList.item(i);
			if(node instanceof Element){
				System.out.println(node.getNodeName());
				tree(node.getChildNodes());
			}
		}
	}
	
	/**
	 * 获取document
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public Document getDocument(String xml) throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		return builder.parse(xml);
		 
	}
	/**
	 * 将docment写到文件中
	 * @param doc
	 * @param xml
	 * @throws Exception
	 */
	public void writeDocument(Document doc,String xml) throws Exception{
		TransformerFactory factory=TransformerFactory.newInstance();
		Transformer transformer=factory.newTransformer();
		Source xmlSource=new DOMSource(doc);
		Result outputTarget=new StreamResult(new File(xml));
		transformer.transform(xmlSource, outputTarget);
	}
	
	/**
	 * 读取xml
	 * @throws Exception
	 */
	@Test
	public void readXml() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		NodeList nodeList=document.getChildNodes();
		tree(nodeList);
	}
	/**
	 * 在<书>标签的最后加入
	 * <售价>39.0</售价>
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//创建标签
		Element element=document.createElement("售价");
		element.setTextContent("39.0");
		
		Node node=document.getElementsByTagName("书").item(0);
		node.appendChild(element);
		
		writeDocument(document, "src/main/resource/book.xml");
	}
	/**
	 * 在<书架>标签下的<售价>标签前加入
	 * <售价>39.0</售价>
	 * @throws Exception
	 */
	@Test
	public void add2() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//创建标签
		Element element=document.createElement("售价");
		element.setTextContent("39.0");
		
		Node node=document.getElementsByTagName("售价").item(0);
		//调用node的parent插入节点
		node.getParentNode().insertBefore(element, node);
		
		writeDocument(document, "src/main/resource/book.xml");
	}
	/**
	 * 删除<阿猫>标签
	 * @throws Exception
	 */
	@Test
	public void delete() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//获得标签
		Node node=document.getElementsByTagName("阿猫").item(0);
		//调用node的parent 删除节点
		node.getParentNode().removeChild(node);
		writeDocument(document, "src/main/resource/book.xml");
	}
	/**
	 * 更新<书名>标签的内容为xxx
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//获得标签
		Node node=document.getElementsByTagName("书名").item(0);
		node.setTextContent("xxx");
		writeDocument(document, "src/main/resource/book.xml");
	}
	/**
	 * 读取<书>标签的name属性的值
	 * @throws Exception
	 */
	@Test
	public void readAttr() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//获得标签
		Element element=(Element) document.getElementsByTagName("书").item(0);
		System.out.println(element.getAttribute("name"));
	}
	/**
	 * 更新<书>标签的name属性的值为yyy
	 * @throws Exception
	 */
	@Test
	public void updateAttr() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//获得标签
		Element element=(Element) document.getElementsByTagName("书").item(0);
		element.setAttribute("name", "yyy");
		writeDocument(document, "src/main/resource/book.xml");
	}
	/**
	 *删除取<书>标签的name属性
	 * @throws Exception
	 */
	@Test
	public void deleteAttr() throws Exception{
		Document document=getDocument("src/main/resource/book.xml");
		//获得标签
		Element element=(Element) document.getElementsByTagName("书").item(0);
		element.removeAttribute("name");
		writeDocument(document, "src/main/resource/book.xml");
	}
}
