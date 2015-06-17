package com.ae.dataGenerateToolTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ae.dataGenerateTool.data.ConfigHelper;
import com.ae.dataGenerateTool.data.XMLHelper;

public class XMLHelperTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		XMLHelper xmlHelperTest=XMLHelper.getXMLHelper();
	    //System.out.println(xmlHelperTest.getRoot().toString());
	    xmlHelperTest.printXML();
	}

}
