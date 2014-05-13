package de.tu_darmstadt.elc.olw.milton.webdav;

import io.milton.config.HttpManagerBuilder;

import io.milton.http.HttpManager;
import io.milton.http.fs.FileSystemResourceFactory;
import io.milton.http.fs.NullSecurityManager;
import io.milton.http.fs.SimpleSecurityManager;
import io.milton.http.http11.auth.DigestGenerator;
import io.milton.servlet.Config;
import io.milton.servlet.DefaultMiltonConfigurator;

import io.milton.servlet.MiltonConfigurator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OLWConfigurator implements MiltonConfigurator {
	private static final Logger log = LoggerFactory
			.getLogger(DefaultMiltonConfigurator.class);
	protected HttpManagerBuilder builder;
	protected HttpManager httpManager;

	public OLWConfigurator() {
		log.info("Initialize Http Manager Builder");
		builder = new HttpManagerBuilder();

	}

	@Override
	public HttpManager configure(Config config) throws ServletException {
		log.info("Listing all config parameters:");
		
		for (String s : config.getInitParameterNames()) {
			log.info(" " + s + " = " + config.getInitParameter(s));
		}

		String rootDir = config.getInitParameter("rootDir");
		String contextPath = config.getInitParameter("contextPath");

		String realm = config.getInitParameter("realm");
		String userName = config.getInitParameter("userName");
		String password = config.getInitParameter("password");
		Map<String, String> userNamesAndPasswords = new HashMap<String, String>();
		userNamesAndPasswords.put(userName, password);
//		SimpleSecurityManager securityManager = new SimpleSecurityManager(
//				realm, userNamesAndPasswords);
//		securityManager.setDigestGenerator(new DigestGenerator());
		io.milton.http.SecurityManager securityManager = new NullSecurityManager();
		File root = new File(rootDir);
		if (!root.exists() || !root.isDirectory()) {
			throw new RuntimeException("Root directory is not valid: "
					+ root.getAbsolutePath());
		}
		FileSystemResourceFactory fileSystemResourceFactory = new FileSystemResourceFactory();
		fileSystemResourceFactory.setRoot(root);
		fileSystemResourceFactory.setContextPath(contextPath);
		fileSystemResourceFactory.setAllowDirectoryBrowsing(true);
		fileSystemResourceFactory.setMaxAgeSeconds(10l);
		builder.setMainResourceFactory(fileSystemResourceFactory);
		builder.setSecurityManager(securityManager);
		builder.setEnableDigestAuth(false);
		builder.setEnableBasicAuth(false);
		builder.setEnableCompression(false);
		

		

		log.debug("Initializing builder");
		initBuilder();

		return builder.buildHttpManager();
	}

	protected void initBuilder() {
		builder.init();
	}

	@Override
	public void shutdown() {
		if (httpManager != null) {
			httpManager.shutdown();
		}
	}

}
