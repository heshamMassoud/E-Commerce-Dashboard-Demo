package sphereConfig;

import io.sphere.client.shop.SphereClientConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public final class Config {
	/** Creates a SphereClientConfig based on a properties file. */
	public static SphereClientConfig load(String filename) throws IOException {
		Properties p = new Properties();
		FileInputStream file = new FileInputStream(filename);
		p.load(file);
		System.out.println(p.toString());
		checkValid(p);
		return new SphereClientConfig.Builder(p.getProperty("sphere.project"),
				p.getProperty("sphere.clientId"),
				p.getProperty("sphere.clientSecret"), Locale.ENGLISH).build();
	}

	private static void checkValid(Properties p) {
		if (p.getProperty("sphere.project").equals("my-project-key"))
			throw new RuntimeException(
					"Please provide your project credentials first. See the 'config.properties' file.");
	}
}