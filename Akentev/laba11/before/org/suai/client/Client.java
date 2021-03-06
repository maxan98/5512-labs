package org.suai.client;


import java.net.DatagramSocket;
import java.net.SocketAddress;

import java.io.Closeable;
import java.io.IOException;

import org.suai.console.ConsoleManager;


public class Client implements Closeable {

	private String name;

	private DatagramSocket sourceSocket;
	private SocketAddress destinationSocket;

	private Sender sender = null;
	private Listener listener = null;


	public Client(String name, DatagramSocket sourceSocket, SocketAddress destinationSocket) {
		this.name = name;

		this.sourceSocket = sourceSocket;
		this.destinationSocket = destinationSocket;
	}


	public void close() {
		this.sourceSocket.close();
	}


	public boolean isClosed() {
		return this.sourceSocket.isClosed();
	}


	public DatagramSocket getSourceSocket() {
		return this.sourceSocket;
	}


	public SocketAddress getDestinationSocket() {
		return this.destinationSocket;
	}


	public void setDestinationSocket(SocketAddress destinationSocket) {
		this.destinationSocket = destinationSocket;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Listener getListener() {
		return this.listener;
	}


	public Sender getSender() {
		return this.sender;
	}


	public void messaging() {
		ConsoleManager console = new ConsoleManager();

		this.listener = new Listener(this, console);
		this.sender = new Sender(this, console);

		this.listener.start();
		this.sender.start();
	}

}
