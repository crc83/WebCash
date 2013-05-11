package com.siriusif.ui.helper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.source;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.siriusif.ui.HallUsePageTest;

public class SelenideJSFErrorChecker {
	
	/** Folder where to store pages with JSF stacktraces.
	 *  Another solution to define this folder is to set 
	 *  java property "test.dump.folder" with appropriate path.
	 *  If two options set property "test.dump.folder" has higher priority.
	 */
	public static String errorReportsFolder;
	
	private static Logger LOGGER = Logger.getLogger(HallUsePageTest.class);
	private static final String DUMP_FOLDER = "test.dump.folder";
	
	/**
	 * Return the caller class and method.
	 * 
	 * @param depth of caller
	 * @return caller
	 */
	public static StackTraceElement getCaller(int depth) {
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		int correctedDepth = Math.min(stackTraceElements.length, depth + 1);
		// +1 to include this method in depth
		return stackTraceElements[correctedDepth];
	}

	/**
	 * Verifies if page has no JSF traces.
	 * If trace found it will be saved to dump file and test will be marked as failed.
	 * Set <code>errorReportsFolder</code> or "test.dump.folder" 
	 * to define where to save error dumps.
	 */
	public static void assertNoErrorss() {
		if ($("#trace").exists()) {
			LOGGER.info("Error page detected at:" + getCaller(2));
			LOGGER.debug(source());
			saveToFile(source());
			fail();
		}
	}

	private static void saveToFile(String html) {
		String dumpFolder = System.getProperty(DUMP_FOLDER,errorReportsFolder);
		if (dumpFolder == null || "".equals(dumpFolder)) {
			return;
		}
		StackTraceElement caller = getCaller(3);
		try {
			String filename = caller.getClassName() + "#"
					+ caller.getMethodName() + ".html";
			LOGGER.info("Saving error page to file:" + filename);
			PrintWriter pw = new PrintWriter(new File(dumpFolder,filename));
			pw.write(html);
			pw.close();
			LOGGER.info("Page saved to file");
		} catch (Exception e) {
			LOGGER.info("Error saving file");
		}
	}
	
	
}
