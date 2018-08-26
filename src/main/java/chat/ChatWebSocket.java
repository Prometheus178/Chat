package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocket {
    private ChatServiceImpl chatService;
    private Session session;

    public ChatWebSocket(ChatServiceImpl chatService){
        this.chatService = chatService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        chatService.addClient(this);
        this.session = session;
    }
    @OnWebSocketMessage
    public void onMessage(String data){
        chatService.sendMessage(data);
    }
    @OnWebSocketClose
    public void onClose(int statusCode, String reason){
        chatService.removeClient(this);
    }
    public void sendString(String data){
        try {
            session.getRemote().sendString(data);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
