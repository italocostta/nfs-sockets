package br.edu.ifpb.gugawag.so.sockets;

import java.io.*;
import java.net.*;
import java.util.*;

public class NFSServidor {

    private static final int PORT = 7001;
    private static List<Arquivo> files = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado. Aguardando clientes...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Thread para tratar cada cliente em paralelo
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream())
            ) {
                while (true) {
                    String comando = dis.readUTF();
                    System.out.println("Comando recebido do cliente: " + comando);

                    String[] partes = comando.split("\\s+");
                    String response = "";

                    switch (partes[0]) {
                        case "readdir":
                            response = readdir();
                            break;
                        case "rename":
                            if (partes.length >= 3) {
                                rename(partes[1], partes[2]);
                            } else {
                                response = "Formato de comando inválido";
                            }
                            break;
                        case "create":
                            if (partes.length >= 2) {
                                create(partes[1]);
                            } else {
                                response = "Formato de comando inválido";
                            }
                            break;
                        case "remove":
                            if (partes.length >= 2) {
                                remove(partes[1]);
                            } else {
                                response = "Formato de comando inválido";
                            }
                            break;
                        default:
                            response = "Comando desconhecido";
                            break;
                    }

                    dos.writeUTF(response);
                    dos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String readdir() {
            StringBuilder fileList = new StringBuilder();
            for (Arquivo file : files) {
                fileList.append(file.getNome()).append("\n");
            }
            return fileList.toString();
        }

        private void rename(String oldName, String newName) {
            for (Arquivo file : files) {
                if (file.getNome().equals(oldName)) {
                    file.setNome(newName);
                    return;
                }
            }
        }

        private void create(String fileName) {
            files.add(new Arquivo(fileName));
        }

        private void remove(String fileName) {
            Iterator<Arquivo> iterator = files.iterator();
            while (iterator.hasNext()) {
                Arquivo file = iterator.next();
                if (file.getNome().equals(fileName)) {
                    iterator.remove();
                    return;
                }
            }
        }
    }
}
