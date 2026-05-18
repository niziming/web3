package cn.zm.web3j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.protocol.core.DefaultBlockParameterName;


public class B_Wallet {
    public static void main(String[] args) throws IOException {
        String rpcUrl = "https://eth-sepolia.g.alchemy.com/v2/90w48DpLR704QpnMJok1n";
        String address = "0x872dA44EFd79a834Cb8541776277742974181018";
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        String clientVersion = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        System.out.println("connect to = " + clientVersion);


        BigInteger balanceWei = web3j.ethGetBalance(
                address,
                DefaultBlockParameterName.LATEST
        ).send().getBalance();

        BigDecimal eth = Convert.fromWei(
                balanceWei.toString(),
                Convert.Unit.ETHER
        );

        System.out.println("ETH Balance: " + eth);
    }
}
