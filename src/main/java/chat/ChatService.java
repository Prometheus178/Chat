package chat;

public interface ChatService {
    public void sendMessage(String data);
    public void addClient(ChatWebSocket webSocket);
    public void removeClient(ChatWebSocket webSocket);
}
