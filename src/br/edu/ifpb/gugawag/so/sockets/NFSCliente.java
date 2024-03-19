package br.edu.ifpb.gugawag.so.sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NFSCliente {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 7001;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao servidor");

            while (true) {
                System.out.print("Digite um dos comandos. (readdir, rename, create, remove): ");
                String command = scanner.nextLine();

                dos.writeUTF(command);
                dos.flush();

                String resposta = dis.readUTF();
                System.out.println("Resposta do servidor: " + resposta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

