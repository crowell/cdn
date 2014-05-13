package de.tu_darmstadt.elc.olw.api.webdav;

import io.milton.common.ContentTypeUtils;
import io.milton.common.Path;
import io.milton.http.exceptions.BadRequestException;
import io.milton.http.exceptions.ConflictException;
import io.milton.http.exceptions.NotAuthorizedException;
import io.milton.http.exceptions.NotFoundException;
import io.milton.httpclient.Folder;
import io.milton.httpclient.Host;
import io.milton.httpclient.HostBuilder;
import io.milton.httpclient.HttpException;
import io.milton.httpclient.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tu_darmstadt.elc.olw.api.misc.UUIDGenerator;

public class WebDAVClient extends Thread {
	private static Logger logger = LoggerFactory.getLogger(WebDAVClient.class);

	private Host host;

	public WebDAVClient(String username, String password, String webDAVServer,
			String defaulWorkspace) {
		logger.info("Initialize webdav client");
		HostBuilder hostBuilder = new HostBuilder();
		hostBuilder.setUser(username);
		hostBuilder.setPassword(password);
		hostBuilder.setServer(webDAVServer);
		hostBuilder.setRootPath(defaulWorkspace);
		host = hostBuilder.buildHost();
		host.setUseDigestForPreemptiveAuth(false);
	}

	/**
	 * uploads file to uuid
	 * 
	 * @param file
	 * @param uuid
	 *            23-45-67-ab-...
	 * @param workspace
	 * @throws HttpException
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws ConflictException
	 * @throws NotAuthorizedException
	 * @throws URISyntaxException
	 * 
	 */
	public void uploadFile(File file, String uuid)
			throws NotAuthorizedException, ConflictException,
			BadRequestException, NotFoundException, IOException, HttpException,
			URISyntaxException {
		Path path = Path.path(uuid.replaceAll("-", "/"));
		InputStream fis = null;
		String contentType = ContentTypeUtils.findContentTypes(file);

		// Prepar input stream
		fis = new FileInputStream(file);
		Folder uuidFolder = this.createSubFolderChain(uuid);
		uuidFolder.upload(file.getName(), fis, file.length(), contentType,
				null, null);

	}

	public void uploadFolder(File folder, String uuid) {

	}

	/**
	 * 
	 * @param url
	 * @param destDir
	 * @return
	 * @throws IOException
	 */
	public File downloadFile(String fileName, String uuid, File destDir) {
		return null;

	}

	/**
	 * downloads Folder
	 * 
	 * @param uuid
	 * @param destDir
	 * @throws IOException
	 */
	public void downloadFolder(String uuid, File destDir) {

	}

	public Folder createSubFolderChain(String uuid)
			throws NotAuthorizedException, ConflictException,
			BadRequestException, NotFoundException, HttpException,
			URISyntaxException, IOException {
		Folder parentFolder = (Folder) host.getFolder("");

		Folder currentFolder = null;
		String[] IDs = uuid.split("-");
		for (int i = 0; i < IDs.length; i++) {
			if (parentFolder.child(IDs[i]) == null)
				currentFolder = parentFolder.createFolder(IDs[i]);
			else
				currentFolder = (Folder) parentFolder.child(IDs[i]);
			parentFolder = currentFolder;
		}
		return currentFolder;
	}

	/**
	 * uploads a stream to a uuid path, with a new file name
	 * 
	 * @param inputStream
	 * @param fileName
	 * @param uuid
	 * @throws SardineException
	 */
	public void uploadStream(InputStream inputStream, String fileName,
			String uuid) {
	}

	public void createSubFolder(String url, String newFolder) {

	}

	public void removeSubFolder(String uuid) {

	}

	public static void main(String[] args) throws NotAuthorizedException,
			ConflictException, BadRequestException, NotFoundException,
			IOException, HttpException, URISyntaxException {
		WebDAVClient jcr = null;
		// String uuid = "00112233-4455-6677-8899-aabbccddeeff";
		String uuid = "12345678-1234-1234-1234-123456789abc";
		String splittedUUID = UUIDGenerator.splitUUID(uuid);
		File downloadDest = new File("/home/ubuntu/Downloads");
		File videoFile = new File(downloadDest, "1.pdf");
		// downloadDest.mkdir();

		// http://olw-material.hrz.tu-darmstadt.de/olw-roh-repository/
		jcr = new WebDAVClient("service", "service",
				"localhost:8080/olw-konv-repository", "material");

		jcr.uploadFile(videoFile, splittedUUID);
		

	}
}
