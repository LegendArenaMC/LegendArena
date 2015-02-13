package net.thenamedev.legendarena.history;

//This is a modified version of a class created by evilmidget38:
//
//https://gist.github.com/evilmidget38/26d70114b834f71fb3b4
//
//I don't know if I need to do give any official kind of credit, but
//I'd feel dirty if I didn't at least say this much.
//
//(the above comment is from the original source - I did not add this, just to clarify -Pixel)

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.*;

class FetchedUuid {
	String id;
}

public class UUIDFetcher {
	private static final String PROFILE_URL = "https://api.mojang.com/profiles/minecraft";
	private final String name;

	public UUIDFetcher(String name) {
		this.name = name;
	}

	public UUID fetch() throws IOException {
		Gson gson = new GsonBuilder().create();
		UUID uuid;
		HttpURLConnection connection = createConnection();
		String body = gson.toJson(name);
		writeBody(connection, body);
		FetchedUuid[] id = gson.fromJson(
				new InputStreamReader(connection.getInputStream()),
				FetchedUuid[].class);
		uuid = UUIDFetcher.getUUID(id[0].id);
		return uuid;
	}

	private static void writeBody(HttpURLConnection connection, String body)
			throws IOException {
		OutputStream stream = connection.getOutputStream();
		stream.write(body.getBytes());
		stream.flush();
		stream.close();
	}

	private static HttpURLConnection createConnection() throws IOException {
		URL url = new URL(PROFILE_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		return connection;
	}

	private static UUID getUUID(String id) {
		return UUID.fromString(id.substring(0, 8) + "-" + id.substring(8, 12)
				+ "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-"
				+ id.substring(20, 32));
	}

	public static byte[] toBytes(UUID uuid) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
		byteBuffer.putLong(uuid.getMostSignificantBits());
		byteBuffer.putLong(uuid.getLeastSignificantBits());
		return byteBuffer.array();
	}

	public static UUID fromBytes(byte[] array) {
		if(array.length != 16) {
			throw new IllegalArgumentException("Illegal byte array length: "
					+ array.length);
		}
		ByteBuffer byteBuffer = ByteBuffer.wrap(array);
		long mostSignificant = byteBuffer.getLong();
		long leastSignificant = byteBuffer.getLong();
		return new UUID(mostSignificant, leastSignificant);
	}

	public static UUID getUUIDOf(String name) throws IOException {
		return new UUIDFetcher(name).fetch();
	}
}
