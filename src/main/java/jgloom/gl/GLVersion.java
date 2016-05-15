package jgloom.gl;

import jgloom.GLOOMException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Contains information about an OpenGL version
 */
public class GLVersion {
	private GLPlatform platform;

	private int majorVersion;
	private int minorVersion;
	private int releaseVersion;

	private final String vendorString;
	private final String rendererString;

	/**
	 * Parses and determines the OpenGL version being run
	 * @param platform       The OpenGL platform (ex. GLES)
	 * @param versionString  The OpenGL version string (ex. 1.1)
	 * @param vendorString   The OpenGL vendor string
	 * @param rendererString The OpenGL renderer string
	 * @throws GLOOMException Parsing version numbers can go wrong when not to spec
     */
	public GLVersion (GLPlatform platform, String versionString, String vendorString, String rendererString) throws GLOOMException {
		this.platform = platform;
		if (platform == GLPlatform.GLES)
			extractVersion("OpenGL ES (\\d(\\.\\d){0,2})", versionString);
		else if (platform == GLPlatform.WebGL)
			extractVersion("WebGL (\\d(\\.\\d){0,2})", versionString);
		else if (platform == GLPlatform.OpenGL)
			extractVersion("(\\d(\\.\\d){0,2})", versionString);
		else {
			majorVersion = -1;
			minorVersion = -1;
			releaseVersion = -1;
			vendorString = "";
			rendererString = "";
		}

		this.vendorString = vendorString;
		this.rendererString = rendererString;
	}

	private void extractVersion (String patternString, String versionString) throws GLOOMException {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(versionString);
		boolean found = matcher.find();
		if (found) {
			String result = matcher.group(1);
			String[] resultSplit = result.split("\\.");
			majorVersion = parseInt(resultSplit[0], 2);
			minorVersion = resultSplit.length < 2 ? 0 : parseInt(resultSplit[1], 0);
			releaseVersion = resultSplit.length < 3 ? 0 : parseInt(resultSplit[2], 0);
		} else {
			majorVersion = 2;
			minorVersion = 0;
			releaseVersion = 0;
			throw new GLOOMException("Invalid version string: " + versionString);
		}
	}

	/** Forgiving parsing of gl major, minor and release versions as some manufacturers don't adhere to spec **/
	private int parseInt (String v, int defaultValue) {
		try {
			return Integer.parseInt(v);
		} catch (NumberFormatException nfe) {
			return defaultValue;
		}
	}

	/** @return what {@link GLPlatform} of GL implementation this application has access to, e.g. {@link GLPlatform#OpenGL} or {@link GLPlatform#GLES}*/
	public GLPlatform getType () {
		return platform;
	}

	/** @return the major version of current GL connection. -1 if running headless */
	public int getMajorVersion () {
		return majorVersion;
	}

	/** @return the minor version of the current GL connection. -1 if running headless */
	public int getMinorVersion () {
		return minorVersion;
	}

	/** @return the release version of the current GL connection. -1 if running headless */
	public int getReleaseVersion () {
		return releaseVersion;
	}

	/** @return the vendor string associated with the current GL connection */
	public String getVendorString () {
		return vendorString;
	}

	/** @return the name of the renderer associated with the current GL connection.
	 * This name is typically specific to a particular configuration of a hardware platform. */
	public String getRendererString () {
		return rendererString;
	}

	/**
	 * Checks to see if the current GL connection version is higher, or equal to the provided test versions.
	 *
	 * @param testMajorVersion the major version to test against
	 * @param testMinorVersion the minor version to test against
	 * @return true if the current version is higher or equal to the test version
	 */
	public boolean isVersionEqualToOrHigher (int testMajorVersion, int testMinorVersion) {
		return majorVersion > testMajorVersion || (majorVersion == testMajorVersion && minorVersion >= testMinorVersion);
	}

	/** @return a string with the current GL connection data */
	public String getDebugVersionString () {
		return "Type: " + platform + "\n" +
				"Version: " + majorVersion + ":" + minorVersion + ":" + releaseVersion + "\n" +
				"Vendor: " + vendorString + "\n" +
				"Renderer: " + rendererString;
	}

}