package cn.zm.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class A_Connect {
    public static void main(String[] args) throws IOException {
        String rpcUrl = "https://eth-sepolia.g.alchemy.com/v2/90w48DpLR704QpnMJok1n";
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        String clientVersion = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        System.out.println("connect to = " + clientVersion);

    }
}
