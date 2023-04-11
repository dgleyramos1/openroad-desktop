package com.openroad.controller;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("conexao.fxml")
public class ConexaoController {

    @FXML
    private TextField textFieldIP;

    @FXML
    private TextField textFieldPort;

    private String port;

    public void setPort(String port) {
        this.port = port;
        textFieldPort.setText(port);
    }

    @FXML
    void initialize() {
        String localInetAddress = getLocalInetAddress();
        textFieldIP.setText(localInetAddress);

        System.out.println("Porta: " + port);
    }

    private static String getLocalInetAddress() {
        String ipv4 = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet4Address) {
                        ipv4 = addr.getHostAddress();
                    }
                }

                return ipv4;
            }
        } catch (SocketException e) {
            throw new RuntimeException("get local inet address fail", e);
        }
        return ipv4;
    }
}
