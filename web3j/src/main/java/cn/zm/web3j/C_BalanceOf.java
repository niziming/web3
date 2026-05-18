package cn.zm.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;

import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.util.Arrays;
import java.util.List;

public class C_BalanceOf {
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


        // balanceOf 调用
        String contractAddress = "0x1c7D4B196Cb0C7B01d743Fbc6116a902379C7238";
        Function function = new Function(
                "balanceOf",
                Arrays.asList(new Address(contractAddress)),
                Arrays.asList(new TypeReference<Uint256>() {})
        );

        String encoded = FunctionEncoder.encode(function);

        // 发起 eth_call
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        address,
                        contractAddress,
                        encoded
                ),
                DefaultBlockParameterName.LATEST
        ).send();

        // 解码结果
        List<org.web3j.abi.datatypes.Type> results =
                FunctionReturnDecoder.decode(
                        response.getValue(),
                        function.getOutputParameters()
                );

        BigInteger tokenBalance =
                (BigInteger) results.get(0).getValue();

        System.out.println("Token Balance: " + tokenBalance);
    }
}
