/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;
 
public class Client2 {
    String nomeServer="nomeserver";
    int portaServer= 6789;
    DataInputStream in;
    DataOutputStream out;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    int numero;
    int numero2;
    
    public Socket connetti(){
        System.out.println("2 CLIENT: partito in esecuzione");
        try{
            tastiera= new BufferedReader(new InputStreamReader(System.in));
            miosocket= new Socket (nomeServer, portaServer);
            outVersoServer= new DataOutputStream(miosocket.getOutputStream());
            inDalServer= new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            
            
        }catch (UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(){
        
            try{
                for(;;){
                    stringaRicevutaDalServer=inDalServer.readLine();
                    System.out.println(stringaRicevutaDalServer);
                    numero=tastiera.read();
                    outVersoServer.writeByte(tastiera.read());
                    if(numero == 0){
                        System.out.println(" termina elaborazione e chiude connessione");
                        miosocket.close();
                        break;
                    }
                    
                    numero=tastiera.read();
                    numero2=tastiera.read();
                    outVersoServer.writeByte(numero);
                    outVersoServer.writeByte(numero2);
                    stringaRicevutaDalServer=inDalServer.readLine();
                    System.out.println(stringaRicevutaDalServer);
                
                }
            }catch (Exception e){
                System.out.println(e.getMessage());  
                System.out.println("errore durante la connessione");
                System.exit(1);
            }
        }
        
    }

