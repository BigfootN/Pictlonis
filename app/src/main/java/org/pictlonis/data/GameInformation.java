package org.pictlonis.data;

import android.content.Context;

import org.pictlonis.activity.draw.Drawer;
import org.pictlonis.chat.ChatView;
import org.pictlonis.net.NetworkNode;
import org.pictlonis.net.client.Client;
import org.pictlonis.net.host.Server;

import java.io.File;

/**
 * Created by bigfoot on 01/11/17.
 */

public class GameInformation {
	private static final GameInformation mInstance = new GameInformation();
	private User mUser;
	private int nbPlayers;
	private int nbConnected;
	private Context ctx;
	private File USERS_FILE;
	private boolean isPlayer;
	private Drawer drawer;
	private ChatView chat;
	private UserList userList;

	public enum NodeType {
		SERVER,
		CLIENT
	};

	private Server server;
	private Client client;
	private NodeType type;

	private GameInformation() {
		server = null;
		client = null;
		nbPlayers = 0;
		nbConnected = 0;
	}

	public File getUsersFile() {
		return USERS_FILE;
	}

	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	public void setUser(User user) {
		mUser = user;
	}

	public static GameInformation getInstance() {
		return mInstance;
	}

	public void setContext(Context ctx) throws Exception {
		this.ctx = ctx;
		USERS_FILE = new File(ctx.getFilesDir(),"users.dat");
		USERS_FILE.createNewFile();
	}

	public void setNode(NodeType type, NetworkNode node) {
		if (type == NodeType.SERVER)
			server = (Server) node;
		else if (type == NodeType.CLIENT)
			client = (Client) node;

		this.type = type;
	}

	public NodeType getGameType() {
		return type;
	}

	public NetworkNode getNode() {
		if (type == NodeType.CLIENT)
			return client;
		else if (type == NodeType.SERVER)
			return server;

		return null;
	}

	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}

	public int getNbPlayers() {
		return nbPlayers;
	}

	public void setNbConnected(int nbConnected) {
		this.nbConnected = nbConnected;
	}

	public int getNbConnected() {
		return nbConnected;
	}

	public User getUser() {
		return mUser;
	}

	public void setIsPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setDrawer(Drawer drawer) {
		this.drawer = drawer;
	}

	public Drawer getDrawer() {
		return drawer;
	}

	public ChatView getChatView() {
		return chat;
	}

	public void setChat(ChatView chat) {
		this.chat = chat;
	}
}
