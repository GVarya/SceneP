using Dweiss;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using UnityEngine;

public class TCPIPServer : MonoBehaviour
{
    public int port = 8052;
    #region private members 	
    /// <summary> 	
    /// TCPListener to listen for incomming TCP connection 	
    /// requests. 	
    /// </summary> 	
    private TcpListener tcpListener;
    /// <summary> 
    /// Background thread for TcpServer workload. 	
    /// </summary> 	
    private Thread tcpListenerThread;
    /// <summary> 	
    /// Create handle to connected tcp client. 	
    /// </summary> 	
    private TcpClient connectedTcpClient;
    #endregion

    private TestChange[] projectors;
    public int channel;

    private void clearCommands()
    {
        while (!commands.IsEmpty)
        {
            String command;
            commands.TryDequeue(out command);
        }
    }

    private ConcurrentQueue<string> commands = new ConcurrentQueue<string>();

    // Use this for initialization
    void Start()
    {
        port = Settings.Instance.tcpPort;
        projectors = (TestChange[])GameObject.FindObjectsOfType(typeof(TestChange));
        tcpListenerThread = new Thread(new ThreadStart(ListenForIncommingRequests));
        tcpListenerThread.IsBackground = true;
        tcpListenerThread.Start();

       

    }
    /// <summary>
    /// 
    /// </summary>
    void Update()
    { 
        string command="";
        if (commands.TryDequeue(out command))
        {
            Debug.Log(command);
            if (command == "FadeOutAll")
            {
                Debug.Log("Fading out all");
                foreach (TestChange projector in projectors)
                {
                    projector.FadeOut();
                }
            }
            else if (command == "BlinkAll")
            {
                foreach (TestChange projector in projectors)
                {
                    projector.Blink();
                }
            }
            else
            {
                string[] str_arr = command.Split(' ');
                try
                {
                    int channel = Int16.Parse(str_arr[0]);
                    int value = Int16.Parse(str_arr[1]);
                    if (str_arr.Length != 2 || channel > 512 || channel < 0 || value < 0 || value > 255)
                        throw new Exception();
                    Debug.Log(command + " is sending to projectors");
                    foreach (TestChange projector in projectors)
                    {
                        projector.getCommand(channel, value);
                    }
                    /*
                    foreach (TestChange projector in projectors)
                    {
                        Animator animator = projector.GetComponent<Animator>();
                        if (animator != null)
                        {
                            Debug.Log("Played");
                            animator.Play("Base Layer.blink");
                        }
                    }
                    */

                }
                catch (Exception)
                {
                    Debug.Log("Bad command");
                }
            }
        }

    }

    /// <summary> 	
    /// Runs in background TcpServerThread; Handles incomming TcpClient requests 	
    /// </summary> 	
    private void ListenForIncommingRequests()
    {
        // Create listener on localhost. 			
        tcpListener = new TcpListener(IPAddress.Parse("127.0.0.1"), port);
        tcpListener.Start();
        Debug.Log("Server is listening");
        Byte[] bytes = new Byte[1024];
        
        while (true)
        {
            try
            {
                Debug.Log("Waiting for client");
                using (connectedTcpClient = tcpListener.AcceptTcpClient())
                {
                    commands.Enqueue("BlinkAll");
                    // Get a stream object for reading 					
                    using (NetworkStream stream = connectedTcpClient.GetStream())
                    {
                        int length;
                        // Read incomming stream into byte arrary. 						
                        while ((length = stream.Read(bytes, 0, bytes.Length)) != 0)
                        {
                            var incommingData = new byte[length];
                            Array.Copy(bytes, 0, incommingData, 0, length);
                            // Convert byte array to string message. 							
                            string clientMessage = Encoding.ASCII.GetString(incommingData);
                            Debug.Log("client message received as: " + clientMessage);
                            if (clientMessage != "")
                            {
                                commands.Enqueue(clientMessage);
                            }
                        }
                        clearCommands();
                        commands.Enqueue("FadeOutAll");
                    }
                }
            }
            catch (Exception exception)
            {
                Debug.Log("Exception " + exception.ToString());
                clearCommands();
                commands.Enqueue("FadeOutAll");

            }
        }
    }
        
}

