package io.codeforall.javatars;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static ArrayList<ClientSide> clientList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int portNumber = 9000;

        System.out.println("                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "        CCCCCCCCCCCCChhhhhhh                                       tttt                  SSSSSSSSSSSSSSS                                                                                                    \n" +
                "     CCC::::::::::::Ch:::::h                                    ttt:::t                SS:::::::::::::::S                                                                                                   \n" +
                "   CC:::::::::::::::Ch:::::h                                    t:::::t               S:::::SSSSSS::::::S                                                                                                   \n" +
                "  C:::::CCCCCCCC::::Ch:::::h                                    t:::::t               S:::::S     SSSSSSS                                                                                                   \n" +
                " C:::::C       CCCCCC h::::h hhhhh         aaaaaaaaaaaaa  ttttttt:::::ttttttt         S:::::S                eeeeeeeeeeee    rrrrr   rrrrrrrrrvvvvvvv           vvvvvvv eeeeeeeeeeee    rrrrr   rrrrrrrrr   \n" +
                "C:::::C               h::::hh:::::hhh      a::::::::::::a t:::::::::::::::::t         S:::::S              ee::::::::::::ee  r::::rrr:::::::::rv:::::v         v:::::vee::::::::::::ee  r::::rrr:::::::::r  \n" +
                "C:::::C               h::::::::::::::hh    aaaaaaaaa:::::at:::::::::::::::::t          S::::SSSS          e::::::eeeee:::::eer:::::::::::::::::rv:::::v       v:::::ve::::::eeeee:::::eer:::::::::::::::::r \n" +
                "C:::::C               h:::::::hhh::::::h            a::::atttttt:::::::tttttt           SS::::::SSSSS    e::::::e     e:::::err::::::rrrrr::::::rv:::::v     v:::::ve::::::e     e:::::err::::::rrrrr::::::r\n" +
                "C:::::C               h::::::h   h::::::h    aaaaaaa:::::a      t:::::t                   SSS::::::::SS  e:::::::eeeee::::::e r:::::r     r:::::r v:::::v   v:::::v e:::::::eeeee::::::e r:::::r     r:::::r\n" +
                "C:::::C               h:::::h     h:::::h  aa::::::::::::a      t:::::t                      SSSSSS::::S e:::::::::::::::::e  r:::::r     rrrrrrr  v:::::v v:::::v  e:::::::::::::::::e  r:::::r     rrrrrrr\n" +
                "C:::::C               h:::::h     h:::::h a::::aaaa::::::a      t:::::t                           S:::::Se::::::eeeeeeeeeee   r:::::r               v:::::v:::::v   e::::::eeeeeeeeeee   r:::::r            \n" +
                " C:::::C       CCCCCC h:::::h     h:::::ha::::a    a:::::a      t:::::t    tttttt                 S:::::Se:::::::e            r:::::r                v:::::::::v    e:::::::e            r:::::r            \n" +
                "  C:::::CCCCCCCC::::C h:::::h     h:::::ha::::a    a:::::a      t::::::tttt:::::t     SSSSSSS     S:::::Se::::::::e           r:::::r                 v:::::::v     e::::::::e           r:::::r            \n" +
                "   CC:::::::::::::::C h:::::h     h:::::ha:::::aaaa::::::a      tt::::::::::::::t     S::::::SSSSSS:::::S e::::::::eeeeeeee   r:::::r                  v:::::v       e::::::::eeeeeeee   r:::::r            \n" +
                "     CCC::::::::::::C h:::::h     h:::::h a::::::::::aa:::a       tt:::::::::::tt     S:::::::::::::::SS   ee:::::::::::::e   r:::::r                   v:::v         ee:::::::::::::e   r:::::r            \n" +
                "        CCCCCCCCCCCCC hhhhhhh     hhhhhhh  aaaaaaaaaa  aaaa         ttttttttttt        SSSSSSSSSSSSSSS       eeeeeeeeeeeeee   rrrrrrr                    vvv            eeeeeeeeeeeeee   rrrrrrr            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                            \n" +
                "\n");
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket;

        ExecutorService cachePool = Executors.newCachedThreadPool();
        int i = 0;
       while (true) {
               clientSocket = serverSocket.accept();
               clientList.add(new ClientSide(clientSocket));
               cachePool.submit(clientList.get(i));
               i++;
       }
    }
}
